import java.util.ArrayList;
import java.util.Scanner;

public class NC51178 {
  static int N = 6005;
  static int n;
  static int[] happies = new int[N];
  static int[] fathers = new int[N];
  static ArrayList<ArrayList<Integer>> children = new ArrayList<>();
  static int[][] dp = new int[N][2];

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    n = input.nextInt();
    for (int i = 0; i <= n; ++i) {
      children.add(new ArrayList<>());
    }
    for (int i = 1; i <= n; ++i) {
      happies[i] = input.nextInt();
    }
    for (int i = 0; i < n - 1; ++i) {
      int l = input.nextInt(), k = input.nextInt();
      fathers[l] = k;
      children.get(k).add(l);
    }
    input.nextInt();
    input.nextInt();
    input.close();

    int ans = 0;
    for (int i = 1; i <= n; ++i) {
      if (fathers[i] == 0) {
        dfs(i);
        ans += Math.max(dp[i][1], dp[i][0]);
      }
    }
    System.out.println(ans);
  }

  static void dfs(int father) {
    dp[father][1] = happies[father];
    if (children.get(father).size() == 0) {
      dp[father][0] = 0;
      return;
    }

    for (int child : children.get(father)) {
      dfs(child);
      dp[father][0] += Math.max(dp[child][0], dp[child][1]);
      dp[father][1] += dp[child][0];
    }
  }
}
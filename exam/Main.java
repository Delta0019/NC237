import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  static ArrayList<Integer> list = new ArrayList<>();
  static int k = 0;
  static int ans = 0;
  static int[][] dp;

  static void solve() {
    int n = list.size() - 1;
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j < i; ++j) {
        dp[i][1] = Math.max(dp[i][1], dp[j][0] + list.get(i) - list.get(j));
        int next = (i + k + 1) > n ? n : (i + k + 1);
        dp[next][0] = Math.max(dp[next][0], dp[i][1]);

        if (i - 1 >= 1) {
          dp[i][0] = Math.max(dp[i][0], dp[i - 1][0]);
        }
      }
    }

    int ans = Math.max(dp[n][0], dp[n][1]);
    System.out.println(ans);
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String[] strs = input.nextLine().split("\\],");
    input.close();

    strs[0] = strs[0].substring(1, strs[0].length());
    String[] dayStrings = strs[0].split("\\, ");
    list.add(0);
    for (String day : dayStrings) {
      list.add(Integer.parseInt(day));
    }
    k = Integer.parseInt(strs[1]);
    int N = list.size() + 5;

    dp = new int[N][N];
    solve();
  }
}
import java.util.Scanner;

public class NC20240 {
  static int n, k, state_cnt;
  static int[] sta = new int[2005];
  static long[] sit = new long[2005];
  static long[][][] f = new long[15][2005][105];

  static void dfs(int x, int num, int cur) {
    if (cur >= n) {
      sit[++state_cnt] = x;
      sta[state_cnt] = num;
      return;
    }
    dfs(x, num, cur + 1);
    dfs(x + (1 << cur), num + 1, cur + 2);
  }

  static boolean compatible(int j, int x) {
    if ((sit[j] & sit[x]) > 0)
      return false;
    if (((sit[j] << 1) & sit[x]) > 0)
      return false;
    if ((sit[j] & (sit[x] << 1)) > 0)
      return false;
    return true;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    n = input.nextInt();
    k = input.nextInt();
    input.close();

    dfs(0, 0, 0);
    for (int j = 1; j <= state_cnt; ++j)
      f[1][j][sta[j]] = 1;
    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j <= state_cnt; ++j) {
        for (int x = 1; x <= state_cnt; ++x) {
          if (!compatible(j, x))
            continue;
          for (int l = sta[j]; l <= k; ++l)
            f[i][j][l] += f[i - 1][x][l - sta[j]];
        }
      }
    }

    long ans = 0;
    for (int i = 0; i <= state_cnt; ++i) {
      ans += f[n][i][k];
    }
    System.out.println(ans);
  }
}
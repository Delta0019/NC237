import java.util.Scanner;

// i: level, j: index, k: total break
// dp[i][j][k] = Math.max(dp[i - 1][index > j - 1][k - j]);
public class NC210249 {
  static int N = 55;
  static int[][] values = new int[55][55];
  static long[][] sums = new long[55][55];
  static long[][][] dp = new long[55][505][505];
  static int[] s = new int[55];

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt(), m = input.nextInt();
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= n - i + 1; ++j) {
        values[j + i - 1][i] = input.nextInt();
      }
    }
    input.close();

    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= i; ++j) {
        sums[i][j] = sums[i][j - 1] + values[i][j];
      }
    }

    for (int i = 1; i <= n; ++i) {
      s[i] = s[i - 1] + i;
    }

    long ans = 0;
    for (int i = 1; i <= n; ++i) {
      // **Note**: 这里j需要从0开始，因为下一行j = 1会访问该行的j=0，如果从1开始，则意味着对于下一行的j=1来说丢失了上一行的信息
      for (int j = 0; j <= i; ++j) {
        for (int k = 0; k <= m; ++k) {
          for (int j_pre = Math.max(j - 1, 0); j_pre <= Math.min(i - 1, k - j); ++j_pre) {
            dp[i][j][k] = Math.max(dp[i - 1][j_pre][k - j] + sums[i][j], dp[i][j][k]);
          }
          ans = Math.max(dp[i][j][k], ans);
        }
      }
    }

    System.out.println(ans);
  }
}

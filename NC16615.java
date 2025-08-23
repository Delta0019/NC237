import java.util.Scanner;

// 两种路线叠加的dp, 单一路线时为dp[i][j], 表示当前对象的位置, 这里尝试使用l, k表示另一个对象的位置
// n <= 50 -> dp[][][][] -> dp[i][j][l][k]
// 令 k > j, 以保证两条路线不相交，即一条路线走下, k对应的走上
public class NC16615 {
  static int N = 55;
  static int[][] map = new int[N][N];
  static long[][][][] dp = new long[N][N][N][N];

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt(), m = input.nextInt();
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        map[i][j] = input.nextInt();
      }
    }
    input.close();

    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        for (int k = 1; k <= n; ++k) {
          for (int l = j + 1; l <= m; ++l) {
            long v1 = dp[i - 1][j][k - 1][l];
            long v2 = j < (l - 1) ? dp[i - 1][j][k][l - 1] : -1;
            long v3 = dp[i][j - 1][k - 1][l];
            long v4 = dp[i][j - 1][k][l - 1];
            long m1 = Math.max(v1, v2);
            long m2 = Math.max(v3, v4);
            dp[i][j][k][l] = Math.max(m1, m2) + map[i][j] + map[k][l];
            // System.out.println("(" + i + ", " + j + "), (" + k + ", " + l + "): " +
            // dp[i][j][k][l]);
          }
        }
      }
    }

    System.out.println(dp[n][m - 1][n - 1][m]);
  }
}

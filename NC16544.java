import java.util.Scanner;

// 以 state 表示此时位于链的节点，以i和最右边为1的位 bit 表示该链的端点，尝试加入下一个节点j
// 状态转移方程: dp[new_state][j] = dp[new_state][j] + dp[state][i];
public class NC16544 {
  static int n, m, k;
  static long mod = 998244353;
  static long[][] dp = new long[1 << 20][25];
  static long[] ks = new long[25];
  static boolean[][] edges = new boolean[25][25];

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    n = input.nextInt();
    m = input.nextInt();
    k = input.nextInt();

    for (int i = 0; i < m; ++i) {
      int a = input.nextInt(), b = input.nextInt();
      edges[a][b] = true;
      edges[b][a] = true;
    }
    input.close();

    for (int i = 0; i < n; ++i) {
      dp[1 << i][i + 1] = 1;
    }

    int len = (1 << n) - 1;
    for (int state = 1; state <= len; ++state) {
      int start = n;
      for (int i = 0; i < n; ++i) {
        if ((state >> i & 1) == 1) {
          start = i;
          break;
        }
      }
      // enum start
      for (int i = start; i < n; ++i) {
        if ((state >> i & 1) == 0)
          continue;
        // enum end
        for (int j = start + 1; j < n; ++j) {
          if ((state >> j & 1) == 1)
            continue;
          int st = 1 << j;
          if (edges[i + 1][j + 1]) {
            dp[state | st][j + 1] = (dp[state | st][j + 1] + dp[state][i + 1]) % mod;

          }
        }
        if (edges[i + 1][start + 1]) {
          int edge_len = Integer.bitCount(state);
          if (edge_len > 2) {
            int pos = edge_len % k;
            ks[pos] = (ks[pos] + dp[state][i + 1]) % mod;
          }
        }
      }
    }

    for (int i = 0; i < k; ++i) {
      System.out.println(ks[i] / 2);
    }
  }
}

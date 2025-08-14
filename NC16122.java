import java.util.Scanner;

// 注意 floyd 算法需要把 k 放在最外层，确保第 k 轮是基于前 k - 1 轮的正确结果。
// 接下来就是 TSP 邮递问题 (NP 问题)，每当访问一个状态时，都能确保所有小于它的状态都已经被访问。
// TSP 循环内部，只需要遍历当前可能在的节点，以及接下来将要前往的节点即可。
public class NC16122 {
  static int n, m, r;
  static int[] parks = new int[20];
  static int[][] costs = new int[205][205];
  static int N = 2000005;
  static int[][] dp = new int[1 << 15][20];

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    n = input.nextInt();
    m = input.nextInt();
    r = input.nextInt();

    for (int i = 0; i < costs.length; ++i) {
      for (int j = 0; j < costs[i].length; ++j) {
        costs[i][j] = N;
      }
    }

    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j < dp[i].length; ++j) {
        dp[i][j] = N;
      }
    }

    for (int i = 1; i <= r; ++i) {
      parks[i] = input.nextInt();
    }

    for (int i = 0; i < m; ++i) {
      int a = input.nextInt(), b = input.nextInt(), c = input.nextInt();
      costs[a][b] = c;
      costs[b][a] = c;
    }
    input.close();

    // floyd algorithm
    for (int k = 1; k <= n; ++k) {
      for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
          costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
        }
      }
    }

    for (int i = 0; i < r; ++i) {
      dp[1 << i][i + 1] = 0;
    }

    // 01 + 10
    int len = (1 << r) - 1;
    // ignore start & end
    for (int state = 1; state < len; ++state) {
      for (int j = 0; j < r; ++j) {
        if (((state >> j) & 1) == 0)
          continue;
        for (int k = 0; k < r; ++k) {
          if (((state >> k) & 1) == 1)
            continue;
          int st = 1 << k;
          dp[state | st][k + 1] = Math.min(dp[state | st][k + 1],
              dp[state][j + 1] + costs[parks[j + 1]][parks[k + 1]]);
        }
      }
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 1; i <= r; ++i) {
      ans = Math.min(ans, dp[len][i]);
    }

    System.out.println(ans);
  }
}

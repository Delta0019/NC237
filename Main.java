import java.util.Scanner;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 1. 读取输入
    int n = sc.nextInt(); // 课程数
    int m = sc.nextInt(); // 楼层数
    long M = sc.nextLong(); // 初始法力值

    long[] power = new long[n];
    for (int i = 0; i < n; i++) {
      power[i] = sc.nextLong();
    }

    long[] mana = new long[n];
    for (int i = 0; i < n; i++) {
      mana[i] = sc.nextLong();
    }

    long[] bonus = new long[m];
    for (int i = 0; i < m; i++) {
      bonus[i] = sc.nextLong();
    }

    sc.close();

    // 2. 初始化 DP 表
    // dp[i][j]: 学完第 i+1 门课，且该课在第 j+1 楼层时的最大强度
    // cost[i][j]: 达成 dp[i][j] 的最小法力消耗
    long[][] dp = new long[n][m];
    long[][] cost = new long[n][m];

    // 初始化dp为-1表示不可达，cost为极大值
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], -1L);
      Arrays.fill(cost[i], Long.MAX_VALUE);
    }

    // 3. 基本情况 (Base Case): 处理第一门课程 (i=0)
    for (int j = 0; j < m; j++) { // 遍历第一门课可能在的楼层
      long currentCost = mana[0] * bonus[j];
      if (currentCost <= M) {
        dp[0][j] = power[0] * bonus[j];
        cost[0][j] = currentCost;
      }
    }

    // 4. 状态转移: 处理第 i=1 到 n-1 门课程
    for (int i = 1; i < n; i++) { // 当前课程 i
      for (int j = 0; j < m; j++) { // 当前课程在楼层 j
        long courseCost = mana[i] * bonus[j];
        long coursePower = power[i] * bonus[j];

        for (int k = 0; k < m; k++) { // 上一门课程在楼层 k
          // 如果上一个状态不可达，则无法从该状态转移
          if (dp[i - 1][k] == -1) {
            continue;
          }

          long switchCost = Math.max(0, (j - k));
          long newTotalCost = cost[i - 1][k] + courseCost + switchCost;

          if (newTotalCost <= M) {
            long newTotalPower = dp[i - 1][k] + coursePower;

            // 更新规则
            if (newTotalPower > dp[i][j]) {
              dp[i][j] = newTotalPower;
              cost[i][j] = newTotalCost;
            } else if (newTotalPower == dp[i][j]) {
              cost[i][j] = Math.min(cost[i][j], newTotalCost);
            }
          }
        }
      }
    }

    // 5. 寻找最终结果
    long maxPower = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (dp[i][j] > maxPower) {
          maxPower = dp[i][j];
        }
      }
    }

    System.out.println(maxPower);
  }
}
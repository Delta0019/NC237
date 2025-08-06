import java.util.Scanner;

// 递推公式: 
//    对于 nums[i] 有两种情况: 
//        1. 接续上一个区间 dp[i - 1][j] + num[i];
//        2. 自成一个区间   dp[foo][j - 1] + num[i];  注意: 这里的foo为小于i的整数，表示在i以前最优的取j-1个区间的方法
public class NC235953 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        int[] nums = new int[n + 5];
        for (int i = 1; i <= n; ++i) {
            nums[i] = input.nextInt();
        }
        input.close();

        long[][] dp = new long[n + 5][m + 5];
        long[] dp_max = new long[m + 5];

        for (int i = 1; i <= n; ++i) {
            for (int j = m; j >= 1; --j) {
                dp[i][j] = Math.max(dp[i - 1][j] + nums[i], dp_max[j - 1]);
                dp_max[j] = Math.max(dp[i][j], dp_max[j]);
            }
        }

        System.out.println(dp_max[m]);
    }
}

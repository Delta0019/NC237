import java.util.Scanner;

public class NC50493 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] stones = new int[n * 2 + 5];
        int[][] dp_min = new int[n * 2 + 5][n * 2 + 5];
        int[][] dp_max = new int[n * 2 + 5][n * 2 + 5];

        int next;
        for (int i = 1; i <= n; ++i) {
            next = input.nextInt();
            stones[i] = next;
            stones[i + n] = next;
        }
        input.close();

        int[] sum = new int[2 * n + 5];
        for (int i = 1; i <= 2 * n; ++i) {
            sum[i] = sum[i - 1] + stones[i];
        }

        for (int i = 1; i <= 2 * n; ++i) {
            for (int j = 1; j <= 2 * n; ++j) {
                dp_min[j][i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i <= 2 * n; ++i) {
            for (int j = i; j >= 1; --j) {
                if (i == j) {
                    dp_min[j][i] = 0;
                    dp_max[j][i] = 0;
                }
                for (int k = j; k < i; ++k) {
                    dp_min[j][i] = Math.min(dp_min[j][i], dp_min[j][k] + dp_min[k + 1][i] + sum[i] - sum[j - 1]);
                    dp_max[j][i] = Math.max(dp_max[j][i], dp_max[j][k] + dp_max[k + 1][i] + sum[i] - sum[j - 1]);
                }
            }
        }

        int min_ans = Integer.MAX_VALUE, max_ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n - 1; ++i) {
            min_ans = Math.min(min_ans, dp_min[i][i + n - 1]);
            max_ans = Math.max(max_ans, dp_max[i][i + n - 1]);
        }
        System.out.println(min_ans);
        System.out.println(max_ans);
    }
}

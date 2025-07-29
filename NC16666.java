import java.util.Scanner;

public class NC16666 {
    static int[] dp;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        int[] values = new int[m];
        int[] matters = new int[m];
        dp = new int[n + 5];
        for (int i = 0; i < m; ++i) {
            values[i] = input.nextInt();
            matters[i] = input.nextInt();
        }
        input.close();

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = n; j >= values[i]; --j) {
                dp[j] = Math.max(dp[j], dp[j - values[i]] + values[i] * matters[i]);
                ans = Math.max(ans, dp[j]);
            }
        }

        System.out.println(ans);
    }
}

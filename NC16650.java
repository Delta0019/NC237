import java.util.Scanner;

public class NC16650 {

    static int[] dp = new int[1010];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        int m = input.nextInt();
        int[] times = new int[105];
        int[] values = new int[105];

        for (int i = 0; i < m; ++i) {
            times[i] = input.nextInt();
            values[i] = input.nextInt();
        }
        input.close();

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = t; j >= times[i]; --j) {
                dp[j] = Math.max(dp[j], dp[j - times[i]] + values[i]);
                ans = Math.max(ans, dp[j]);
            }
        }

        System.out.println(ans);

    }
}

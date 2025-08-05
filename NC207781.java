import java.util.Arrays;
import java.util.Scanner;

public class NC207781 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] times = new int[n + 5];
        for (int i = 0; i < n; ++i) {
            times[i] = input.nextInt();
        }
        input.close();

        Arrays.sort(times, 0, n);
        int[] dp = new int[n + 5];
        dp[0] = times[0];
        dp[1] = times[1];
        for (int i = 2; i < n; ++i) {
            dp[i] = Math.min(dp[i - 1] + times[0] + times[i], dp[i - 2] + times[0] + times[i] + times[1] + times[1]);
        }

        System.out.println(dp[n - 1]);
    }
}

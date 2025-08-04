import java.util.Arrays;
import java.util.Scanner;

public class NC235246 {

    private static int cmp(int a, int b) {

        if (a == b)
            return 0;
        return a < b ? -1 : 1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int[] qhorses = new int[n + 1];
        int[] thorses = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            thorses[i] = input.nextInt();
        }

        for (int i = 1; i <= n; ++i) {
            qhorses[i] = input.nextInt();
        }
        input.close();

        Arrays.sort(qhorses, 1, n + 1);
        Arrays.sort(thorses, 1, n + 1);

        long[][] dp = new long[n + 5][n + 5];
        for (int len = 1; len <= n; ++len) {
            for (int i = 1; i + len - 1 <= n; ++i) {
                int j = len + i - 1;
                dp[i][j] = Math.max(dp[i][j - 1] + 200 * cmp(thorses[j], qhorses[len]),
                        dp[i + 1][j] + 200 * cmp(thorses[i], qhorses[len]));
            }
        }

        System.out.println(dp[1][n]);
    }
}
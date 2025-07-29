import java.util.Scanner;

public class NC235624 {

    static int maxn = 5000 + 10;
    static int[][] dp = new int[maxn][maxn];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            char[] s = input.next().toCharArray(), t = input.next().toCharArray();
            if (s[0] == t[0])
                dp[1][1] = 1;
            for (int i = 1; i <= s.length; ++i) {
                for (int j = 1; j <= t.length; ++j) {
                    if (s[i - 1] == t[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            System.out.println(dp[s.length][t.length]);

        }
        input.close();
    }
}
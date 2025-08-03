import java.util.Scanner;

public class NC16693 {

    static boolean[] dp = new boolean[20100];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int v = input.nextInt();
        int n = input.nextInt();
        int[] things = new int[n];
        for (int i = 0; i < n; ++i) {
            things[i] = input.nextInt();
        }
        input.close();

        dp[0] = true;
        for (int i = 0; i < n; ++i) {
            for (int j = v; j >= things[i]; --j) {
                dp[j] = dp[j] || dp[j - things[i]];
            }
        }
        for (int j = v; j >= 0; --j) {
            if (dp[j]) {
                System.out.println(v - j);
                break;
            }
        }
    }
}

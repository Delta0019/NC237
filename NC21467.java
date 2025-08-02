import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NC21467 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while (T-- > 0) {
            int n = input.nextInt();
            int[] coins = new int[n];
            for (int i = 0; i < n; ++i) {
                coins[i] = input.nextInt();
            }
            Arrays.sort(coins);
            ArrayList<Integer> needs = new ArrayList<>();
            needs.add(coins[0]);
            int considered_index = 1;
            while (considered_index < n) {
                if (!wqBag(needs, coins[considered_index])) {
                    needs.add(coins[considered_index]);
                }
                ++considered_index;
            }
            System.out.println(needs.size());
        }
        input.close();
    }

    private static boolean wqBag(ArrayList<Integer> needs, int considered) {
        boolean[] dp = new boolean[considered + 5];
        dp[0] = true;
        for (int i = 0; i < needs.size(); ++i) {
            for (int j = needs.get(i); j <= considered; ++j) {
                dp[j] = dp[j - needs.get(i)] || dp[j];
            }
        }
        return dp[considered];
    }
}

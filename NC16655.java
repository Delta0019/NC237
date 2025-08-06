import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

// 缩点: 观察数据得知，石头树很少，但总路径很长，同时每一步很短，因此通过求步长的最小公倍数来缩短总路径。
public class NC16655 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int l = input.nextInt();
        int s = input.nextInt(), t = input.nextInt(), m = input.nextInt();
        int[] stones = new int[m + 2];
        for (int i = 1; i <= m; ++i) {
            stones[i] = input.nextInt();
        }
        input.close();
        stones[0] = 0;
        stones[m + 1] = l;

        Arrays.sort(stones);
        for (int i = 1; i <= m + 1; ++i) {
            int minus = (stones[i] - stones[i - 1]) / 2520 * 2520;
            for (int j = i; j <= m + 1; ++j) {
                stones[j] -= minus;
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= m; ++i) {
            set.add(stones[i]);
        }

        l = stones[m + 1];
        int[] dp = new int[l + t + 5];
        for (int i = 1; i < s; ++i) {
            // Mark
            dp[i] = 100;
        }

        for (int i = s; i <= l + t; ++i) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = s; j <= t; ++j) {
                if (j > i) {
                    continue;
                }
                int is_stone = set.contains(i) ? 1 : 0;
                dp[i] = Math.min(dp[i], dp[i - j] + is_stone);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = l; i <= l + t - 1; ++i) {
            ans = Math.min(ans, dp[i]);
        }

        System.out.println(ans);
    }
}

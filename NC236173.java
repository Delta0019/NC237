import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NC236173 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), d = input.nextInt();
        int[] island = new int[100001];
        int max_island = 0;
        for (int i = 0; i < n; ++i) {
            int next = input.nextInt();
            island[next]++;
            max_island = Math.max(max_island, next);
        }
        input.close();

        int[] dp = new int[100001];
        HashMap<Integer, ArrayList<Integer>> pre_steps = new HashMap<>();

        dp[d] = island[d];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(d);
        pre_steps.put(d, list);

        for (int i = d; i <= max_island; ++i) {
            for (int j = 1; j >= -1; --j) {
                if (!pre_steps.containsKey(i)) {
                    continue;
                }
                for (int prev_step : pre_steps.get(i)) {
                    int step = prev_step + j;
                    if (prev_step >= 1 && step >= 1) {
                        if (dp[i] + island[i + step] > dp[i + step]) {
                            dp[i + step] = dp[i] + island[i + step];
                            if (!pre_steps.containsKey(i + step)) {
                                pre_steps.put(i + step, new ArrayList<>());
                            }
                            pre_steps.get(i + step).clear();
                            pre_steps.get(i + step).add(step);
                        } else if (dp[i] + island[i + step] == dp[i + step]) {
                            dp[i + step] = dp[i] + island[i + step];
                            if (!pre_steps.containsKey(i + step)) {
                                pre_steps.put(i + step, new ArrayList<>());
                            }
                            pre_steps.get(i + step).add(step);
                        }
                    }
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = max_island; i >= d; --i) {
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}

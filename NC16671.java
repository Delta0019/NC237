import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// 分组背包问题，注意 base_cost 和 base_value 不应该重复加
public class NC16671 {
    private static class goods {
        int v, p;

        goods(int v, int p) {
            this.v = v;
            this.p = p;
        }
    }

    private static class pair {
        int cost, value;

        pair(int cost, int value) {
            this.cost = cost;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        HashMap<Integer, ArrayList<Integer>> plugins = new HashMap<>();
        goods[] goods_list = new goods[m + 10];
        for (int i = 1; i <= m; ++i) {
            int v = input.nextInt();
            int p = input.nextInt();
            int q = input.nextInt();
            goods_list[i] = new goods(v, p);
            if (q == 0) {
                if (!plugins.containsKey(i)) {
                    plugins.put(i, new ArrayList<>());
                }
            }
            if (q != 0) {
                if (!plugins.containsKey(q)) {
                    plugins.put(q, new ArrayList<>());
                }
                plugins.get(q).add(i);
            }
        }
        input.close();

        ArrayList<ArrayList<pair>> groups = new ArrayList<>();
        for (int key : plugins.keySet()) {
            ArrayList<pair> group = new ArrayList<>();
            int base_cost = goods_list[key].v;
            int base_value = goods_list[key].v * goods_list[key].p;
            group.add(new pair(goods_list[key].v, base_value));
            int sum_cost = goods_list[key].v, sum_value = base_value;
            for (int child : plugins.get(key)) {
                int cost = goods_list[child].v;
                int value = goods_list[child].v * goods_list[child].p;
                // Mark
                sum_cost += cost;
                sum_value += value;
                group.add(new pair(base_cost + cost, base_value + value));
            }
            if (plugins.get(key).size() == 2) {
                group.add(new pair(sum_cost, sum_value));
            }
            groups.add(group);
        }

        int[] dp = new int[n + 5];
        int ans = 0;
        for (ArrayList<pair> group : groups) {
            for (int i = n; i >= group.get(0).cost; --i) {
                for (pair p : group) {
                    if (i - p.cost >= 0) {
                        dp[i] = Math.max(dp[i], dp[i - p.cost] + p.value);
                        ans = Math.max(dp[i], ans);
                    }
                }
            }
        }

        System.out.println(ans);
    }
}

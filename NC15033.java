import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NC15033 {
    static HashMap<Integer, ArrayList<Integer>> tree = new HashMap<>();

    static final int N = 1010;

    static int n, min_max_child = Integer.MAX_VALUE, ans;
    static int[] son_tree_cnt = new int[N];

    static void dfs(int root, int father) {
        son_tree_cnt[root] = 1;
        int max_child = 0;
        for (int child : tree.get(root)) {
            if (child == father) {
                continue;
            }
            dfs(child, root);
            max_child = Math.max(son_tree_cnt[child], max_child);
            son_tree_cnt[root] += son_tree_cnt[child];
        }
        max_child = Math.max(max_child, n - son_tree_cnt[root]);
        if (max_child <= min_max_child) {
            if (max_child < min_max_child) {
                ans = root;
            } else if (root < ans) {
                ans = root;
            }
            min_max_child = max_child;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        for (int i = 1; i <= n; ++i) {
            tree.put(i, new ArrayList<>());
        }

        for (int i = 0; i < n - 1; ++i) {
            int a = input.nextInt(), b = input.nextInt();
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        input.close();

        dfs(1, 0);
        System.out.println(ans + " " + min_max_child);
    }
}

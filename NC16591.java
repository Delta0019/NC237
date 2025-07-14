import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

// 这题与食物链（NC16884）不同，食物链可以是多个分离的个体关系，因此只需分层，然后分别维护即可。
// 而这题则不能使用分层的方法，因为这题不会显示建立同级关系，因此需要**手动建立朋友关系**，然后再判断朋友之间是否会冲突。
public class NC16591 {
    static class node {
        int a, b;
        long c;

        node(int a, int b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static HashMap<Integer, Integer> acient = new HashMap<>();
    static HashMap<Integer, Integer> conflict = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.nextInt();
        int m = input.nextInt();
        ArrayList<node> nodes = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            nodes.add(new node(input.nextInt(), input.nextInt(), input.nextLong()));
        }
        input.close();
        Collections.sort(nodes, (a, b) -> {
            return (int) (b.c - a.c);
        });
        for (int i = 0; i < m; ++i) {
            node next = nodes.get(i);
            if (find(next.a) == find(next.b)) {
                System.out.println(next.c);
                return;
            }
            if (!conflict.containsKey(next.a)) {
                conflict.put(next.a, next.b);
            } else {
                merge(conflict.get(next.a), next.b);
            }
            if (!conflict.containsKey(next.b)) {
                conflict.put(next.b, next.a);
            } else {
                merge(conflict.get(next.b), next.a);
            }
        }
        System.out.println(0);
    }

    static int find(int a) {
        if (!acient.containsKey(a)) {
            acient.put(a, a);
        }
        if (acient.get(a) == a) {
            return a;
        } else {
            acient.put(a, find(acient.get(a)));
            return acient.get(a);
        }
    }

    static void merge(int a, int b) {
        acient.put(find(a), find(b));
    }
}

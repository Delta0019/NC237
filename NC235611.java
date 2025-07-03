import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;
import java.util.TreeMap;

// 注意 HashSet 不能存储多个相同的值，如果插入重复的值，默认只保留一个
public class NC235611 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);
    static PrintWriter OUT = new PrintWriter(new BufferedOutputStream(System.out));

    public static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }

    public static double nextDouble() throws Exception {
        st.nextToken();
        return st.nval;
    }

    public static String nextStr() throws Exception {
        st.nextToken();
        return st.sval;
    }

    public static String nextLine() throws Exception {
        return br.readLine();
    }

    public static class Pair {
        int a;
        int d;

        public Pair(int a, int d) {
            this.a = a;
            this.d = d;
        }

        @Override
        public String toString() {
            return "a: " + a + ", d: " + d;
        }
    }

    public static void main(String[] args) throws Exception {
        int T = nextInt();

        for (int t = 1; t <= T; ++t) {
            int n = nextInt(), m = nextInt();

            PriorityQueue<Pair> natives_maxa = new PriorityQueue<>((a, b) -> {
                if (b.a == a.a)
                    return a.d - b.d;
                return b.a - a.a;
            });
            PriorityQueue<Pair> enemies_maxd = new PriorityQueue<>((a, b) -> {
                if (b.d == a.d)
                    return a.a - b.a;
                return b.d - a.d;
            });

            for (int i = 0; i < n; ++i) {
                int a = nextInt(), b = nextInt();
                natives_maxa.add(new Pair(a, b));
            }
            for (int i = 0; i < m; ++i) {
                int a = nextInt(), b = nextInt();
                enemies_maxd.add(new Pair(a, b));
            }

            // 从小到大排序可用于对敌的部队的防御力
            TreeMap<Integer, Integer> to_solve = new TreeMap<>();
            int ans = n;
            while (!enemies_maxd.isEmpty()) {
                Pair next_enemy = enemies_maxd.poll();
                // OUT.println("Enemy: " + next_enemy);
                while (!natives_maxa.isEmpty() && natives_maxa.peek().a >= next_enemy.d) {
                    Pair can_solve = natives_maxa.poll();
                    to_solve.put(can_solve.d, to_solve.getOrDefault(can_solve.d, 0) + 1);
                }
                if (to_solve.isEmpty()) {
                    ans = -1;
                    break;
                }

                Integer higher_defense = to_solve.higherKey(next_enemy.a);

                if (higher_defense == null) {
                    --ans;
                    Integer firstKey = to_solve.firstKey();
                    if (to_solve.get(firstKey) == 1) {
                        to_solve.remove(firstKey);
                    } else {
                        to_solve.put(firstKey, to_solve.get(firstKey) - 1);
                    }
                } else {
                    if (to_solve.get(higher_defense) == 1) {
                        to_solve.remove(higher_defense);
                    } else {
                        to_solve.put(higher_defense, to_solve.get(higher_defense) - 1);
                    }
                }
            }
            OUT.println("Case #" + t + ": " + ans);
        }
        OUT.flush();
    }
}
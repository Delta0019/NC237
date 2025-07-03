import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.PriorityQueue;

public class NC235294 {
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

    public static int income(int x, int y) {
        return 500 * x + 2 * y;
    }

    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        int n = nextInt(), m = nextInt();
        HashMap<Integer, Pair> map = new HashMap<>();
        HashMap<Integer, PriorityQueue<Integer>> machines = new HashMap<>();
        PriorityQueue<Pair> sort_x = new PriorityQueue<>((a, b) -> {
            if (a.x == b.x) {
                return b.y - a.y;
            }
            return b.x - a.x;
        });
        for (int i = 0; i < n; ++i) {
            int x = nextInt(), y = nextInt();
            map.put(i, new Pair(x, y));
            if (!machines.containsKey(y)) {
                machines.put(y, new PriorityQueue<>((a, b) -> (b - a)));
            }
            machines.get(y).add(x);
        }
        for (int i = 0; i < m; ++i) {
            int x = nextInt(), y = nextInt();
            sort_x.add(new Pair(x, y));
        }
        long num = 0, sum = 0;
        while (!sort_x.isEmpty()) {
            Pair max_income = sort_x.poll();
            for (int i = max_income.y; i <= 10; ++i) {
                if (!machines.containsKey(i) ||
                        machines.get(i).size() == 0 ||
                        max_income.x > machines.get(i).peek()) {
                    continue;
                }
                ++num;
                OUT.println("x: " + max_income.x + ", y: " + max_income.y);
                sum += income(max_income.x, max_income.y);
                machines.get(i).poll();
                break;
            }
        }
        OUT.println(num + " " + sum);
        OUT.flush();
    }
}
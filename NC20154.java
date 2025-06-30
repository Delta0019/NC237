import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class NC20154 {

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

    static class Tuple<P, U, T> {
        public P i;
        public U t1;
        public T t2;

        public Tuple(P i, U t1, T t2) {
            this.i = i;
            this.t1 = t1;
            this.t2 = t2;
        }
    }

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        PriorityQueue<Tuple<Integer, Integer, Integer>> sort_ddl = new PriorityQueue<>((a, b) -> (a.t2 - b.t2));
        PriorityQueue<Tuple<Integer, Integer, Integer>> todo = new PriorityQueue<>((a, b) -> (b.t1 - a.t1));
        for (int i = 0; i < n; ++i) {
            int t1 = nextInt(), t2 = nextInt();
            sort_ddl.add(new Tuple<>(i, t1, t2));
        }

        long spend_time = 0;
        int ans = 0;
        while (!sort_ddl.isEmpty()) {
            Tuple<Integer, Integer, Integer> next_consider = sort_ddl.poll();
            if (spend_time + next_consider.t1 <= next_consider.t2) {
                todo.add(next_consider);
                spend_time += next_consider.t1;
                ++ans;
            } else {
                if (next_consider.t1 < todo.peek().t1) {
                    spend_time += next_consider.t1 - todo.peek().t1;
                    todo.poll();
                    todo.add(next_consider);
                }
            }
        }
        OUT.println(ans);
        OUT.flush();
    }
}
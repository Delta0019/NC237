import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

// 这题适合使用并查集，但由于建立集之后涉及到删除（仅删除），为了避免删除带来的问题，可以将删除操作逆向：
// 即先将这部分需要删除的 path 移除，然后再建并查集，最后一步步加上这些 path ，对应的查询倒序进行即可。
// 注意，acient.put(a, b) 是在并查集中建立 a -> b 的连接
public class NC235745 {
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

    static class path {
        int end1, end2;

        path(int end1, int end2) {
            this.end1 = end1;
            this.end2 = end2;
        }

        @Override
        public boolean equals(Object obj) {
            path other = (path) obj;
            return end1 == other.end1 && end2 == other.end2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(end1, end2);
        }
    }

    static class operation {
        int op, a, b;

        operation(int op, int a, int b) {
            this.op = op;
            this.a = a;
            this.b = b;
        }

    }

    static HashMap<Integer, Integer> acient = new HashMap<>();

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
        int new_value_root = Math.max(city2power.get(find(a)), city2power.get(find(b)));
        city2power.put(find(a), new_value_root);
        int rootA = find(a);
        int rootB = find(b);
        acient.put(rootB, rootA);
    }

    static HashMap<Integer, Integer> city2power = new HashMap<>();

    public static void main(String[] args) throws Exception {
        int n = nextInt(), m = nextInt();
        for (int i = 1; i <= n; ++i) {
            city2power.put(i, nextInt());
        }
        HashSet<path> paths = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            paths.add(new path(nextInt(), nextInt()));
        }
        int q = nextInt();
        ArrayList<operation> operations = new ArrayList<>();
        for (int i = 0; i < q; ++i) {
            String op = nextStr();
            int op_num = 0, a = 0, b = 0;
            if (op.equals("Q")) {
                op_num = 1;
                a = nextInt();
            } else if (op.equals("D")) {
                op_num = 2;
                a = nextInt();
                b = nextInt();
                path p = new path(a, b);
                paths.remove(p);
            }
            operations.add(new operation(op_num, a, b));
        }

        for (path p : paths) {
            merge(p.end1, p.end2);
        }

        ArrayList<Integer> outputs = new ArrayList<>();
        for (int i = q - 1; i >= 0; --i) {
            operation next = operations.get(i);
            if (next.op == 1) {
                outputs.add(city2power.get(find(next.a)));
            } else {
                path p = new path(next.a, next.b);
                merge(p.end1, p.end2);
            }
        }
        for (int i = outputs.size() - 1; i >= 0; --i) {
            OUT.println(outputs.get(i));
        }
        OUT.flush();
    }
}
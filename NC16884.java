import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;

public class NC16884 {

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

    static HashMap<Integer, Integer> acient = new HashMap<>();

    public static void main(String[] args) throws Exception {
        int n = nextInt(), k = nextInt();
        int opt, x, y;
        int ans = 0;
        while (k-- > 0) {
            opt = nextInt();
            x = nextInt();
            y = nextInt();
            if (x > n || y > n) {
                ++ans;
                continue;
            }
            if (opt == 1) {
                if (find(x) == find(y + n) || find(x) == find(y + 2 * n)) {
                    ++ans;
                    continue;
                }
                merge(x, y);
                merge(x + n, y + n);
                merge(x + 2 * n, y + 2 * n);
            } else if (opt == 2) {
                if (find(x) == find(y) || find(x) == find(y + 2 * n)) {
                    ++ans;
                    continue;
                }
                merge(x, y + n);
                merge(x + n, y + 2 * n);
                merge(x + 2 * n, y);
            }
        }
        OUT.println(ans);
        OUT.flush();
    }

    public static int find(int node) {
        if (!acient.containsKey(node)) {
            acient.put(node, node);
            return node;
        }
        if (acient.get(node) == node) {
            return node;
        } else {
            int old_acient = acient.get(node);
            int new_parent = find(old_acient);
            acient.put(node, new_parent);
            return acient.get(node);
        }
    }

    public static void merge(int x, int y) {
        acient.put(find(x), find(y));
    }

}
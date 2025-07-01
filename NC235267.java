import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.HashSet;

// Note: 注意用完即清空，同时清空X和Y，同时每个点可能不只有一个基地，因此不能直接使用 HashSet。
public class NC235267 {
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

    public static void main(String[] args) throws Exception {
        int n = nextInt(), m = nextInt();
        HashMap<Integer, HashSet<Integer>> Xs = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> Ys = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            int x = nextInt(), y = nextInt();
            if (!Xs.containsKey(x)) {
                Xs.put(x, new HashSet<>());
            }
            Xs.get(x).add(y);
            if (!Ys.containsKey(y)) {
                Ys.put(y, new HashSet<>());
            }
            Ys.get(y).add(x);
            if (!map.containsKey(x)) {
                map.put(x, new HashMap<>());
            }
            if (!map.get(x).containsKey(y)) {
                map.get(x).put(y, 0);
            }
            map.get(x).put(y, map.get(x).get(y) + 1);
        }
        for (int i = 0; i < m; ++i) {
            int c = nextInt(), d = nextInt(), cnt = 0;
            if (c == 0) {
                if (!Xs.containsKey(d)) {
                    OUT.println(0);
                    continue;
                }
                HashSet<Integer> destroy = Xs.get(d);
                for (int y : destroy) {
                    Ys.get(y).remove(d);
                    cnt += map.get(d).get(y);
                    map.get(d).put(y, 0);
                }
                Xs.remove(d);
            } else {
                if (!Ys.containsKey(d)) {
                    OUT.println(0);
                    continue;
                }
                HashSet<Integer> destroy = Ys.get(d);
                for (int x : destroy) {
                    Xs.get(x).remove(d);
                    cnt += map.get(x).get(d);
                    map.get(x).put(d, 0);
                }
                Ys.remove(d);
            }
            OUT.println(cnt);
        }
        OUT.flush();
    }
}

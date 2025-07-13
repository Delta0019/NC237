import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;

public class NC23803 {

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

    static HashMap<String, String> acient = new HashMap<>();

    public static void main(String[] args) throws Exception {
        int n = nextInt(), m = nextInt();
        for (int i = 0; i < n; ++i) {
            String name = nextStr();
            acient.put(name, name);
        }
        for (int i = 0; i < m; ++i) {
            int opt = nextInt();
            String p1 = nextStr(), p2 = nextStr();
            if (opt == 1) {
                merge(p1, p2);
            } else {
                OUT.println(find(p1) == find(p2) ? 1 : 0);
            }
        }
        OUT.flush();
    }

    public static String find(String name) {
        if (acient.get(name) == name) {
            return name;
        } else {
            acient.put(name, find(acient.get(name)));
            return acient.get(name);
        }
    }

    public static void merge(String n1, String n2) {
        if (acient.get(n1) != acient.get(n2)) {
            acient.put(find(n1), find(n2));
        }
    }

}
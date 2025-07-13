import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;

public class NC235622 {

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
    static HashMap<Integer, Integer> set_num = new HashMap<>();
    static HashMap<Integer, Integer> distance2root = new HashMap<>();

    public static void main(String[] args) throws Exception {
        int q = nextInt();
        String opt;
        int x, y;
        while (q-- > 0) {
            opt = nextStr();
            if (opt.equals("M")) {
                x = nextInt();
                y = nextInt();
                move(x, y);
            } else {
                x = nextInt();
                find(x);
                OUT.println(distance2root.get(x));
            }
        }
        OUT.flush();
    }

    public static int find(int node) {
        if (!acient.containsKey(node)) {
            acient.put(node, node);
            distance2root.put(node, 0);
            set_num.put(node, 1);
            return node;
        }
        if (acient.get(node) == node) {
            return node;
        } else {
            int old_acient = acient.get(node);
            int new_parent = find(old_acient);
            acient.put(node, new_parent);
            distance2root.put(node, distance2root.get(node) + distance2root.get(old_acient));
            return acient.get(node);
        }
    }

    public static void move(int node1, int node2) {
        int root_node1 = find(node1), root_node2 = find(node2);
        acient.put(root_node1, root_node2);
        distance2root.put(root_node1, set_num.get(root_node2));
        set_num.put(root_node2, set_num.get(root_node1) + set_num.get(root_node2));
    }

}
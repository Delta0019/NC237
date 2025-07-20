import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class NC16417 {

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

    static int[][] nodes;
    static boolean[] visited;
    static boolean flag = false;
    static int n, h, r;

    public static void main(String[] args) throws Exception {
        int T = nextInt();
        while (T-- > 0) {
            n = nextInt();
            h = nextInt();
            r = nextInt();
            visited = new boolean[n];
            nodes = new int[n][3];
            flag = false;
            for (int i = 0; i < n; ++i) {
                nodes[i][0] = nextInt();
                nodes[i][1] = nextInt();
                nodes[i][2] = nextInt();
            }

            for (int i = 0; i < n; ++i) {
                if (flag) {
                    break;
                }
                if (nodes[i][2] <= r) {
                    visited[i] = true;
                    dfs(nodes[i]);
                }
            }

            if (flag) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    public static void dfs(int[] node) {
        if (flag || node[2] + r >= h) {
            flag = true;
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (visited[i]) {
                continue;
            }
            if (dis(node, nodes[i]) <= 2 * r) {
                visited[i] = true;
                dfs(nodes[i]);
            }
        }
    }

    public static double dis(int[] node1, int[] node2) {
        double pow_dis = 0;
        for (int i = 0; i < 3; ++i) {
            pow_dis += Math.pow((node1[i] - node2[i]), 2);
        }
        return Math.sqrt(pow_dis);
    }
}

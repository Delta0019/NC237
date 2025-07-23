import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public class NC15665 {
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

    static class Node {
        int ni;
        int nj;
        int step;

        Node(int ni, int nj, int step) {
            this.ni = ni;
            this.nj = nj;
            this.step = step;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ni, nj);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Node node = (Node) obj;
            return ni == node.ni && nj == node.nj;
        }

        @Override
        public String toString() {
            return "Node: " + ni + ", " + nj + ", " + step;
        }
    }

    static HashMap<Node, ArrayList<Node>> csz;
    static char[][] mg;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt(), m = input.nextInt(), q = input.nextInt();
            csz = new HashMap<>();
            mg = new char[n][m];
            visited = new boolean[n][m];

            int si = 0, sj = 0;
            for (int i = 0; i < n; ++i) {
                char[] chars = input.next().toCharArray();
                for (int j = 0; j < m; ++j) {
                    mg[i][j] = chars[j];
                    visited[i][j] = false;
                    if (chars[j] == 'S') {
                        si = i;
                        sj = j;
                    }
                }
            }
            for (int i = 0; i < q; ++i) {
                Node node1 = new Node(input.nextInt(), input.nextInt(), 0);
                Node node2 = new Node(input.nextInt(), input.nextInt(), 0);
                if (!csz.containsKey(node1)) {
                    csz.put(node1, new ArrayList<>());
                }
                csz.get(node1).add(node2);
            }
            System.out.println(bfs(si, sj, n, m));
        }
    }

    static int bfs(int si, int sj, int n, int m) {
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> (a.step - b.step));
        int[] movei = new int[] { -1, 1, 0, 0 };
        int[] movej = new int[] { 0, 0, -1, 1 };
        Node start = new Node(si, sj, 0);
        heap.add(start);
        while (!heap.isEmpty()) {
            Node cur_node = heap.poll();
            visited[cur_node.ni][cur_node.nj] = true;
            if (mg[cur_node.ni][cur_node.nj] == '#') {
                continue;
            }
            if (mg[cur_node.ni][cur_node.nj] == 'T') {
                return cur_node.step;
            }
            for (int i = 0; i < 4; ++i) {
                int nexti = cur_node.ni + movei[i];
                int nextj = cur_node.nj + movej[i];
                if (!(nexti >= 0 && nexti < n && nextj >= 0 && nextj < m)) {
                    continue;
                }
                Node next_node = new Node(nexti, nextj, cur_node.step + 1);
                if (!visited[nexti][nextj]) {
                    heap.add(next_node);
                }

            }
            if (csz.containsKey(cur_node)) {
                for (Node node : csz.get(cur_node)) {
                    if (!visited[node.ni][node.nj]) {
                        node.step = cur_node.step + 3;
                        heap.add(node);
                    }
                }
            }
        }
        return -1;
    }
}

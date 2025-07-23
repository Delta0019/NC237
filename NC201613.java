import java.util.LinkedList;
import java.util.Scanner;

public class NC201613 {

    static char[][][] gd;
    static boolean[][][] visited;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        gd = new char[n + 1][n + 1][n + 1];
        visited = new boolean[n + 1][n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                char[] chars = input.next().toCharArray();
                for (int k = 1; k <= n; ++k) {
                    gd[i][j][k] = chars[k - 1];
                    visited[i][j][k] = false;
                }
            }
        }
        input.close();
        System.out.println(bfs(n));
    }

    static class Node {
        int ni, nj, nk;
        int step;

        Node(int ni, int nj, int nk, int step) {
            this.ni = ni;
            this.nj = nj;
            this.nk = nk;
            this.step = step;
        }
    }

    static int bfs(int n) {
        Node start = new Node(1, 1, 1, 1);
        LinkedList<Node> nodes = new LinkedList<>();
        int[] stepi = new int[] { 1, -1, 0, 0, 0, 0 };
        int[] stepj = new int[] { 0, 0, 1, -1, 0, 0 };
        int[] stepk = new int[] { 0, 0, 0, 0, 1, -1 };
        nodes.addLast(start);
        while (!nodes.isEmpty()) {
            Node cur_node = nodes.removeFirst();
            if (cur_node.ni == n && cur_node.nj == n && cur_node.nk == n) {
                return cur_node.step;
            }
            for (int i = 0; i < 6; ++i) {
                int nexti = cur_node.ni + stepi[i];
                int nextj = cur_node.nj + stepj[i];
                int nextk = cur_node.nk + stepk[i];
                if (nexti >= 1 && nexti <= n && nextj >= 1 && nextj <= n && nextk >= 1 && nextk <= n)
                    if (!visited[nexti][nextj][nextk]) {
                        visited[nexti][nextj][nextk] = true;
                        if (gd[nexti][nextj][nextk] == '.') {
                            nodes.add(new Node(nexti, nextj, nextk, cur_node.step + 1));
                        }
                    }
            }
        }
        return -1;
    }
}

import java.util.LinkedList;
import java.util.Scanner;

// 总体思路: 找到 Key 之前，标记走过的路为 Door (而不是 visited=true)，找到 Key 之后，标记走过的路为 W
// 为了找到 KEY 之后可以走回头路
public class NC15136 {
    static char[][] mg;
    static boolean[][] visited;
    static int step1[] = new int[] { -1, 1, 0, 0 };
    static int step2[] = new int[] { 0, 0, -1, 1 };

    static int key_row, key_col, door_row, door_col, e_row, e_col;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int h = input.nextInt(), w = input.nextInt();
        mg = new char[h][w];
        visited = new boolean[h][w];
        int start_row = 0, start_col = 0;
        for (int i = 0; i < h; ++i) {
            mg[i] = input.next().toCharArray();
            for (int j = 0; j < w; ++j) {
                if (mg[i][j] == 'S') {
                    start_row = i;
                    start_col = j;
                }
            }
        }
        input.close();
        System.out.println(bfs(start_row, start_col));
    }

    static class Node {
        int row;
        int col;
        int step_cnt;
        boolean key;

        Node(int row, int col, int step_cnt, boolean key) {
            this.row = row;
            this.col = col;
            this.step_cnt = step_cnt;
            this.key = key;
        }
    }

    static int bfs(int row, int col) {
        LinkedList<Node> nodes = new LinkedList<>();
        int steps = 0;
        boolean end = false;
        nodes.addLast(new Node(row, col, 0, false));
        ;
        while (!nodes.isEmpty()) {
            Node cur_node = nodes.removeFirst();
            if (cur_node.key) {
                mg[cur_node.row][cur_node.col] = 'W';
            } else {
                mg[cur_node.row][cur_node.col] = 'D';
            }
            for (int i = 0; i < 4; ++i) {
                int next_row = cur_node.row + step1[i];
                int next_col = cur_node.col + step2[i];
                char next_room = mg[next_row][next_col];
                Node next_node = new Node(next_row, next_col, cur_node.step_cnt + 1, cur_node.key);
                switch (next_room) {
                    case 'W':
                        break;
                    case 'D':
                        if (next_node.key) {
                            nodes.add(next_node);
                        }
                        break;
                    case 'E':
                        end = true;
                        steps = next_node.step_cnt;
                        break;
                    case '.':
                        nodes.add(next_node);
                        break;
                    case 'K':
                        next_node.key = true;
                        nodes.add(next_node);
                }
                if (end) {
                    break;
                }
            }
            if (end) {
                break;
            }
        }
        return end ? steps : -1;
    }
}
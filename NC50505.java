import java.util.ArrayList;
import java.util.Scanner;

// Note: 这题的重点是设立 dp[root][q]，即对于每个(子)树，求节点数为 0~q 时的最大苹果个数
public class NC50505 {
  static private class Edge {
    int child;
    int apples;

    Edge(int child, int apples) {
      this.child = child;
      this.apples = apples;
    }
  }

  static private class Node {
    int child_cnt;
    int apple_cnt;
    ArrayList<Edge> edges;

    Node() {
      child_cnt = 0;
      apple_cnt = 0;
      edges = new ArrayList<>();
    }
  }

  static int N = 105;
  static ArrayList<Node> nodes = new ArrayList<>();
  static int[][] dp = new int[N][N];

  static void dfs(int root, int father) {
    Node node = nodes.get(root);
    node.child_cnt = 1;
    for (Edge edge : node.edges) {
      if (edge.child == father) {
        dp[root][1] = edge.apples;
        break;
      }
    }
    node.edges.removeIf(edge -> edge.child == father);

    for (Edge edge : node.edges) {
      dfs(edge.child, root);
      Node child = nodes.get(edge.child);
      node.apple_cnt += child.apple_cnt + edge.apples;
      node.child_cnt += child.child_cnt;
    }
  }

  static int tree_dp(int root, int q) {
    if (q == 0 || dp[root][q] != 0) {
      return dp[root][q];
    }

    Node root_node = nodes.get(root);
    if (root_node.edges.isEmpty()) {
      return dp[root][1];
    }

    int maxn = 0;
    for (int i = 0; i < q; ++i) {
      int left = tree_dp(root_node.edges.get(0).child, i);
      int right = tree_dp(root_node.edges.get(1).child, q - i - 1);
      maxn = Math.max(maxn, left + right + dp[root][1]);
    }
    dp[root][q] = maxn;
    return dp[root][q];
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n, q;
    n = input.nextInt();
    q = input.nextInt();
    for (int i = 0; i <= n; ++i) {
      nodes.add(new Node());
    }
    for (int i = 0; i < n - 1; ++i) {
      int node1 = input.nextInt(), node2 = input.nextInt(), apples = input.nextInt();
      nodes.get(node1).edges.add(new Edge(node2, apples));
      nodes.get(node2).edges.add(new Edge(node1, apples));
    }
    input.close();

    int root = 0;
    for (int i = 1; i <= n; ++i) {
      Node node = nodes.get(i);
      if (node.edges.size() == 2) {
        root = i;
        break;
      }
    }

    dfs(root, root);
    tree_dp(root, q + 1);
    System.out.println(dp[root][q + 1]);
  }
}

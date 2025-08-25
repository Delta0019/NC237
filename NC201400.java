import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

public class NC201400 {
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
    int id;
    long w;
    long root_w;
    int parent;
    int child_size;
    ArrayList<Integer> children = new ArrayList<>();

    Node(int id) {
      this.id = id;
      w = 0;
      root_w = 0;
      parent = 0;
      child_size = 0;
      children = new ArrayList<>();
    }
  }

  static HashMap<Integer, ArrayList<Integer>> edges = new HashMap<>();
  static Node[] id2node = new Node[1000005];
  static long ans = Long.MAX_VALUE;

  static void buildTree(int root, int parent) {
    Node rootNode = id2node[root];
    for (int child : edges.get(root)) {
      if (child == parent)
        continue;

      Node childNode = id2node[child];
      childNode.parent = root;
      rootNode.children.add(child);
      buildTree(child, root);
      rootNode.child_size += childNode.child_size + 1;
      rootNode.w += childNode.w + childNode.child_size + 1;
    }
  }

  static void dfs(int root, int parent) {
    Node parentNode = id2node[parent];
    Node rootNode = id2node[root];
    rootNode.root_w = parentNode.root_w + parentNode.child_size
        - (2 * rootNode.child_size + 1);
    rootNode.child_size = parentNode.child_size;
    ans = Math.min(ans, rootNode.root_w);
    for (int child : rootNode.children) {
      dfs(child, root);
    }
  }

  public static void main(String[] args) throws Exception {
    int n = nextInt();
    for (int i = 1; i <= n; ++i) {
      edges.put(i, new ArrayList<>());
      id2node[i] = new Node(i);
    }
    for (int i = 0; i < n - 1; ++i) {
      int x = nextInt(), y = nextInt();
      edges.get(x).add(y);
      edges.get(y).add(x);
    }

    buildTree(1, 0);
    id2node[1].root_w = id2node[1].w;
    ans = id2node[1].root_w;
    for (int child : edges.get(1)) {
      dfs(child, 1);
    }

    OUT.println(ans);
    OUT.flush();
  }
}

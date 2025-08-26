import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class NC20115 {
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

  static final int N = 100005;
  static int[] prev_cnt;
  static ArrayList<HashSet<Integer>> edges;
  static PriorityQueue<Integer> maxHeap;
  static ArrayList<Integer> result;

  public static void main(String[] args) throws Exception {
    int d = nextInt();
    while (d-- > 0) {
      prev_cnt = new int[N];
      edges = new ArrayList<>();
      maxHeap = new PriorityQueue<>((a, b) -> b - a);
      result = new ArrayList<>();

      int n = nextInt(), m = nextInt();
      for (int i = 0; i <= n; ++i) {
        edges.add(new HashSet<>());
      }
      // 反向建边
      for (int i = 0; i < m; ++i) {
        int x = nextInt(), y = nextInt();
        if (!edges.get(y).contains(x)) {
          edges.get(y).add(x);
          prev_cnt[x]++;
        }
      }

      for (int i = 1; i <= n; ++i) {
        if (prev_cnt[i] == 0) {
          maxHeap.add(i);
        }
      }

      while (!maxHeap.isEmpty()) {
        int peek = maxHeap.poll();
        result.add(peek);
        for (int edge : edges.get(peek)) {
          prev_cnt[edge]--;
          if (prev_cnt[edge] == 0) {
            maxHeap.add(edge);
          }
        }
      }

      if (result.size() != n) {
        OUT.println("Impossible!");
      } else {
        for (int i = n - 1; i >= 0; --i) {
          OUT.print(result.get(i) + " ");
        }
        OUT.println();
      }
    }
    OUT.flush();
  }
}
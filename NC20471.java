import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class NC20471 {
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

  static int[][] map = new int[2005][2005];
  static int[][] h = new int[2005][2005];
  static int[][] l = new int[2005][2005];
  static int[][] r = new int[2005][2005];

  public static void main(String[] args) throws Exception {
    int n = nextInt();
    int m = nextInt();
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        map[i][j] = nextInt();
      }
    }

    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        if (map[i][j] == map[i - 1][j] || i == 1)
          h[i][j] = 1;
        else
          h[i][j] = h[i - 1][j] + 1;

        if (map[i][j] == map[i][j - 1] || j == 1)
          l[i][j] = 1;
        else
          l[i][j] = l[i][j - 1] + 1;
      }
    }

    for (int i = 1; i <= n; ++i) {
      for (int j = m; j >= 1; --j) {
        if (map[i][j] == map[i][j + 1] || j == m)
          r[i][j] = 1;
        else
          r[i][j] = r[i][j + 1] + 1;
      }
    }

    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        if (h[i][j] > 1) {
          l[i][j] = Math.min(l[i - 1][j], l[i][j]);
          r[i][j] = Math.min(r[i - 1][j], r[i][j]);
        }
      }
    }

    int square = 0, rectangle = 0;
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        square = Math.max(square, h[i][j] * (l[i][j] + r[i][j] - 1));
        int len = Math.min(h[i][j], l[i][j] + r[i][j] - 1);
        rectangle = Math.max(rectangle, (int) Math.pow(len, 2));
      }
    }

    System.out.println(rectangle);
    System.out.println(square);
  }
}

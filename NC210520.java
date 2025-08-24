import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Scanner;

// dp[i][j] += dp[i][j - weight[k]];
public class NC210520 {
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

  static final int N = 2305;
  static final int mod = 10;
  static int[] things = new int[N];
  static long[] currentDp = new long[N + 1]; // 每轮使用新的一维数组

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    while (input.hasNext()) {
      int n = input.nextInt(), m = input.nextInt();
      long[] f = new long[N];
      for (int i = 1; i <= n; ++i) {
        things[i] = input.nextInt();
      }

      f[0] = 1;
      for (int i = 1; i <= n; ++i) {
        for (int j = m; j >= things[i]; --j) {
          f[j] = (f[j] % mod + f[j - things[i]] % mod) % mod;
        }
      }

      for (int i = 1; i <= n; ++i) {
        for (int j = 0; j <= m; ++j) {
          if (j < things[i])
            currentDp[j] = f[j];
          else
            currentDp[j] = (f[j] % mod - currentDp[j - things[i]] % mod + 10) % mod;
        }

        for (int j = 1; j <= m; ++j) {
          OUT.print(currentDp[j]);
        }
        OUT.println();
      }
      OUT.flush();
    }
    input.close();
  }
}
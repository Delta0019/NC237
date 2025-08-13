import java.util.Scanner;

public class NC16886 {
  static long[] map = new long[105];
  static int n, m, state_cnt;
  static long[] states = new long[205];
  static int[] cnts = new int[205];
  static long[][][][] f = new long[105][50][50][405];

  static void dfs(int cur, int num, int state) {
    if (cur >= m) {
      states[++state_cnt] = state;
      cnts[state_cnt] = num;
      return;
    }
    dfs(cur + 1, num, state);
    dfs(cur + 3, num + 1, state + (1 << cur));
  }

  static boolean compatible(int state, int j, int k) {
    if ((states[state] & states[j]) > 0 || (states[state] & states[k]) > 0) {
      return false;
    }
    return true;
  }

  static boolean isP(int state, int i) {
    if ((states[state] & map[i]) > 0) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    n = input.nextInt();
    m = input.nextInt();
    for (int i = 1; i <= n; ++i) {
      char[] chars = input.next().toCharArray();
      int state = 0;
      for (int j = 0; j < m; ++j) {
        if (chars[m - j - 1] == 'H') {
          state += 1 << j;
        }
      }
      map[i] = state;
    }
    input.close();

    dfs(0, 0, 0);

    for (int i = 1; i <= state_cnt; ++i) {
      if (!isP(i, 1))
        continue;
      f[1][i][0][cnts[i]] = 1;
    }

    for (int i = 1; i <= state_cnt; ++i) {
      if (!isP(i, 2))
        continue;
      for (int j = 1; j <= state_cnt; ++j) {
        if (!isP(j, 1))
          continue;

        for (int k = cnts[i]; k <= 4 * 2; ++k) {
          f[2][i][j][k] = f[1][j][0][k - cnts[i]];
          // System.out.println(Integer.toBinaryString((int) states[j]));
          // System.out.println(Integer.toBinaryString((int) states[i]));
          if (f[2][i][j][k] == 1) {
            // System.out.println("true");
          }
          // System.out.println("-----------");
        }
      }
    }

    for (int i = 3; i <= n; ++i) {
      for (int state = 1; state <= state_cnt; ++state) {
        if (!isP(state, i)) {
          continue;
        }
        for (int j = 1; j <= state_cnt; ++j) {
          if (!isP(j, i - 1))
            continue;

          for (int k = 1; k <= state_cnt; ++k) {
            if (!isP(k, i - 2))
              continue;

            if (!compatible(state, j, k))
              continue;
            for (int l = cnts[state]; l <= 4 * i; ++l) {
              f[i][state][j][l] = f[i - 1][j][k][l - cnts[state]];
            }
          }
        }
      }
    }

    for (int l = 4 * n; l >= 0; --l) {
      for (int i = 1; i <= state_cnt; ++i) {
        for (int j = 1; j <= state_cnt; ++j) {
          if (f[n][i][j][l] > 0) {
            System.out.println(l);
            return;
          }
        }
      }
    }

  }
}

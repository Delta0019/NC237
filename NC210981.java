import java.util.Scanner;

public class NC210981 {
  static int[] cows = new int[20];
  static long[][] dp = new long[1 << 16][20];

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    int k = input.nextInt();
    for (int i = 1; i <= n; ++i) {
      int next = input.nextInt();
      cows[i] = next;
      dp[1 << (i - 1)][i] = 1;
    }
    input.close();

    int len = (1 << n) - 1;
    for (int state = 0; state <= len; ++state) {
      for (int i = 1; i <= n; ++i) {
        if ((state >> (i - 1) & 1) == 0)
          continue;
        for (int j = 1; j <= n; ++j) {
          if ((state >> (j - 1) & 1) == 1)
            continue;
          if (Math.abs(cows[i] - cows[j]) <= k)
            continue;
          dp[state | (1 << (j - 1))][j] += dp[state][i];
        }
      }
    }

    long ans = 0;
    for (int i = 1; i <= n; ++i) {
      ans += dp[len][i];
    }
    System.out.println(ans);
  }
}
import java.math.BigInteger;
import java.util.Scanner;

// 注意: 求递推公式时，应该是left和right越近，即越是里层，pow越大
public class NC16645 {
    static int n, m;
    static int[][] map = new int[85][85];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                map[i][j] = input.nextInt();
            }
        }
        input.close();

        BigInteger ans = BigInteger.valueOf(0);
        for (int row = 1; row <= n; ++row) {
            ans = ans.add(solve(row));
        }

        System.out.println(ans);
    }

    static private BigInteger solve(int row) {
        BigInteger[][] dp = new BigInteger[85][85];
        for (int i = 0; i < 85; ++i) {
            for (int j = 0; j < 85; ++j) {
                dp[i][j] = BigInteger.valueOf(0);
            }
        }

        for (int len = 1; len <= m; ++len) {
            for (int l = 1, r = l + len - 1; r <= m; ++l, ++r) {
                BigInteger powe2i = BigInteger.valueOf((long) Math.pow(2, m - len + 1));
                BigInteger left = dp[l + 1][r].add(powe2i.multiply(BigInteger.valueOf(map[row][l])));
                BigInteger right = dp[l][r - 1].add(powe2i.multiply(BigInteger.valueOf(map[row][r])));
                if (left.compareTo(right) > 0) {
                    dp[l][r] = left;
                } else {
                    dp[l][r] = right;
                }
            }
        }

        return dp[1][m];
    }
}

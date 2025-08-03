import java.util.Scanner;

// 二位背包问题，需要使用两次 dp ，每次循环层数: 遍历所有物件 -> 遍历cost -> 遍历选择个数1 -> 遍历选择个数2
public class NC14699 {
    static private class Pair {
        int value, cost;

        Pair(int value, int cost) {
            this.value = value;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt(), d = input.nextInt();
        Pair[] czs = new Pair[n];
        Pair[] lzs = new Pair[m];
        for (int i = 0; i < n; ++i) {
            int a1 = input.nextInt();
            int b1 = input.nextInt();
            czs[i] = new Pair(a1, b1);
        }
        for (int i = 0; i < m; ++i) {
            int a2 = input.nextInt();
            int b2 = input.nextInt();
            lzs[i] = new Pair(a2, b2);
        }
        input.close();

        int ans = 0;
        int[][][] dp = new int[305][10][10];
        dp[0][0][0] = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = d; j >= czs[i].cost; --j) {
                for (int k = 1; k <= 5; ++k) {
                    dp[j][k][0] = Math.max(dp[j][k][0], dp[j - czs[i].cost][k - 1][0] + czs[i].value);
                    ans = Math.max(ans, dp[j][k][0]);
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = d; j >= lzs[i].cost; --j) {
                for (int k = 1; k <= 5; ++k) {
                    for (int l = 1; l <= k; ++l) {
                        dp[j][k][l] = Math.max(dp[j][k][l], dp[j - lzs[i].cost][k][l - 1] + lzs[i].value);
                        ans = Math.max(ans, dp[j][k][l]);
                    }
                }
            }
        }

        System.out.println(ans);
    }
}

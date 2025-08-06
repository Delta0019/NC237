import java.util.Scanner;

// Note: 该实现仅能获取部分分数。实际实现需要使用 bitset 的方法，这使用 C++ 代码更容易实现。
public class NC236172 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), a = input.nextInt();
        int[] ws = new int[n];
        for (int i = 0; i < n; ++i) {
            ws[i] = input.nextInt();
        }
        input.close();

        int[] dp = new int[a + 5];
        for (int i = 0; i < n; ++i) {
            for (int j = a; j >= ws[i]; --j) {
                dp[j] = Math.max(dp[j], dp[j - ws[i]] + ws[i]);
            }
        }
        System.out.println(dp[a]);
    }
}

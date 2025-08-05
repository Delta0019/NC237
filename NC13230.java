import java.util.Scanner;

// 由于区间dp需要处理 len = 0 的情况，同时 right = left + len - 1 会在 left = 0 时为 -1，因此需要让数组从 1 开始使用索引
// 区间dp常用循环: len: 0 ~ array.length; left = 1, right = left + len - 1, left++, right++, right <= array.length
public class NC13230 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while (T-- > 0) {
            char[] a = (" " + input.next()).toCharArray();
            char[] b = (" " + input.next()).toCharArray();
            int alen = a.length - 1;
            int blen = b.length - 1;

            int ans = 0;
            boolean[][][][] dp = new boolean[55][55][55][55];
            for (int la = 0; la <= alen; la++) {
                for (int lb = 0; lb <= blen; ++lb) {
                    for (int ia = 1, ja = ia + la - 1; ja <= alen; ++ia, ++ja) {
                        for (int ib = 1, jb = ib + lb - 1; jb <= blen; ++ib, ++jb) {
                            if (la + lb <= 1) {
                                dp[ia][ja][ib][jb] = true;
                            } else {
                                if (la > 1 && a[ia] == a[ja]) {
                                    dp[ia][ja][ib][jb] |= dp[ia + 1][ja - 1][ib][jb];
                                }
                                if (la > 0 && lb > 0 && a[ia] == b[jb]) {
                                    dp[ia][ja][ib][jb] |= dp[ia + 1][ja][ib][jb - 1];
                                }
                                if (lb > 1 && b[ib] == b[jb]) {
                                    dp[ia][ja][ib][jb] |= dp[ia][ja][ib + 1][jb - 1];
                                }
                                if (la > 0 && lb > 0 && b[ib] == a[ja]) {
                                    dp[ia][ja][ib][jb] |= dp[ia][ja - 1][ib + 1][jb];
                                }
                            }
                            if (dp[ia][ja][ib][jb]) {
                                ans = Math.max(ans, la + lb);
                            }
                        }
                    }
                }
            }
            System.out.println(ans);
        }
        input.close();
    }
}

import java.util.Scanner;

// 复习 hash
public class NC16752 {

    static final long mod = 1000000007L;

    static final long base = 37L;

    static String ans = "";

    public static class Hash {
        String text;
        long[] hash;
        long[] pow;

        public Hash(String text) {
            this.text = text;
            int len = text.length();
            hash = new long[len + 1];
            pow = new long[len + 1];

            pow[0] = 1;

            for (int i = 0; i < len; ++i) {
                pow[i + 1] = (pow[i] * base) % mod;
                hash[i + 1] = (hash[i] * base + text.charAt(i)) % mod;
            }
        }

        public long gethash(int l, int r) {
            long h = hash[r] - (hash[l] * pow[r - l]) % mod;
            h = (h % mod + mod) % mod;
            return h;
        }
    }

    static String[] strings = new String[25];
    static Hash[] hashes = new Hash[25];
    static boolean[] used = new boolean[25];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; ++i) {
            String string = input.next();
            strings[i] = string;
            strings[i + n] = string;
            hashes[i] = new Hash(strings[i]);
            hashes[i + n] = new Hash(strings[i]);
        }
        char head = input.next().charAt(0);
        input.close();
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            if (strings[i].charAt(0) == head) {
                used[i] = true;
                sBuilder.append(strings[i]);
                if (sBuilder.length() > ans.length()) {
                    ans = sBuilder.toString();
                }
                dfs(sBuilder, 2 * n, i);
                sBuilder.setLength(0);
                used[i] = false;
            }
        }
        // System.out.println(ans);
        System.out.println(ans.length());
    }

    public static void dfs(StringBuilder sBuilder, int n, int last_pick) {
        for (int i = 0; i < n; ++i) {
            if (used[i]) {
                continue;
            }
            int len_last = strings[last_pick].length();
            int length = Math.min(len_last, strings[i].length());
            for (int j = length; j >= 1; --j) {
                long hash1 = hashes[last_pick].gethash(len_last - j, len_last);
                long hash2 = hashes[i].gethash(0, j);
                if (hash1 != hash2) {
                    continue;
                }
                used[i] = true;
                int orig_len = sBuilder.length();
                sBuilder.append(strings[i].subSequence(j, strings[i].length()));
                if (sBuilder.length() > ans.length()) {
                    ans = sBuilder.toString();
                }
                dfs(sBuilder, n, i);
                sBuilder.setLength(orig_len);
                used[i] = false;
            }
        }
    }
}
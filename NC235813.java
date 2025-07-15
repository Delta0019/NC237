import java.util.Scanner;

public class NC235813 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        input.close();
        int[] cnt = new int[1];
        dfs(1, cnt);
        System.out.println(cnt[0]);
    }

    static int[][] used = new int[13][13];
    static int n;

    public static void dfs(int index, int[] cnt) {
        if (index == n + 1) {
            ++cnt[0];
            return;
        }
        for (int i = 1; i <= n; ++i) {
            if (used[index][i] == 0) {
                ban(index, i);
                dfs(index + 1, cnt);
                pick(index, i);
            }
        }
    }

    public static void ban(int index, int col) {
        for (int i = 1; i <= n; ++i) {
            used[i][col] += 1;
            used[index][i] += 1;
        }
        int[] ope1 = new int[] { 1, -1, 1, -1 };
        int[] ope2 = new int[] { -1, 1, 1, -1 };
        for (int i = 0; i < 4; ++i) {
            int i1 = index, c1 = col;
            while (i1 >= 1 && i1 <= n && c1 >= 1 && c1 <= n) {
                used[i1][c1] += 1;
                i1 += ope1[i];
                c1 += ope2[i];
            }
        }
    }

    public static void pick(int index, int col) {
        for (int i = 1; i <= n; ++i) {
            used[i][col] -= 1;
            used[index][i] -= 1;
        }
        int[] ope1 = new int[] { 1, -1, 1, -1 };
        int[] ope2 = new int[] { -1, 1, 1, -1 };
        for (int i = 0; i < 4; ++i) {
            int i1 = index, c1 = col;
            while (i1 >= 1 && i1 <= n && c1 >= 1 && c1 <= n) {
                used[i1][c1] -= 1;
                i1 += ope1[i];
                c1 += ope2[i];
            }
        }
    }
}

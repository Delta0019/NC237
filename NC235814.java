import java.util.Scanner;

public class NC235814 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();
        input.close();
        dfs(1, 1);
        System.out.println(ans);
    }

    static int n, m;
    static int[] step1 = new int[] { 1, 1, 2, 2 };
    static int[] step2 = new int[] { 2, -2, 1, -1 };
    static int ans = 0;

    static void dfs(int cur_x, int cur_y) {
        if (cur_x == m && cur_y == n) {
            ans++;
            return;
        }
        for (int i = 0; i < 4; ++i) {
            cur_x += step1[i];
            cur_y += step2[i];
            if (cur_x >= 1 && cur_y >= 1 && cur_x <= m && cur_y <= n) {
                dfs(cur_x, cur_y);
            }
            cur_x -= step1[i];
            cur_y -= step2[i];
        }
    }
}

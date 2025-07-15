public class NC15128 {
    public static void main(String[] args) {
        dfs(0);
    }

    static boolean[] used = new boolean[9];
    static int[] path = new int[8];

    public static void dfs(int index) {
        if (index == 8) {
            for (int i = 0; i < 8; ++i) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = 1; i <= 8; ++i) {
                if (!used[i]) {
                    path[index] = i;
                    used[i] = true;
                    dfs(index + 1);
                    used[i] = false;
                }
            }
        }
    }
}

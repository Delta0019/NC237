import java.util.Scanner;

public class Main {
    private static class goods {
        int v, p, q;

        goods(int v, int p, int q) {
            this.v = v;
            this.p = p;
            this.q = q;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        goods[] goods_list = new goods[m + 10];
        for (int i = 1; i <= m; ++i) {
            int v = input.nextInt();
            int p = input.nextInt();
            int q = input.nextInt();
            goods_list[i] = new goods(v, p, q);
        }
    }
}

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class NC22096 {

    private static PrintWriter OUT = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
            PrintNums(1, n);
            OUT.flush();
        }
        input.close();
    }

    private static void PrintNums(int cur, int n) {
        if (cur > n) {
            return;
        }
        for (int i = 1; i <= cur; ++i) {
            OUT.print(i + " ");
        }
        OUT.println();
        PrintNums(cur + 1, n);
    }
}

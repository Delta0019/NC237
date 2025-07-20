import java.util.HashSet;
import java.util.Scanner;

public class NC24911 {
    static int[][] sd = new int[10][10];
    static boolean[][] blank = new boolean[10][10];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; ++j) {
                sd[i][j] = input.nextInt();
                if (sd[i][j] != 0) {
                    blank[i][j] = true;
                }
            }
        }
        input.close();
        search(0, 0);
    }

    public static void search(int row, int col) {
        if (col == 9) {
            col = 0;
            ++row;
        }

        if (row == 9) {
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    System.out.print(sd[i][j] + " ");
                }
                System.out.println();
            }
            return;
        }

        if (sd[row][col] != 0) {
            search(row, col + 1);
            return;
        }
        HashSet<Integer> range = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            range.add(i + 1);
        }

        int row_index = row / 3;
        int col_index = col / 3;
        for (int i = 0; i < 9; i++) {
            range.remove(sd[row][i]);
            range.remove(sd[i][col]);
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                range.remove(sd[row_index * 3 + i][col_index * 3 + j]);
            }
        }

        for (int num : range) {
            sd[row][col] = num;
            search(row, col + 1);
            sd[row][col] = 0;
        }
    }
}

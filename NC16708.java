import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

// 注意 long
public class NC16708 {
    private static class Point {
        int row, col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            Point point = (Point) obj;
            return row == point.row && col == point.col;
        }
    }

    static HashSet<Point> ban_set = new HashSet<>();

    static HashMap<Point, Long> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int ar = 0, ac = 0;
        int br = input.nextInt(), bc = input.nextInt();
        int mr = input.nextInt(), mc = input.nextInt();
        input.close();

        int[] rowStep = new int[] { -2, -2, -1, -1, 0, 1, 1, 2, 2 };
        int[] colStep = new int[] { -1, 1, -2, 2, 0, -2, 2, -1, 1 };
        for (int i = 0; i < 9; ++i) {
            ban_set.add(new Point(mr + rowStep[i], mc + colStep[i]));
        }
        System.out.println(search(new Point(ar, ac), new Point(br, bc)));
    }

    private static long search(Point cur, Point target) {
        if (map.containsKey(cur)) {
            return map.get(cur);
        }
        if (cur.equals(target)) {
            return 1;
        }
        if (cur.col > target.col || cur.row > target.row || ban_set.contains(cur)) {
            return 0;
        }
        Point new_cur1 = new Point(cur.row + 1, cur.col);
        Point new_cur2 = new Point(cur.row, cur.col + 1);
        long result = search(new_cur1, target) + search(new_cur2, target);
        map.put(cur, result);
        return result;
    }
}

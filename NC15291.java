import java.util.ArrayList;
import java.util.Scanner;

// 首先思考能否用常数时间列出所有可能的取值，而非对于每个数，分别求对应的取值。
public class NC15291 {
    static ArrayList<Long> luck_list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int l = input.nextInt(), r = input.nextInt();
        input.close();

        fill("", 12);
        luck_list.sort((a, b) -> {
            if (a < b) {
                return -1;
            } else {
                return 1;
            }
        });

        long ans = 0;
        int pointer = 0;

        for (int i = l; i <= r; ++i) {
            while (luck_list.get(pointer) < i) {
                pointer++;
            }
            ans += luck_list.get(pointer);
        }
        System.out.println(ans);
    }

    public static void fill(String str, int depth) {
        if (depth == 0) {
            return;
        }

        String str4 = str + '4';
        luck_list.add(Long.parseLong(str4));
        fill(str4, depth - 1);

        String str7 = str + '7';
        luck_list.add(Long.parseLong(str7));
        fill(str7, depth - 1);
    }

}

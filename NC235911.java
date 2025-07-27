import java.util.HashMap;
import java.util.Scanner;

public class NC235911 {
    static int ans = 0;

    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.close();
        System.out.println(search(n));
    }

    static int search(int rest) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (map.containsKey(rest)) {
            return map.get(rest);
        } else {
            int result = search(rest - 1) + search(rest - 2);
            map.put(rest, result);
            return result;
        }
    }
}

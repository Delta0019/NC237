import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NC16810 {
    static HashMap<Integer, Boolean> used = new HashMap<>();
    static ArrayList<Integer> heights = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] height_strs = input.nextLine().split("\\ ");
        input.close();
        for (int i = 0; i < height_strs.length; ++i) {
            int height = Integer.parseInt(height_strs[i]);
            heights.add(height);
            used.put(height, false);
        }
        int system_cnt = 0;
        while (used_cnt < heights.size()) {
            list.clear();
            result.clear();
            search(0);
            for (int i : result) {
                used.put(i, true);
            }
            used_cnt += result.size();
            max_list = max_list > result.size() ? max_list : result.size();
            system_cnt++;
        }
        System.out.println(max_list);
        System.out.println(system_cnt);
    }

    static int max_list = 0;
    static int used_cnt = 0;
    static ArrayList<Integer> list = new ArrayList<>();
    static ArrayList<Integer> result = new ArrayList<>();

    @SuppressWarnings("unchecked")
    static void search(int last_index) {
        for (int i = last_index; i < heights.size(); ++i) {
            int height = heights.get(i);
            int size = list.size();
            if (used.get(height))
                continue;
            if (list.isEmpty() || heights.get(i) < list.get(size - 1)) {
                list.add(heights.get(i));
                used.put(height, true);
                search(i + 1);
                used.put(height, false);
                list.remove(size);
            }
        }
        if (list.size() > result.size()) {
            result = (ArrayList<Integer>) list.clone();
        }
        return;
    }
}

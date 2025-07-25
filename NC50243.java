import java.util.Arrays;
import java.util.Scanner;

// Note: 遇到这类拼凑长度的题目，一定要降序排序，在遍历时优先使用大的，不然会导致WA。
public class NC50243 {
    static int[] sticks;
    static boolean[] used;
    static int n;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        sticks = new int[n];
        used = new boolean[n];

        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            sticks[i] = input.nextInt();
            sum += sticks[i];
            maxLen = Math.max(maxLen, sticks[i]);
        }
        input.close();

        Arrays.sort(sticks);
        for (int i = 0; i < n / 2; i++) {
            int temp = sticks[i];
            sticks[i] = sticks[n - 1 - i];
            sticks[n - 1 - i] = temp;
        }

        for (int target = maxLen; target <= sum; target++) {
            if (sum % target != 0) {
                continue;
            }

            Arrays.fill(used, false);
            int groupCount = sum / target;

            if (canForm(0, 0, target, groupCount)) {
                System.out.println(target);
                return;
            }
        }
    }

    static boolean canForm(int groupIndex, int currentLen, int target, int totalGroups) {
        if (groupIndex == totalGroups) {
            return true;
        }

        if (currentLen == target) {
            return canForm(groupIndex + 1, 0, target, totalGroups);
        }

        for (int i = 0; i < n; i++) {
            if (used[i] || currentLen + sticks[i] > target) {
                continue;
            }

            if (i > 0 && sticks[i] == sticks[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            if (canForm(groupIndex, currentLen + sticks[i], target, totalGroups)) {
                return true;
            }
            used[i] = false;

            if (currentLen == 0) {
                return false;
            }
        }

        return false;
    }
}
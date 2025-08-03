import java.util.*;
import java.io.*;

public class NC235951 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long n = Long.parseLong(input[0]);
        long s = Long.parseLong(input[1]);

        TreeMap<Long, Long> mp = new TreeMap<>();
        mp.put(0L, 0L);

        for (int i = 0; i < n; i++) {
            String[] itemInput = br.readLine().split(" ");
            long t = Long.parseLong(itemInput[0]);
            long v = Long.parseLong(itemInput[1]);

            // Note: 初始将mp中所有的值给予了newMp
            TreeMap<Long, Long> newMp = new TreeMap<>(mp);

            for (Map.Entry<Long, Long> entry : mp.descendingMap().entrySet()) {
                long currentWeight = entry.getKey();
                long currentValue = entry.getValue();

                if (currentWeight + t <= s) {
                    long newWeight = currentWeight + t;
                    long newValue = currentValue + v;

                    newMp.put(newWeight, Math.max(newMp.getOrDefault(newWeight, 0L), newValue));
                }
            }

            mp = newMp;
        }

        long ans = 0;
        for (Map.Entry<Long, Long> entry : mp.entrySet()) {
            ans = Math.max(ans, entry.getValue());
        }

        System.out.println(ans);
    }
}
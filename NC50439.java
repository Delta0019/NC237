import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class NC50439 {

    static class Pair<P, U> {
        public P first;
        public U second;

        public Pair(P first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        HashMap<Integer, Pair<Integer, Integer>> soldiers = new HashMap<>();
        HashSet<Integer> tuan = new HashSet<>();
        PriorityQueue<Pair<Integer, Integer>> pick_s = new PriorityQueue<>((a, b) -> {
            return b.second - a.second;
        });
        PriorityQueue<Pair<Integer, Integer>> minHeap_s = new PriorityQueue<>((a, b) -> {
            return a.second - b.second;
        });
        PriorityQueue<Pair<Integer, Integer>> minHeap_v = new PriorityQueue<>((a, b) -> {
            return a.second - b.second;
        });
        for (int i = 0; i < n; ++i) {
            int v = input.nextInt(), s = input.nextInt();
            pick_s.add(new Pair<>(i, s));
            soldiers.put(i, new Pair<>(v, s));
        }

        long ans = 0;
        long cur_cnt = 0;
        while (!pick_s.isEmpty()) {
            Pair<Integer, Integer> next = pick_s.poll();
            int i = next.first, s = next.second, v = soldiers.get(i).first;
            tuan.add(i);
            minHeap_s.add(new Pair<Integer, Integer>(i, s));
            minHeap_v.add(new Pair<Integer, Integer>(i, v));
            cur_cnt += v;
            while (true) {
                while (true) {
                    if (tuan.contains(minHeap_s.peek().first)) {
                        break;
                    }
                    minHeap_s.poll();
                }
                if (tuan.size() <= minHeap_s.peek().second) {
                    break;
                }
                Pair<Integer, Integer> next_out = minHeap_v.poll();
                tuan.remove(next_out.first);
                cur_cnt -= next_out.second;
            }
            if (cur_cnt > ans) {
                ans = cur_cnt;
            }
        }

        System.out.println(ans);
        input.close();
    }
}
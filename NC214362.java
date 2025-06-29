import java.util.PriorityQueue;
import java.util.Scanner;

public class NC214362 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt(), k = input.nextInt();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; ++i) {
            minHeap.add(input.nextInt());
        }

        for (int i = 0; i < Math.min(k, n); ++i) {
            maxHeap.add(minHeap.poll());
        }

        input.nextLine();
        for (int i = 0; i < m; ++i) {
            String[] strings = input.nextLine().split("\\ ");
            if (strings.length == 1) {
                if (n < k) {
                    System.out.println(-1);
                    continue;
                }
                System.out.println(maxHeap.peek());
            } else if (strings.length == 2) {
                int x = Integer.parseInt(strings[1]);
                if (n < k) {
                    maxHeap.add(x);
                } else if (minHeap.peek() == null || x < minHeap.peek()) {
                    maxHeap.add(x);
                    maxHeap.poll();
                }
                ++n;
            }
        }
        input.close();
    }
}
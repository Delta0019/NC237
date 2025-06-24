import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        PriorityQueue<Long> miniHeap = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            miniHeap.add((long) input.nextInt());
        }
        input.close();

        long result = 0;
        while (miniHeap.size() > 1) {
            long v1 = miniHeap.poll(), v2 = miniHeap.poll();
            long sum = v1 + v2;
            result += sum;
            miniHeap.add(sum);
        }
        System.out.println(result);
    }
}
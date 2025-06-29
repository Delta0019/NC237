import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class NC50940 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);
    static PrintWriter OUT = new PrintWriter(new BufferedOutputStream(System.out));

    public static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }

    public static double nextDouble() throws Exception {
        st.nextToken();
        return st.nval;
    }

    public static String nextStr() throws Exception {
        st.nextToken();
        return st.sval;
    }

    public static String nextLine() throws Exception {
        return br.readLine();
    }

    public static void main(String[] args) throws Exception {
        int p = nextInt();
        for (int t = 0; t < p; ++t) {
            int index = nextInt();
            int m = nextInt();
            OUT.println(index + " " + (m + 1) / 2);

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
            ArrayList<Integer> outputs = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                int next = nextInt();
                minHeap.add(next);
                if (i % 2 == 0) {
                    while (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                        minHeap.add(maxHeap.poll());
                        maxHeap.add(minHeap.poll());
                    }
                    maxHeap.add(minHeap.poll());
                    outputs.add(maxHeap.peek());
                }
            }

            for (int i = 0; i < (m + 1) / 2; ++i) {
                if (i > 0 && i % 10 == 0) {
                    OUT.println();
                }
                OUT.print(outputs.get(i) + " ");
            }
            OUT.println();
        }
        OUT.flush();
    }
}
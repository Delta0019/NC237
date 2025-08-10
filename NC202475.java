import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class NC202475 {
  static int N = 100005;
  static ArrayList<ArrayList<Integer>> children = new ArrayList<>();
  static int[] values = new int[N + 1];
  static long ans = Long.MIN_VALUE;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();

    for (int i = 0; i <= n; ++i) {
      children.add(new ArrayList<>());
    }

    for (int i = 1; i <= n; ++i) {
      int value = input.nextInt();
      values[i] = value;
    }

    for (int i = 0; i < n - 1; ++i) {
      int a = input.nextInt(), b = input.nextInt();
      children.get(a).add(b);
      children.get(b).add(a);
    }
    input.close();

    solve(1, 1);
    System.out.println(ans);
  }

  static long solve(int root, int parent) {
    children.get(root).removeIf(a -> a == parent);
    if (children.get(root).isEmpty()) {
      ans = Math.max(ans, values[root]);
      return values[root];
    }

    PriorityQueue<Long> heap = new PriorityQueue<>((a, b) -> (int) (b - a));
    for (int child : children.get(root)) {
      heap.add(solve(child, root));
    }
    long first = (heap.isEmpty() || heap.peek() < 0) ? 0 : heap.poll();
    long second = (heap.isEmpty() || heap.peek() < 0) ? 0 : heap.peek();
    long maxn = first + second + values[root];
    if (maxn > ans) {
      ans = maxn;
    }

    long result = (first + values[root]) > 0 ? first + values[root] : 0;
    return result;
  }
}

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

// 注意处理游离点，即将点加入集合中时，需要手动遍历到而非自动遍历(即通过后继来访问)
// 遍历 PriorityQueue 并非顺序访问，只有不断 pop() 才是顺序访问
public class NC236175 {
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

  static final int N = 100005;
  static int[] times = new int[N];
  static int[] start_times = new int[N];
  static int[] anti_start_times = new int[N];
  static int[] prev_cnt = new int[N];
  static int[] anti_prev_cnt = new int[N];

  static ArrayList<ArrayList<Integer>> post = new ArrayList<>();
  static ArrayList<ArrayList<Integer>> anti_post = new ArrayList<>();

  static PriorityQueue<Integer> cando = new PriorityQueue<>((a, b) -> times[a] - times[b]);
  static PriorityQueue<Integer> todo = new PriorityQueue<>((a, b) -> times[a] - times[b]);

  static PriorityQueue<Integer> tasks = new PriorityQueue<>();

  public static void main(String[] args) throws Exception {
    int n = nextInt(), m = nextInt();
    for (int i = 0; i <= n; ++i) {
      post.add(new ArrayList<>());
      anti_post.add(new ArrayList<>());
    }
    for (int i = 1; i <= n; ++i) {
      times[i] = nextInt();
    }
    for (int i = 0; i < m; ++i) {
      int u = nextInt(), v = nextInt();
      post.get(u).add(v);
      prev_cnt[v]++;
      anti_post.get(v).add(u);
      anti_prev_cnt[u]++;
    }

    for (int i = 1; i <= n; ++i) {
      if (prev_cnt[i] == 0) {
        todo.add(i);
      }
    }

    long total_time = 0, total_done = 0;
    while (!todo.isEmpty()) {
      cando = todo;
      todo = new PriorityQueue<>((a, b) -> times[a] - times[b]);
      for (int task : cando) {
        total_time = Math.max(total_time, start_times[task] + times[task]);
        total_done++;

        for (int post_task : post.get(task)) {
          prev_cnt[post_task]--;
          start_times[post_task] = Math.max(start_times[task] + times[task], start_times[post_task]);
          if (prev_cnt[post_task] == 0) {
            todo.add(post_task);
          }
        }
      }
    }

    if (total_done != n) {
      System.out.println(-1);
      return;
    }

    cando = new PriorityQueue<>((a, b) -> times[a] - times[b]);
    todo = new PriorityQueue<>((a, b) -> times[a] - times[b]);
    for (int i = 1; i <= n; ++i) {
      if (anti_prev_cnt[i] == 0) {
        todo.add(i);
      }
    }

    int ans = 0;
    PriorityQueue<Integer> ans_list = new PriorityQueue<>();
    while (!todo.isEmpty()) {
      cando = todo;
      todo = new PriorityQueue<>((a, b) -> times[a] - times[b]);

      for (int task : cando) {
        if (total_time - anti_start_times[task] - times[task] > start_times[task]) {
          ans++;
          ans_list.add(task);
        }
        for (int anti_post_task : anti_post.get(task)) {
          anti_prev_cnt[anti_post_task]--;
          anti_start_times[anti_post_task] = Math.max(anti_start_times[task] + times[task],
              anti_start_times[anti_post_task]);

          if (anti_prev_cnt[anti_post_task] == 0) {
            todo.add(anti_post_task);
          }

        }
      }
    }

    System.out.println(ans);
    while (!ans_list.isEmpty()) {
      System.out.print(ans_list.poll() + " ");
    }
    System.out.println();
  }
}

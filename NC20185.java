import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class NC20185 {
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

    static class Pair<T, U> {
        public T first;
        public U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) throws Exception {
        int n = nextInt(), m = nextInt();
        PriorityQueue<Pair<Integer, Integer>> cacheMaxHeap = new PriorityQueue<>((a, b) -> b.second - a.second);
        HashSet<Integer> cacheSet = new HashSet<>();
        HashMap<Integer, LinkedList<Integer>> nexti = new HashMap<>();

        ArrayList<Integer> access = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int next = nextInt();
            access.add(next);
            if (!nexti.containsKey(next)) {
                nexti.put(next, new LinkedList<Integer>());
            }
            nexti.get(next).addLast(i);
        }

        for (LinkedList<Integer> list : nexti.values()) {
            list.addLast(Integer.MAX_VALUE);
        }

        int ans = 0;
        for (int cur_access = 0; cur_access < n; ++cur_access) {
            int nextAccess = access.get(cur_access);
            if (!cacheSet.contains(nextAccess)) {
                if (cacheSet.size() == m) {
                    while (true) {
                        Pair<Integer, Integer> peek = cacheMaxHeap.poll();
                        if (cacheSet.contains(peek.first) && nexti.get(peek.first).getFirst() == peek.second) {
                            cacheSet.remove(peek.first);
                            break;
                        }
                    }
                }
                ++ans;
                cacheSet.add(nextAccess);
            }
            nexti.get(nextAccess).removeFirst();
            cacheMaxHeap.add(new Pair<Integer, Integer>(nextAccess, nexti.get(nextAccess).peekFirst()));
        }
        OUT.println(ans);
        OUT.flush();
    }
}
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class fastRW {
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

    // 复制以上代码，并在原代码结尾附上 OUT.flush()即可。以下是一些模板
    public static void main(String[] args) throws Exception {
        readInt();
        // readStr();
        // readLine();
    }

    public static void readInt() throws Exception {
        // 读取整型数
        int n_int = nextInt();
        int list_int[] = new int[n_int];
        for (int i = 0; i < n_int; i++) {
            list_int[i] = nextInt();
        }
        for (int i = 0; i < n_int; i++) {
            OUT.println(list_int[i]);
        }
        OUT.flush();
    }

    public static void readStr() throws Exception {
        // 读取字符串
        int n_str = nextInt();
        String list_str[] = new String[n_str];
        for (int i = 0; i < n_str; i++) {
            list_str[i] = nextStr();
        }
        for (int i = 0; i < n_str; i++) {
            OUT.println(list_str[i]);
        }
        OUT.flush();
    }

    public static void readLine() throws Exception {
        // 读取下一行
        int n_line = nextInt();
        br.readLine();// 只读了第一行中的数字，要把第一行空出去
        String list_line[] = new String[n_line];
        for (int i = 0; i < n_line; i++) {
            list_line[i] = nextLine();
        }
        for (int i = 0; i < n_line; i++) {
            OUT.println(list_line[i]);
        }
        OUT.flush();
    }

}
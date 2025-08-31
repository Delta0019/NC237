import java.util.HashSet;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int m = input.nextInt(), n = input.nextInt(), k = input.nextInt();
    long ans = 0;
    HashSet<Integer> attacks = new HashSet<>();
    for (int i = 0; i < m; ++i) {
      int attack = input.nextInt();
      attacks.add(attack);
    }
    for (int i = 0; i < n; ++i) {
      int stone = input.nextInt();
      int need = k - stone;
      if (attacks.contains(need)) {
        ans++;
      }
    }
    System.out.println(ans);
  }
}

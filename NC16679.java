import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class NC16679 {
  static class Edge {
    int end;
    int weight;

    Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
  }

  static class Node {
    int id;
    long c, u;

    Node(int id, long c, long u) {
      this.id = id;
      this.c = c;
      this.u = u;
    }
  }

  // 0: c, 1: u
  static long[][] nodes = new long[205][2];
  static ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
  static ArrayList<Integer> inputNodes = new ArrayList<>();
  static boolean[] visited = new boolean[205];
  static LinkedList<Integer> list = new LinkedList<>();
  static ArrayList<Node> results = new ArrayList<>();

  static void go() {
    while (!list.isEmpty()) {
      int node = list.removeFirst();
      if (edges.get(node).isEmpty()) {
        if (nodes[node][0] > 0) {
          results.add(new Node(node, nodes[node][0], nodes[node][1]));
        }
        continue;
      }
      for (Edge edge : edges.get(node)) {
        int end = edge.end;
        if (nodes[node][0] > 0) {
          nodes[end][0] += edge.weight * nodes[node][0];
        }
        if (!visited[end]) {
          list.add(end);
          visited[end] = true;
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt(), p = input.nextInt();
    for (int i = 1; i <= n; ++i) {
      int c = input.nextInt(), u = input.nextInt();
      nodes[i][0] = c;
      nodes[i][1] = u;
      if (c != 0) {
        list.add(i);
      } else {
        nodes[i][0] -= u;
      }
    }
    for (int i = 0; i <= n; ++i) {
      edges.add(new ArrayList<>());
    }
    for (int i = 0; i < p; ++i) {
      int a = input.nextInt(), b = input.nextInt(), w = input.nextInt();
      edges.get(a).add(new Edge(b, w));
    }
    input.close();

    go();
    Collections.sort(results, (a, b) -> a.id - b.id);
    for (Node result : results) {
      System.out.println(result.id + " " + result.c);
    }
    if (results.isEmpty()) {
      System.out.println("NULL");
    }
  }
}

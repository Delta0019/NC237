#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 1e5 + 5, M = 5e5 + 5, V = 1e4 + 5;
const int maxn = 0x3f3f3f3f;

struct Edge
{
  int to, w;

  Edge(int to, int w) : to(to), w(w) {};
};

struct Node
{
  int id, dis;

  Node(int id, int dis) : id(id), dis(dis) {};

  bool operator<(const Node &other) const
  {
    return dis > other.dis;
  }
};

int n, m, ans = 0;
int a, b, v;
int vis[N];
int dis[N];
vector<vector<Edge>> graph(N, vector<Edge>());
priority_queue<Node> pq;

void solve()
{
  memset(dis, 127, sizeof(dis));
  dis[1] = 0;
  pq.push({1, 0});
  while (!pq.empty())
  {
    Node node = pq.top();
    pq.pop();

    if (vis[node.id])
      continue;
    vis[node.id] = true;
    ans += node.dis;

    int next = 0, dis = maxn;
    for (Edge edge : graph[node.id])
    {
      if (vis[edge.to])
        continue;

      pq.push({edge.to, edge.w});
    }
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m;
  for (int i = 0; i < m; ++i)
  {
    cin >> a >> b >> v;
    graph[a].push_back({b, v});
    graph[b].push_back({a, v});
  }

  solve();

  cout << ans << endl;

  return 0;
}
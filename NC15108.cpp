#include <bits/stdc++.h>
using namespace std;
const int C = 1e6 + 5, N = 1e4 + 5, M = 1e2 + 5;

struct Edge
{
  int to, w;

  Edge(int to, int w) : to(to), w(w) {};

  bool operator<(const Edge &other) const
  {
    return w > other.w;
  }
};

int c, n, m, ans = 0, cnt = 0;
int v1, v2, h;
vector<vector<Edge>> graph(N, vector<Edge>());
priority_queue<Edge> pq;
int dis[N];
int vis[N];

void solve()
{
  memset(dis, 127, sizeof(dis));
  dis[1] = 0;
  pq.push({1, 0});

  while (!pq.empty())
  {
    int node = pq.top().to;
    int w = pq.top().w;
    pq.pop();

    if (vis[node])
      continue;
    vis[node] = true;

    cnt++;
    ans += w;

    for (Edge edge : graph[node])
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

  cin >> c >> n >> m;
  for (int i = 0; i < n; ++i)
  {
    cin >> v1 >> v2 >> h;
    graph[v1].push_back({v2, h});
    graph[v2].push_back({v1, h});
  }

  solve();

  cout << (cnt == m && ans <= c ? "Yes" : "No") << endl;

  return 0;
}
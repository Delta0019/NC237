#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 1e5 + 5, M = 1e6 + 5;

struct Edge
{
  int to, w, h;

  Edge(int to, int w, int h) : to(to), w(w), h(h) {};

  bool operator<(const Edge &other) const
  {
    if (h == other.h)
      return w > other.w;
    return h < other.h;
  }
};

int n, m;
int hi, ui, vi, ki;
int len = 0, cnt = 0;
int h[N], vis[N];
vector<vector<Edge>> graph(N, vector<Edge>());
priority_queue<Edge> pq;

void solve()
{
  pq.push({1, 0, h[1]});

  while (pq.size())
  {
    int node = pq.top().to;
    int w = pq.top().w;
    pq.pop();

    if (vis[node])
      continue;
    vis[node] = 1;
    // cout << "node: " << node << endl;

    cnt++;
    len += w;

    for (Edge edge : graph[node])
    {
      if (vis[edge.to])
        continue;

      // cout << "to: " << edge.to << endl;
      pq.push({edge.to, edge.w, h[edge.to]});
    }
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m;
  for (int i = 1; i <= n; ++i)
  {
    cin >> hi;
    h[i] = hi;
  }

  for (int i = 0; i < m; ++i)
  {
    cin >> ui >> vi >> ki;
    if (h[ui] >= h[vi])
      graph[ui].push_back({vi, ki, h[vi]});
    if (h[vi] >= h[ui])
      graph[vi].push_back({ui, ki, h[ui]});
  }

  solve();

  cout << cnt << " " << len << endl;

  return 0;
}
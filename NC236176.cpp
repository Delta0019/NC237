#include <bits/stdc++.h>
using namespace std;
const int N = 1e5 + 10;

struct Node
{
  int index, cost;

  bool operator<(const Node &other) const
  {
    return cost > other.cost;
  }
};

struct sy
{
  int to, next, w;
} edge[N * 20];
int head[N * 3], cnt = 0;
void addEdge(int from, int to, int w)
{
  edge[++cnt].next = head[from];
  edge[cnt].to = to;
  edge[cnt].w = w;
  head[from] = cnt;
};

int n, m, c;
int u, v, w;
int num2l[N];
bool vis[N * 3];
set<int> level[N];
priority_queue<Node> pq;

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m >> c;
  int maxLevel = 0;

  for (int i = 1; i <= n; ++i)
  {
    cin >> num2l[i];
    level[num2l[i]].insert(i);
    maxLevel = max(maxLevel, num2l[i]);
  }

  for (int i = 0; i < m; ++i)
  {
    cin >> u >> v >> w;
    addEdge(u, v, w);
    addEdge(v, u, w);
  }

  for (int i = 1; i <= maxLevel; ++i)
  {
    for (int node : level[i])
    {
      addEdge(node, i + n, 0);
      addEdge(i + 2 * n, node, 0);
    }

    if (i < maxLevel)
    {
      addEdge(i + n, (i + 1) + 2 * n, c);
      addEdge((i + 1) + n, i + 2 * n, c);
    }
  }

  pq.push({1, 0});

  while (!pq.empty())
  {
    Node node = pq.top();
    pq.pop();

    if (vis[node.index])
      continue;
    vis[node.index] = true;

    if (node.index == n)
    {
      cout << node.cost << endl;
      return 0;
    }

    for (int i = head[node.index]; i; i = edge[i].next)
    {
      sy e = edge[i];
      if (vis[e.to])
        continue;
      pq.push({e.to, node.cost + e.w});
    }
  }

  cout << -1 << endl;
  return 0;
}
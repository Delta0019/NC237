#include <bits/stdc++.h>
using namespace std;
#define int long long
const int inf = 0x3f3f3f3f;
const int N = 1e3 + 5;
const int M = 2e4 + 5;

struct sy
{
  int to;
  int w;
  int next;
} edge[M];

struct Node
{
  int number;
  int distance;
};

int n, m, s, t, cnt = 0;
int dis[N], vis[N], head[N];
auto cmp = [](Node a, Node b)
{ return a.distance > b.distance; };
priority_queue<Node, vector<Node>, function<bool(Node, Node)>> pq(cmp);

void add_edge(int a, int b, int v)
{
  edge[++cnt].next = head[a];
  edge[cnt].to = b;
  edge[cnt].w = v;
  head[a] = cnt;
}

void dij(int s)
{
  dis[s] = 0;
  pq.push({s, 0});
  while (pq.size())
  {
    int num = pq.top().number;
    pq.pop();

    if (vis[num])
      continue;
    vis[num] = 1;

    for (int i = head[num]; i; i = edge[i].next)
    {
      int next = edge[i].to;
      int w = edge[i].w;
      if (vis[next])
        continue;

      if (dis[next] > dis[num] + w)
      {
        dis[next] = dis[num] + w;
        pq.push({next, dis[next]});
      }
    }
  }

  cout << (dis[t] >= inf ? -1 : dis[t]) << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m >> s >> t;
  memset(dis, inf, sizeof(dis));

  int a, b, v;
  for (int i = 0; i < m; ++i)
  {
    cin >> a >> b >> v;
    add_edge(a, b, v);
    add_edge(b, a, v);
  }
  dij(s);

  return 0;
}
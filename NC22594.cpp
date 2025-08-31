#include <bits/stdc++.h>
using namespace std;
#define int long long

const int N = 8e2 + 5;
const int M = 8e3 + 5;
const int K = 15;
const int inf = 0x3f3f3f3f * 2;

struct Node
{
  int num;
  int distance;
  int attack;

  bool operator<(const Node &node) const
  {
    if (distance == node.distance)
      return attack > node.attack;
    return distance > node.distance;
  }
};

struct prev_star
{
  int to;
  int cost;
  int next;
} edges[M];

int n, m, k, star_cnt = 0;
int dangerous[N], star_head[N], dis[N][K], vis[N][K];
priority_queue<Node> pq;

void add_edge(int x, int y, int w)
{
  edges[++star_cnt].next = star_head[x];
  edges[star_cnt].to = y;
  edges[star_cnt].cost = w;
  star_head[x] = star_cnt;
}

void dij()
{
  memset(dis, 127, sizeof(dis));
  dis[1][0] = 0;
  pq.push({1, 0, 0});

  while (pq.size())
  {
    Node node = pq.top();
    pq.pop();

    if (node.attack > k || vis[node.num][node.attack])
      continue;

    // cout << "next: " << node.num << endl;

    vis[node.num][node.attack] = 1;

    for (int i = star_head[node.num]; i; i = edges[i].next)
    {
      prev_star edge = edges[i];
      int attack = node.attack + dangerous[node.num];

      if (vis[edge.to][attack] || attack > k)
        continue;

      if (dis[node.num][node.attack] + edge.cost < dis[edge.to][attack])
      {
        dis[edge.to][attack] = dis[node.num][node.attack] + edge.cost;

        // cout << "  --edge: " << edge.to << ", attack: " << attack << ", dis: " << dis[edge.to][attack] << endl;

        pq.push({edge.to, dis[edge.to][attack], attack});
      }
    }
  }

  int ans = inf;
  for (int i = 0; i <= k; ++i)
  {
    ans = min(ans, dis[n][i]);
  }
  ans = ans >= inf ? -1 : ans;
  std::cout << ans << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), std::cout.tie(0);

  cin >> n >> m >> k;

  for (int i = 1; i <= n; ++i)
  {
    cin >> dangerous[i];
  }

  int u, v, w;
  for (int i = 0; i < m; ++i)
  {
    cin >> u >> v >> w;
    add_edge(u, v, w);
    add_edge(v, u, w);
  }
  dij();

  return 0;
}
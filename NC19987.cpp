#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 1500 + 5;
const int M = 5000 + 5;
const int mod = 1000000007;

int n, m;
int u, v, w;

struct Node
{
  int to, dis;

  auto operator<(const Node &other) const
  {
    return dis > other.dis;
  }

  Node(int to, int dis) : to(to), dis(dis) {};
};

struct Edge
{
  int to, w, id;

  Edge(int to, int w, int id) : to(to), w(w), id(id) {};
};

vector<vector<Edge>> edges(N, vector<Edge>());
int ans[M];
vector<pair<int, int>> edges_node(M);

// need to clear
vector<vector<Edge>> redges(N, vector<Edge>());
priority_queue<Node> min_pq;
bool vis[N];
int dis[N];
int in_cnt[N];
int cnt1[N], cnt2[N];

void top_sort()
{
  priority_queue<Node> zero_pq;
  for (int i = 1; i <= n; ++i)
  {
    for (auto redge : redges[i])
      in_cnt[redge.to]++;
  }

  for (int i = 1; i <= n; ++i)
  {
    if (in_cnt[i] == 0)
      zero_pq.push({i, 0});
  }

  while (!zero_pq.empty())
  {
    auto node = zero_pq.top();
    zero_pq.pop();

    for (auto redge : redges[node.to])
    {
      cnt2[redge.to] += cnt2[node.to];
      in_cnt[redge.to]--;
      if (in_cnt[redge.to] == 0)
        zero_pq.push({redge.to, 0});
      ans[redge.id] = (ans[redge.id] + (cnt1[edges_node[redge.id].first] * cnt2[edges_node[redge.id].second]) % mod) % mod;
    }
  }
}

void dijkstra()
{
  while (!min_pq.empty())
  {
    Node node = min_pq.top();
    min_pq.pop();

    if (vis[node.to])
      continue;
    vis[node.to] = true;

    cnt2[node.to] = 1;

    // cout << "node: " << node.to << ", dis: " << node.dis << ", dis[i]: " << dis[node.to] << endl;

    for (auto edge : edges[node.to])
    {
      if (dis[edge.to] > dis[node.to] + edge.w)
      {
        dis[edge.to] = dis[node.to] + edge.w;

        cnt1[edge.to] = cnt1[node.to];
        redges[edge.to].clear();
        redges[edge.to].push_back({node.to, 0, edge.id});

        min_pq.push({edge.to, dis[edge.to]});
      }
      else if (dis[edge.to] == dis[node.to] + edge.w)
      {
        cnt1[edge.to] += cnt1[node.to];
        redges[edge.to].push_back({node.to, 0, edge.id});
      }
    }
  }
}

void solve()
{

  for (int i = 1; i <= n; ++i)
  {
    memset(vis, 0, sizeof(vis));
    memset(dis, 0x3f, sizeof(dis));

    fill(cnt1, cnt1 + N, 0);
    fill(cnt2, cnt2 + N, 0);
    redges = vector<vector<Edge>>(N, vector<Edge>());

    dis[i] = 0;
    min_pq.push({i, 0});

    cnt1[i] = 1;
    dijkstra();

    top_sort();
  }

  for (int i = 0; i < m; ++i)
  {
    cout << ans[i] << endl;
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m;
  for (int i = 0; i < m; ++i)
  {
    cin >> u >> v >> w;
    edges[u].push_back({v, w, i});
    edges_node[i] = {u, v};
  }

  solve();

  return 0;
}

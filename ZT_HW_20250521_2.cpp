#include <bits/stdc++.h>
using namespace std;
const int N = 25;

struct Step
{
  int id, dis;

  bool operator<(const Step &other) const
  {
    return dis > other.dis;
  }
};

int n, m;
int graph[N][N], vis[N];

int solve()
{
  priority_queue<Step> pq;
  pq.push({0, 0});

  while (!pq.empty())
  {
    int id = pq.top().id;
    int dis = pq.top().dis;
    pq.pop();

    if (vis[id])
      continue;
    vis[id] = true;

    if (id == m)
      return dis;

    for (int i = 0; i <= n; ++i)
    {
      if (graph[id][i] == 0 || vis[i])
        continue;

      pq.push({i, dis + graph[id][i]});
    }
  }
  return 0;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  for (int i = 0; i <= n; ++i)
    for (int j = 0; j <= n; ++j)
      cin >> graph[i][j];
  cin >> m;

  cout << solve() << endl;

  return 0;
}
#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 55, M = 1e3 + 5;

int n, m;
int u, v, w;
// <to, dis>
vector<vector<pair<int, int>>> edges(N, vector<pair<int, int>>());
vector<vector<pair<int, int>>> redges(N, vector<pair<int, int>>());
// <dis, edge_cnt>
pair<int, int> dp[N][N];

bool valid(int x, int y)
{
  return dp[x][y].second > 0;
}

void floyd()
{
  for (int k = 1; k <= n; ++k)
  {
    for (int i = 1; i <= n; ++i)
    {
      for (int j = 1; j <= n; ++j)
      {
        if (!valid(i, k) || !valid(k, j))
          continue;

        if (!valid(i, j))
        {
          dp[i][j] = {dp[i][k].first + dp[k][j].first,
                      dp[i][k].second + dp[k][j].second};
          continue;
        }

        double new_avg = 1.0 * (dp[i][k].first + dp[k][j].first) /
                         (dp[i][k].second + dp[k][j].second);
        double old_avg = 1.0 * dp[i][j].first / dp[i][j].second;
        if (old_avg > new_avg)
        {
          dp[i][j] = {dp[i][k].first + dp[k][j].first,
                      dp[i][k].second + dp[k][j].second};
        }
      }
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
    cin >> u >> v >> w;
    edges[u].push_back({v, w});
    redges[v].push_back({u, w});
    if (dp[u][v].second == 0 || w < dp[u][v].first)
      dp[u][v] = {w, 1};
  }
  floyd();

  int q;
  cin >> q;
  while (q--)
  {
    int x, y;
    cin >> x >> y;
    if (dp[x][y].second != 0)
    {
      // cout << "x: " << x << ", y: " << y << ", first: " << dp[x][y].first << ", second: " << dp[x][y].second << endl;
      double avg = 1.0 * dp[x][y].first / dp[x][y].second;
      cout << avg << endl;
    }
    else
    {
      cout << "OMG" << endl;
    }
  }
  return 0;
}
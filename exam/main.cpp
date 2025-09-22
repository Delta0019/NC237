#include <bits/stdc++.h>
using namespace std;
const int N = 105;

struct Node
{
  int cost, row, col;

  bool operator<(const Node &other)
  {
    return cost < other.cost;
  }
};

int m, n, k;
int graph[N][N];
map<int, int> nodes;

int dp[N][N];

void solve()
{
  memset(dp, 127, sizeof(dp));

  dp[0][0] = 0;
  for (int i = 1; i < m; ++i)
  {
    dp[i][0] = dp[i - 1][0] + graph[i - 1][0];
  }

  for (int i = 1; i < n; ++i)
  {
    dp[0][i] = dp[0][i - 1] + graph[0][i - 1];
  }

  for (int i = 2; i < m; ++i)
  {
    for (int j = 2; j < n; ++j)
    {
      dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]);
      dp[i][j] += graph[i][j];
    }
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> m >> n >> k;
  for (int i = 0; i < m; ++i)
  {
    for (int j = 0; j < n; ++j)
    {
      cin >> graph[i][j];
    }
  }

  solve();
  cout << dp[m - 1][n - 1] << endl;

  return 0;
}
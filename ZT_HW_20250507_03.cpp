#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 1e3 + 5;
const int inf = 0x3f3f3f3f;

int n, ans = inf;
int grip[N][N];
int dp0[N][N], dp1[N][N];
int move1[] = {0, -1, 0, 1}, move2[] = {-1, 0, 1, 0};

void updateAns(int r, int c)
{
  if (dp0[r][c] >= inf)
    return;

  int neigh = inf;
  for (int i = 0; i < 4; ++i)
  {
    int x = r + move1[i], y = c + move2[i];
    if (x >= 1 && x <= n && y >= 1 && y <= n && grip[x][y] != 0 && dp1[x][y] < inf)
    {
      neigh = min(neigh, dp1[x][y]);
    }
  }
  int res = max(dp0[r][c], neigh);
  ans = min(ans, res);
}

void solve1()
{
  dp1[n][n] = grip[n][n];
  for (int i = n - 1; i >= 1; --i)
  {
    dp1[i][n] = dp1[i + 1][n] + grip[i][n];
    dp1[n][i] = dp1[n][i + 1] + grip[n][i];
  }

  for (int i = n - 1; i >= 1; --i)
  {
    for (int j = n - 1; j >= 1; --j)
    {
      if (grip[i][j] == 0)
        continue;
      dp1[i][j] = min(dp1[i][j + 1], dp1[i + 1][j]) + grip[i][j];
    }
  }
}

void solve0()
{
  dp0[1][1] = grip[1][1];
  updateAns(1, 1);
  for (int i = 2; i <= n; ++i)
  {
    dp0[i][1] = dp0[i - 1][1] + grip[i][1];
    updateAns(i, 1);
    dp0[1][i] = dp0[1][i - 1] + grip[1][i];
    updateAns(1, i);
  }

  for (int i = 2; i <= n; ++i)
  {
    for (int j = 2; j <= n; ++j)
    {
      dp0[i][j] = min(dp0[i][j - 1], dp0[i - 1][j]) + grip[i][j];
      updateAns(i, j);
    }
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  int input;
  for (int i = 1; i <= n; ++i)
  {
    for (int j = 1; j <= n; ++j)
    {
      cin >> input;
      grip[i][j] = input == 0 ? inf : input;
      dp0[i][j] = inf;
      dp1[i][j] = inf;
    }
  }

  solve1();
  solve0();

  cout << (ans == inf ? -1 : ans) << endl;

  return 0;
}
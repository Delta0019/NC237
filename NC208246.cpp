#include <bits/stdc++.h>
using namespace std;

struct node
{
  int row, col, v, orient;

  bool operator<(const node &other) const
  {
    return v > other.v;
  }
};

int rsteps[] = {-1, 0, 1, 0};
int csteps[] = {0, -1, 0, 1};
const int N = 105;
char g[N][N];
bool vis[N][N];
int v[N][N];
int n, ar, ac, br, bc;
priority_queue<node> pq;

bool valid(int row, int col)
{
  if (row < 0 || row >= n || col < 0 || col >= n || g[row][col] == 'x')
    return false;
  return true;
}

void solve()
{
  while (!pq.empty())
  {
    node next = pq.top();
    pq.pop();

    if (vis[next.row][next.col])
      continue;
    vis[next.row][next.col] = true;

    for (int i = 0; i < 4; ++i)
    {
      if (i == next.orient)
        continue;

      int rcur = next.row + rsteps[i], ccur = next.col + csteps[i];
      while (valid(rcur, ccur))
      {
        if (g[rcur][ccur] == 'B')
        {
          cout << (next.v + 1) << endl;
          return;
        }

        pq.push({rcur, ccur, next.v + 1, i});
        rcur += rsteps[i];
        ccur += csteps[i];
      }
    }
  }
  cout << -1 << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  for (int i = 0; i < n; ++i)
  {
    for (int j = 0; j < n; ++j)
    {
      cin >> g[i][j];
      if (g[i][j] == 'A')
      {
        ar = i;
        ac = j;
      }
      if (g[i][j] == 'B')
      {
        br = i;
        bc = j;
      }
    }
  }

  for (int i = 0; i < 4; ++i)
  {
    int rcur = ar + rsteps[i], ccur = ac + csteps[i];
    while (valid(rcur, ccur))
    {
      if (g[rcur][ccur] == 'B')
      {
        cout << 0 << endl;
        return 0;
      }

      pq.push({rcur, ccur, 0, i});
      rcur += rsteps[i];
      ccur += csteps[i];
    }
  }

  solve();

  return 0;
}
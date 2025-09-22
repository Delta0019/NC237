#include <bits/stdc++.h>
using namespace std;

struct Step
{
  int cnt;
  pair<int, int> pos;
};

int ability, n, m, maxh;
pair<int, int> start, target;
vector<vector<int>> mountain;

vector<vector<int>> vis;
int move1[] = {-1, 0, 1, 0};
int move2[] = {0, -1, 0, 1};

bool Valid(int r, int c)
{
  return r >= 0 && r < n && c >= 0 && c < m;
}

int solve()
{
  queue<Step> q;
  q.push({0, start});

  // bfs
  while (!q.empty())
  {
    pair<int, int> cur = q.front().pos;
    int cnt = q.front().cnt;
    q.pop();
    if (vis[cur.first][cur.second])
      continue;
    vis[cur.first][cur.second] = true;

    if (cur == target)
      return cnt;

    for (int i = 0; i < 4; ++i)
    {
      int nextr = cur.first + move1[i];
      int nextc = cur.second + move2[i];
      if (!Valid(nextr, nextc))
        continue;

      int diff = abs(mountain[nextr][nextc] - mountain[cur.first][cur.second]);
      if (ability < diff || vis[nextr][nextc])
        continue;

      q.push({cnt + 1, {nextr, nextc}});
    }
  }

  return -1;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> ability >> n >> m;
  mountain = vector<vector<int>>(n, vector<int>(m));
  vis = vector<vector<int>>(n, vector<int>(m));
  for (int i = 0; i < n; ++i)
  {
    for (int j = 0; j < m; ++j)
    {
      vis[i][j] = 0;
      cin >> mountain[i][j];
      if (mountain[i][j] == 0)
        start = {i, j};

      if (maxh < mountain[i][j])
      {
        maxh = mountain[i][j];
        target = {i, j};
      }
    }
  }

  cout << solve() << endl;

  return 0;
}
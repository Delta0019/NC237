#include <bits/stdc++.h>
using namespace std;
const int N = 55;

int m, n;
int sr, sc;
string row;
int graph[N][N];
int vis[N][N];
int bug1r, bug1c, bug2r, bug2c;
int mover[] = {-1, 0, 1, 0};
int movec[] = {0, -1, 0, 1};

bool valid(int r, int c)
{
  return r >= 1 && r <= m && c >= 1 && c <= n && graph[r][c] != '1';
}

struct Step
{
  int r, c, dis;

  Step(int r, int c, int dis) : r(r), c(c), dis(dis) {};

  bool operator<(const Step &other) const
  {
    return dis > other.dis;
  }
};

int solve()
{
  priority_queue<Step> pq;
  pq.push({sr, sc, 0});

  while (!pq.empty())
  {
    Step cur = pq.top();
    pq.pop();

    if (!valid(cur.r, cur.c) || vis[cur.r][cur.c])
      continue;
    vis[cur.r][cur.c] = true;

    // cout << "vis, r: " << cur.r << ", c: " << cur.c << ", dis: " << cur.dis << endl;

    if (graph[cur.r][cur.c] == 'E')
    {
      return cur.dis;
    }
    if (graph[cur.r][cur.c] == '2')
    {
      if (cur.r == bug1r && cur.c == bug1c)
        pq.push({bug2r, bug2c, cur.dis});
      else
        pq.push({bug1r, bug1c, cur.dis});
    }

    for (int i = 0; i < 4; ++i)
    {
      pq.push({cur.r + mover[i], cur.c + movec[i], cur.dis + 1});
    }
  }

  return -1;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> m >> n;
  for (int i = 1; i <= m; ++i)
  {
    cin >> row;
    for (int j = 1; j <= n; ++j)
    {
      graph[i][j] = row[j - 1];
      if (graph[i][j] == 'S')
      {
        sr = i;
        sc = j;
      }
      if (graph[i][j] == '2')
      {
        if (bug1r == 0)
        {
          bug1r = i;
          bug1c = j;
        }
        else
        {
          bug2r = i;
          bug2c = j;
        }
      }
    }
  }

  cout << solve() << endl;

  return 0;
}
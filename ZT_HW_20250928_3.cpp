#include <bits/stdc++.h>
using namespace std;
const int N = 6e2 + 5;

struct Node
{
  int x, y, v;
};

int n, m, ans = 0;
size_t cnt = 0;
int graph[N][N];
int cover[12][N][N];
vector<Node> ms;

void parse_machine()
{
  for (int mac = 0; mac < ms.size(); ++mac)
  {
    int x = ms[mac].x, y = ms[mac].y, v = ms[mac].v;
    for (int i = 0; i < n; ++i)
    {
      for (int j = 0; j < m; ++j)
      {
        if (pow(i - x, 2) + pow(j - y, 2) <= pow(v, 2))
        {
          cover[mac][i][j] = 1;
        }
      }
    }
  }
}

void solve()
{
  int choices = 1 << ms.size();
  for (int mask = 1; mask < choices; ++mask)
  {
    double cur_ans = 0;
    double res[N][N];
    memset(res, 0, sizeof(res));

    for (int mac = 0; mac < ms.size(); ++mac)
    {
      if (!((mask >> mac) & 1))
        continue;

      for (int i = 0; i < n; ++i)
      {
        for (int j = 0; j < m; ++j)
        {
          res[i][j] += cover[mac][i][j];
        }
      }
    }

    for (int i = 0; i < n; ++i)
    {
      for (int j = 0; j < m; ++j)
      {
        cur_ans += (res[i][j] == 0 || graph[i][j] <= 0) ? 0 : graph[i][j] / res[i][j];
      }
    }

    cur_ans = (int)cur_ans;
    if (cur_ans > ans)
    {
      ans = cur_ans;
      cnt = bitset<12>(mask).count();
    }
    else if (cur_ans == ans)
    {
      cnt = min(cnt, bitset<12>(mask).count());
    }
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m;
  for (int i = 0; i < n; ++i)
  {
    for (int j = 0; j < m; ++j)
    {
      cin >> graph[i][j];
      if (graph[i][j] < 0)
        ms.push_back({i, j, -graph[i][j]});
    }
  }

  parse_machine();
  solve();

  cout << ans << " " << cnt << endl;

  return 0;
}
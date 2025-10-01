#include <bits/stdc++.h>
using namespace std;
const int N = 105;
const int M = 10;
const int inf = 0x3f3f3f3f;

int n, m, init, ans = 0;
int power[N], mana[N], bonus[M];

void dfs(int idx, int level, int gift, int cost)
{
  gift += power[idx] * bonus[level];
  cost += mana[idx] * bonus[level];

  if (cost > init)
    return;

  ans = max(ans, gift);

  if (idx == n)
    return;

  for (int l = 1; l <= m; ++l)
  {
    int waste = l > level ? (l - level) : 0;
    dfs(idx + 1, l, gift, cost + waste);
  }
}

void solve()
{
  for (int l = 1; l <= m; ++l)
  {
    dfs(1, l, 0, 0);
  }
  cout << ans << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m >> init;
  for (int i = 1; i <= n; ++i)
  {
    cin >> power[i];
  }

  for (int i = 1; i <= n; ++i)
  {
    cin >> mana[i];
  }

  for (int i = 1; i <= m; ++i)
  {
    cin >> bonus[i];
  }

  solve();

  return 0;
}
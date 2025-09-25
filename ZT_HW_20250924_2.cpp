#include <bits/stdc++.h>
using namespace std;
const int N = 25;

int n, m;
vector<int> a(N);
vector<vector<int>> graph(N, vector<int>());
vector<bitset<N>> lights(N);

vector<int> ans;

void transfer()
{
  for (int i = 1; i <= n; ++i)
  {
    bitset<N> bit;
    for (int num : graph[i])
    {
      bit[num] = 1;
    }
    lights[i] = bit;
  }
}

void solve()
{
  bool found = false;

  for (int mask = 0; mask < (1 << n); ++mask)
  {
    bitset<N> cur;
    for (int i = 1; i <= n; ++i)
      cur[i] = (a[i] == 1);

    for (int i = 1; i <= n; ++i)
    {
      if (((mask >> (i - 1)) & 1) == 0)
        continue;
      cur ^= lights[i];
    }

    if (cur.count() == 0)
    {
      vector<int> cand;
      for (int i = 0; i < n; ++i)
        if ((mask >> i) & 1)
          cand.push_back(i + 1);

      if (!found)
      {
        ans = cand;
        found = true;
      }
      else
      {
        if (cand.size() < ans.size())
          ans = cand;
        else if (cand.size() == ans.size() && cand < ans)
          ans = cand;
      }
    }
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m;
  for (int i = 1; i <= n; ++i)
  {
    cin >> a[i];
    graph[i].push_back(i);
  }

  int x, y;
  for (int i = 0; i < m; ++i)
  {
    cin >> x >> y;
    graph[x].push_back(y);
  }

  transfer();
  solve();

  if (ans.empty())
  {
    cout << -1 << endl;
  }
  else
  {
    for (int num : ans)
      cout << num << ' ';
    cout << endl;
  }

  return 0;
}
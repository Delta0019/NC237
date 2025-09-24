#include <bits/stdc++.h>
using namespace std;
const int N = 25;

int n, m;
vector<int> a(N);
vector<vector<int>> graph(N, vector<int>());
vector<bitset<N>> lights(N);

int min_cnt = INT_MAX;
set<int> ans;

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
  for (int mask = 0; mask < (1 << n); ++mask)
  {
    bitset<N> cur;
    for (int i = 1; i <= n; ++i)
      cur[i] = (a[i] == 1);

    for (int i = 1; i <= n; ++i)
    {
      if ((mask >> (i - 1) & 1) == 0)
        continue;

      cur ^= lights[i];
    }

    if (cur.count() == 0)
    {
      bitset<N> res(mask);
      if (res.count() > min_cnt)
        continue;

      if (res.count() == min_cnt)
        ans.insert(mask);

      if (res.count() < min_cnt)
      {
        min_cnt = res.count();
        ans = set<int>();
        ans.insert(mask);
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
    set<string> res;
    for (bitset<N> bit : ans)
    {
      string str = "";
      for (int i = 0; i < n; ++i)
      {
        if (bit[i])
          str += (char)('0' + i + 1);
      }
      res.insert(str);
    }

    auto it = res.begin();
    for (char ch : *it)
    {
      cout << ch << ' ';
    }
    cout << endl;
  }

  return 0;
}
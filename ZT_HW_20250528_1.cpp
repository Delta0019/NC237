#include <bits/stdc++.h>
using namespace std;
const int N = 1e5 + 5;

struct Node
{
  int idx;
  int val;

  bool operator<(const Node &other) const
  {
    if (val == other.val)
      return idx < other.idx;
    return val < other.val;
  }
};

int k, n;
int cross[N], ans[N];
multiset<Node> wnd;

void solve()
{
  int back = 2 * k - 1;
  for (int i = 0; i < n + k; ++i)
  {
    if (i < n)
      wnd.insert({i, cross[i]});

    if (i >= k)
      ans[i - k] = wnd.begin()->idx;

    if (i >= back)
      wnd.erase({(i - back), cross[i - back]});
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  string line;
  getline(cin, line);
  cin >> k;
  stringstream ss(line);

  string str;
  while (getline(ss, str, ' '))
  {
    cross[n++] = stoi(str);
  }

  solve();
  for (int i = 0; i < n - 1; ++i)
  {
    cout << ans[i] << endl;
  }

  return 0;
}
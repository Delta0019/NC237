#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 2e5 + 10;

int n;
int a[N], w[N];
auto cmp = [](int x, int y)
{
  return a[x] < a[y];
};
multiset<int, decltype(cmp)> papers(cmp);

map<int, int> total;

bool check(int x)
{
  auto it = total.lower_bound(x);
  // cout << "x: " << x << endl;
  // cout << "it: " << it->first << ", " << it->second << endl;
  if (it->second >= x)
  {
    return true;
  }
  return false;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  int T;
  cin >> T;
  while (T--)
  {
    papers.clear();
    total.clear();
    int left = 0, right = 0, ans = 0;
    cin >> n;
    for (int i = 0; i < n; ++i)
    {
      cin >> a[i];
      right = max(a[i], right);
      papers.insert(i);
    }
    for (int i = 0; i < n; ++i)
    {
      cin >> w[i];
    }

    int cnt = 0;
    for (auto rit = papers.rbegin(); rit != papers.rend(); ++rit)
    {
      cnt += w[*rit];
      total[a[*rit]] = cnt;
      // cout << "rit: " << a[*rit] << ", cnt: " << cnt << endl;
    }

    while (left <= right)
    {
      int mid = left + (right - left) / 2;
      // cout << "mid: " << mid << endl;
      if (check(mid))
      {
        ans = mid;
        left = mid + 1;
      }
      else
      {
        right = mid - 1;
      }
    }

    cout << ans << endl;
  }

  return 0;
}
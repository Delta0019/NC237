#include <bits/stdc++.h>
using namespace std;
#define int long long

const int N = 2e5 + 5;
int n;

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  int T;
  cin >> T;
  while (T--)
  {
    cin >> n;
    int a[n + 5], b[n + 5], ans = 0;
    map<int, int> map_cnt;
    for (int i = 1; i <= n; ++i)
    {
      cin >> a[i];
      b[i] = a[i] - i;
      map_cnt[b[i]]++;
    }

    map<int, int> prev_sum;
    int cnt = 0;
    for (auto it = map_cnt.rbegin(); it != map_cnt.rend(); ++it)
    {
      cnt += it->second;
      prev_sum[it->first] = cnt;
    }

    for (int i = 1; i <= n; ++i)
    {
      auto match = prev_sum.upper_bound(-b[i]);
      if (match != prev_sum.end())
      {
        ans += match->second;

        if (b[i] * 2 > 0)
          ans--;
      }
    }
    ans /= 2;

    cout << ans << endl;
  }

  return 0;
}
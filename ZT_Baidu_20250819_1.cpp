#include <bits/stdc++.h>
using namespace std;

const int N = 1e5 + 5;
int n;
int a[N], b[N], pre[N], suf[N];

int solve(int i)
{
  return (a[i - 1] == b[i - 1] || a[i + 1] == b[i + 1] || a[i - 1] == a[i + 1] || b[i - 1] == b[i + 1]) ? 1 : 0;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  int T;
  cin >> T;
  while (T--)
  {
    int ans = 0;
    cin >> n;
    for (int i = 1; i <= n; ++i)
      cin >> a[i];
    for (int i = 1; i <= n; ++i)
    {
      cin >> b[i];
      if (a[i] == b[i])
        ans++;
    }

    for (int i = 1; i <= n; ++i)
    {
      int x = a[i] == b[i], y, z, t;
      if (i < n)
      {
        y = a[i] == a[i + 1];
        z = b[i] == b[i + 1];
        t = a[i + 1] == b[i + 1];
      }
      else
      {
        y = z = t = 0;
      }
      pre[i] = max({x, y, z, t});
      pre[i] += (i > 0 ? pre[i - 1] : 0);
    }

    for (int i = n; i >= 1; --i)
    {
      int x = a[i] == b[i], y, z, t;
      if (i < n)
      {
        y = a[i] == a[i + 1];
        z = b[i] == b[i + 1];
        t = a[i + 1] == b[i + 1];
      }
      else
      {
        y = z = t = 0;
      }
      suf[i] = max({x, y, z, t});
      suf[i] += (i > 0 ? suf[i + 1] : 0);
    }

    // delete i = 1;
    ans = max(ans, suf[2]);
    // delete i = 2
    ans = max(ans, solve(2) + suf[3]);
    // delete i = n
    ans = max(ans, pre[n - 1]);

    for (int i = 3; i <= n - 1; ++i)
    {
      ans = max(ans, pre[i - 2] + solve(i) + suf[i + 1]);
    }

    cout << ans << endl;
  }

  return 0;
}
#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 1e5 + 5;

int n, k;
int scores[N];
int dp[N];
multiset<int> wnd;

void solve()
{
  for (int i = 0; i <= n; ++i)
  {
    dp[i] = INT_MIN;
  }

  dp[1] = scores[1];
  wnd.insert(dp[1]);
  for (int i = 2; i <= n; ++i)
  {
    if ((i - k) > 1)
      wnd.erase(dp[i - k - 1]);

    dp[i] = *wnd.rbegin() + scores[i];
    // cout << "rbegin: " << *wnd.rbegin() << endl;
    //  cout << "dp[" << i << "]: " << dp[i] << endl;
    wnd.insert(dp[i]);
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> k >> n;
  for (int i = 1; i <= n; ++i)
  {
    cin >> scores[i];
  }

  solve();

  cout << dp[n] << endl;

  return 0;
}
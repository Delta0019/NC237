#include <bits/stdc++.h>
using namespace std;

int l, r, ans;

int getOddCnt(int end)
{
  return end - end / 3;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  int T;
  cin >> T;
  while (T--)
  {
    cin >> l >> r;

    ans = 0;
    ans = getOddCnt(r) - getOddCnt(l) + (l % 3 == 0 ? 0 : 1);
    cout << ans << endl;
  }
  return 0;
}
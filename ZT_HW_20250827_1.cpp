#include <bits/stdc++.h>
using namespace std;

const int N = 1e5 + 5;
int n, ans = 0;
int temp[N];
set<int> res;

bool check(int len)
{
  bool flag = false;
  multiset<int> wnd;
  set<int> temp_res;
  for (int i = 1; i <= n; ++i)
  {
    wnd.insert(temp[i]);
    if (wnd.size() > len)
    {
      auto it = wnd.find(temp[i - len]);
      wnd.erase(it);
    }

    if (wnd.size() < len)
      continue;

    if (*wnd.begin() >= 18 && *wnd.rbegin() <= 24 && (*wnd.rbegin() - *wnd.begin()) <= 4)
    {
      flag = true;
      temp_res.insert(i);
    }
  }

  if (flag)
  {
    ans = len;
    res = temp_res;
  }
  return flag;
}

void solve()
{
  int left = 1, right = n, tmp;
  while (left <= right)
  {
    int mid = left + (right - left) / 2;
    if (check(mid))
    {
      tmp = mid;
      left = mid + 1;
    }
    else
    {
      right = mid - 1;
    }
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);
  cin >> n;
  for (int i = 1; i <= n; ++i)
  {
    cin >> temp[i];
  }

  solve();

  for (int i : res)
  {
    cout << (i - ans) << " " << (i - 1) << endl;
  }

  return 0;
}
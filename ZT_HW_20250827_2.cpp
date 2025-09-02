#include <bits/stdc++.h>
using namespace std;

const int N = 25;
int n, m, avg;
int a[N], prev_sum[N];

pair<int, list<int>> solve(int s, int grps)
{
  int ans = INT_MAX, pick;
  list<int> ans_list;

  if (grps == 1)
  {
    int num = pow(prev_sum[n] - prev_sum[s - 1] - avg, 2);
    ans_list.push_front(n - s + 1);
    return {num, ans_list};
  }

  for (int i = s; grps - 1 <= n - i; ++i)
  {
    int num = pow(prev_sum[i] - prev_sum[s - 1] - avg, 2);
    auto res = solve(i + 1, grps - 1);

    // cout << "s: " << s << ", i: " << i << ", num: " << num << endl;
    // cout << "  --post_group: " << grps - 1 << ", res" << res.first << endl;

    if (num + res.first < ans)
    {
      pick = i - s + 1;
      ans = num + res.first;
      ans_list = res.second;
    }
  }
  ans_list.push_front(pick);
  return {ans, ans_list};
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m;
  for (int i = 1; i <= n; ++i)
  {
    cin >> a[i];
    prev_sum[i] = prev_sum[i - 1] + a[i];
    avg += a[i];
  }
  avg /= m;

  auto res = solve(1, m);
  for (int i : res.second)
  {
    cout << i << " ";
  }
  cout << endl;

  return 0;
}
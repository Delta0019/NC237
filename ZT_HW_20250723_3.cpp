#include <bits/stdc++.h>
using namespace std;

int m;
vector<int> nums;

void solve()
{
  int prev = nums[0], index = 1, ans = 0;

  while (index < m && prev > nums[index])
  {
    prev = nums[index++];
    continue;
  }

  while (index < m)
  {
    int min_num = prev, max_num = nums[index];
    while (index < m && prev <= nums[index])
    {
      max_num = nums[index];
      if (prev == nums[index])
      {
        ans = max(ans, max_num - min_num);
      }
      prev = nums[index++];
    }

    while (index < m && prev >= nums[index])
    {
      min_num = min(min_num, nums[index]);
      ans = max(ans, max_num - min_num);
      prev = nums[index++];
    }
  }

  cout << ans << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> m;
  int num;
  for (int i = 0; i < m; ++i)
  {
    cin >> num;
    nums.push_back(num);
  }

  solve();

  return 0;
}
#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 1e5 + 5;

int r, k, n, sum;
vector<int> diffs;

bool check(int target)
{
  vector<int> diff = diffs;
  int cur = 0, rest = k;
  for (int i = 0; i < n; ++i)
  {
    cur += diff[i];
    if (cur >= target)
      continue;

    int todo = target - cur;
    if (rest < todo)
      return false;

    rest -= todo;
    cur += todo;
    diff[i] += todo;
    diff[i + 2 * r + 1] -= todo;
  }
  return true;
}

int solve()
{
  int left = 0, right = sum + k, ans = 0;
  while (left <= right)
  {
    int mid = left + (right - left) / 2;
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
  return ans;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> r >> k >> n;
  vector<int> cities(n);
  diffs = vector<int>(n + 2 * r + 10);
  for (int i = 0; i < n; ++i)
  {
    cin >> cities[i];
    sum += cities[i];
    int left = max((int)0, i - r);
    int right = i + r + 1;
    diffs[left] += cities[i];
    diffs[right] -= cities[i];
  }

  cout << solve() << endl;

  return 0;
}
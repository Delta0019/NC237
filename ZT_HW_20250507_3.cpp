#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 25;
const int inf = 0x3f3f3f3f;

int n, m;
int a[N];
double min_std = inf, avg = 0;
vector<int> cur;
vector<int> cur_cnt;
vector<int> ans;

int cal_std()
{
  int res = 0;
  for (int num : cur)
    res += pow(num - avg, 2);
  return res;
}

void dfs(int idx, int cnt)
{
  if (idx == n && cnt == m)
  {
    int std = cal_std();
    if (std < min_std)
    {
      min_std = std;
      ans = cur_cnt;
      return;
    }
  }

  if (idx >= n)
    return;

  cur.back() += a[idx];
  cur_cnt.back()++;
  dfs(idx + 1, cnt);
  cur.back() -= a[idx];
  cur_cnt.back()--;

  if (cnt == m)
    return;

  cur.push_back(a[idx]);
  cur_cnt.push_back(1);
  dfs(idx + 1, cnt + 1);
  cur.pop_back();
  cur_cnt.pop_back();
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m;
  int sum = 0;
  for (int i = 0; i < n; ++i)
  {
    cin >> a[i];
    sum += a[i];
  }
  avg = 1.0 * sum / m;

  cur.push_back(a[0]);
  cur_cnt.push_back(1);
  dfs(1, 1);

  for (int num : ans)
  {
    cout << num << " ";
  }
  cout << endl;

  return 0;
}
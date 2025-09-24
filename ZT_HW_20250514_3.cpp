#include <bits/stdc++.h>
using namespace std;

vector<int> cars;
vector<int> reqs;

bool getSubSeq(int total)
{
  queue<int> q;
  int max_end = 0, max_cnt = 0;
  int cur = 0;
  for (int i = 0; i < reqs.size(); ++i)
  {
    q.push(reqs[i]);
    cur += reqs[i];

    while (cur > total)
    {
      cur -= q.front();
      q.pop();
    }

    if (q.size() > max_cnt)
    {
      max_end = i;
      max_cnt = q.size();
    }
  }

  if (max_cnt == 0)
    return false;

  auto it1 = reqs.begin() + max_end - max_cnt + 1;
  auto it2 = reqs.begin() + max_end + 1;
  reqs.erase(it1, it2);

  return true;
}

pair<int, int> solve()
{
  int idx = 0, cur = 0, cnt = 0;

  while (reqs.size() > 0)
  {
    if (idx == cars.size())
      return {cnt, reqs.size()};

    cur += cars[idx++];

    if (getSubSeq(cur))
    {
      cnt++;
      cur = 0;
    }
  }
  return {cnt, 0};
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  string line1, line2;
  getline(cin, line1);
  getline(cin, line2);

  istringstream ss1(line1), ss2(line2);
  int x;
  while (ss1 >> x)
    cars.push_back(x);
  while (ss2 >> x)
    reqs.push_back(x);

  auto res = solve();
  cout << res.first << " " << res.second << endl;

  return 0;
}
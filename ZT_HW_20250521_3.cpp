#include <bits/stdc++.h>
using namespace std;

struct Customer
{
  int idx, segnum;
  vector<tuple<int, int, int>> cores;
};

int m, n, r;
vector<Customer> customers;

long long solve()
{
  long long max_rent = 0;
  for (int mask = 1; mask < (1 << n); ++mask)
  {
    long long rent = 0;
    // time, cnt
    map<int, int> schedule;

    for (int i = 0; i < n; ++i)
    {
      if (((mask >> i) & 1) == 0)
        continue;

      Customer cust = customers[i];
      for (auto [start, stop, core] : customers[i].cores)
      {
        schedule[start] += core;
        schedule[stop] -= core;
        rent += (stop - start + 1) * core * r;
      }
    }

    int cur = 0;
    bool flag = true;
    for (auto [t, core] : schedule)
    {
      cur += core;
      if (cur > m)
      {
        flag = false;
        break;
      }
    }

    if (flag)
      max_rent = max(max_rent, rent);
  }

  return max_rent;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> m >> n >> r;
  for (int i = 0; i < n; ++i)
  {
    int segnum;
    Customer customer;
    cin >> segnum;
    customer.idx = i;
    customer.segnum = segnum;

    for (int j = 0; j < segnum; ++j)
    {
      string need;
      cin >> need;
      stringstream ss(need);
      int start, stop, core;
      string str;

      getline(ss, str, ':');
      start = stoi(str);
      getline(ss, str, ':');
      stop = stoi(str);
      getline(ss, str, ':');
      core = stoi(str);

      customer.cores.push_back({start, stop, core});
    }

    customers.push_back(customer);
  }

  cout << solve() << endl;

  return 0;
}
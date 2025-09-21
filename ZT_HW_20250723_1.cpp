#include <bits/stdc++.h>
using namespace std;
const int N = 200 + 5;

int k, m, goods = 0, money = 0;
int cost[N], profit[N], vis[N];

void solve()
{
  for (int day = 0; day < k; ++day)
  {
    int max_profit = 0, goods_index = 0;
    for (int i = 0; i < goods; ++i)
    {
      if (vis[i] || cost[i] > m)
        continue;

      if (profit[i] > max_profit)
      {
        max_profit = profit[i];
        goods_index = i;
      }
    }
    m += max_profit;
    money += max_profit;
    cout << "index: " << goods_index << ", " << "profit: " << max_profit << endl;
    vis[goods_index] = true;
  }

  cout << money << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> k >> m;
  string str;
  cin.ignore();

  {
    string line;
    getline(cin, line);
    stringstream input(line);

    string cs;
    int index = 0;
    while (getline(input, cs, ' '))
    {
      cost[index++] = stoi(cs);
    }
    goods = index;
  }

  {
    string line;
    getline(cin, line);
    stringstream input(line);

    string ps;
    int index = 0;
    while (getline(input, ps, ' '))
    {
      profit[index++] = stoi(ps);
    }
  }

  solve();

  return 0;
}
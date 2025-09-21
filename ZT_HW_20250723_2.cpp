#include <bits/stdc++.h>
using namespace std;
const int N = 105;

queue<int> cars;
queue<int> soldiers;
queue<int> trans;

void solve()
{
  while (!soldiers.empty() && !cars.empty())
  {
    int car = cars.front();
    cars.pop();

    // case3
    while (!soldiers.empty() && car < soldiers.front())
    {
      trans.push(soldiers.front());
      soldiers.pop();
    }

    // case1&2
    while (!soldiers.empty() && car >= soldiers.front())
    {
      car -= soldiers.front();
      soldiers.pop();
    }

    while (!trans.empty())
    {
      soldiers.push(trans.front());
      trans.pop();
    }
  }

  cout << (cars.empty() ? soldiers.size() : trans.size()) << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  {
    string line;
    getline(cin, line);
    stringstream ss(line);
    string cari;
    int index = 0;
    while (getline(ss, cari, ' '))
    {
      cars.push(stoi(cari));
    }
  }

  {
    string line;
    getline(cin, line);
    stringstream ss(line);
    string reqi;
    int index = 0;
    while (getline(ss, reqi, ' '))
    {
      soldiers.push(stoi(reqi));
    }
  }

  solve();

  return 0;
}
#include <bits/stdc++.h>
using namespace std;
const int N = 1e3 + 5;

int n;
map<string, int> cpu;
map<string, int> mem;
map<string, int> board;

bool isNum(char ch)
{
  return ch >= '0' && ch <= '9';
}

void solve(string str)
{
  bool c = false, m = false, b = false;
  for (int i = 0; i < str.length(); ++i)
  {
    switch (str[i])
    {
    case 'C':
      if (c)
        continue;

      if (!c && isNum(str[i + 1]) && isNum(str[i + 2]))
      {
        string id = "";
        id += str.substr(i, 3);
        cpu[id]++;
        c = true;
      }
      break;
    case 'M':
      if (!m && isNum(str[i + 1]) && isNum(str[i + 2]))
      {
        string id = "";
        id += str.substr(i, 3);
        mem[id]++;
        m = true;
      }
      break;
    case 'B':
      if (!b && isNum(str[i + 1]) && isNum(str[i + 2]))
      {
        string id = "";
        id += str.substr(i, 3);
        board[id]++;
        b = true;
      }
      break;
    }

    if (c && m && b)
      break;
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  string hard;
  for (int i = 0; i < n; ++i)
  {
    cin >> hard;
    solve(hard);
  }

  bool flag = false;
  for (auto p : cpu)
  {
    if (flag)
      cout << ";";
    flag = true;
    cout << p.first << "," << p.second;
  }
  cout << endl;

  flag = false;
  for (auto p : mem)
  {
    if (flag)
      cout << ";";
    flag = true;
    cout << p.first << "," << p.second;
  }
  cout << endl;

  flag = false;
  for (auto p : board)
  {
    if (flag)
      cout << ";";
    flag = true;
    cout << p.first << "," << p.second;
  }
  cout << endl;

  return 0;
}
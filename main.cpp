#include <bits/stdc++.h>
using namespace std;
const int N = 3005;

vector<vector<int>> tree(N, vector<int>(2));

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  string line;
  getline(cin, line);
  stringstream ss(line);

  string next;
  queue<int> q;
  // left: 0; right: 1
  int cur = 0;
  while (getline(ss, next, ' '))
  {
    if (next == "N" && q.empty())
    {
      cout << 0 << endl;
      return 0;
    }

    int num = next == "N" ? -1 : stoi(next);

    if (q.empty())
    {
      q.push(num);
      continue;
    }

    tree[q.front()][cur++] = num;

    if (cur == 2)
    {
      q.pop();
      cur = 0;
    }

    if (num != -1)
      q.push(num);
  }

  return 0;
}
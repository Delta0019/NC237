#include <bits/stdc++.h>
using namespace std;
const int M = 105;

struct Node
{
  int first, second;

  bool operator<(const Node &other) const
  {
    int diff1 = second - first;
    int diff2 = other.second - other.first;
    return diff1 < diff2;
  }
};

int m;
priority_queue<Node> pqa;
priority_queue<Node> pqb;

void parse_input()
{
  string line;
  getline(cin, line);
  line = line.substr(1, line.length() - 2);

  stringstream ss(line);
  string array;
  while (getline(ss, array, ']'))
  {
    array = array.substr(array[0] == ',' ? 3 : 1);
    stringstream array_ss(array);

    string num;
    int inputs[2];
    for (int i = 0; i < 2; ++i)
    {
      getline(array_ss, num, ',');
      inputs[i] = stoi(num);
    }

    if (inputs[0] < inputs[1])
      pqa.push({inputs[0], inputs[1]});
    else
      pqb.push({inputs[1], inputs[0]});
  }
}

int solve(priority_queue<Node> &pq)
{
  int ans = 0, cnt = 0;
  while (!pq.empty())
  {
    cnt++;
    if (cnt <= m / 2)
      ans += pq.top().first;
    else
      ans += pq.top().second;
    pq.pop();
  }
  return ans;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> m;
  cin.ignore();
  parse_input();

  int ans = solve(pqa) + solve(pqb);

  cout << ans << endl;

  return 0;
}
#include <bits/stdc++.h>
using namespace std;
const int N = 1e5 + 5;

struct Node
{
  int id, prio;

  bool operator<(const Node &other) const
  {
    if (prio == other.prio)
      return id < other.id;
    return prio > other.prio;
  }
};

map<int, int> nodes;
queue<int> q;

set<Node> s;

int extract()
{
  while (!s.empty() && s.begin()->prio != nodes[s.begin()->id])
  {
    s.erase(s.begin());
  }
  if (s.empty())
    return -1;

  int id = s.begin()->id;
  s.erase(s.begin());
  return id;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  int n;
  char op;
  int id, priority, k;
  bool flag = true;

  cin >> n;

  for (int t = 0; t < n; ++t)
  {
    vector<int> res;
    cin >> op;
    switch (op)
    {
    case '+':
      cin >> id >> priority;
      nodes[id] = priority;
      s.insert({id, nodes[id]});
      break;

    case '-':
      flag = false;
      cin >> k;
      for (int i = 0; i < k; ++i)
      {
        int get = extract();
        if (get == -1)
          break;
        res.push_back(get);
      }

      if (res.size() < k)
      {
        cout << -1 << endl;
        continue;
      }

      for (int num : res)
      {
        cout << num << " ";
        q.push(num);
      }
      cout << endl;
      break;

    case '=':
      cin >> id >> priority;
      nodes[id] = priority;
      s.insert({id, nodes[id]});

      while (!q.empty())
      {
        s.insert({q.front(), nodes[q.front()]});
        q.pop();
      }
      break;

    default:
      cout << "error" << endl;
      break;
    }
  }

  if (flag)
    cout << "null" << endl;

  return 0;
}
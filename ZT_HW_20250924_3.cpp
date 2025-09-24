#include <bits/stdc++.h>
using namespace std;
const int M = 55;
const int N = 505;

struct Node
{
  bool isLine;
  int idx, price;

  Node(bool isLine, int idx, int price) : isLine(isLine), idx(idx), price(price) {};

  bool operator<(const Node &other) const
  {
    return price > other.price;
  }
};

int m, x, y, z;
int prices[M];
// 0: station, 1: line
int vis[2][N];
// line -> stations
vector<vector<int>> lines(M);
// station -> lines
vector<vector<int>> stations(N);

priority_queue<Node> pq;

int solve()
{
  for (int line : stations[x])
  {
    pq.push({true, line, prices[line]});
  }

  while (!pq.empty())
  {
    Node node = pq.top();
    pq.pop();
    if (vis[node.isLine][node.idx])
      continue;
    vis[node.isLine][node.idx] = true;

    if (!node.isLine && node.idx == y)
      return node.price <= z ? node.price : -1;

    if (node.isLine)
    {
      for (int station : lines[node.idx])
      {
        pq.push({0, station, node.price});
      }
      continue;
    }
    else
    {
      for (int line : stations[node.idx])
      {
        pq.push({1, line, node.price + prices[line]});
      }
    }
  }

  return -1;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> m >> x >> y >> z;

  int price, n, station;
  for (int i = 0; i < m; ++i)
  {
    cin >> price >> n;
    prices[i] = price;
    for (int j = 0; j < n; ++j)
    {
      cin >> station;
      lines[i].push_back(station);
      stations[station].push_back(i);
    }
  }

  cout << solve() << endl;

  return 0;
}
#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 1e6 + 5;

struct Node
{
  int first, second;

  bool operator<(const Node &other) const
  {
    if (second == other.second)
      return first > other.first;
    return second > other.second;
  }
};

int n;
priority_queue<Node> pq;

Node solve()
{
  while (!pq.empty())
  {
    if (pq.size() == 1)
      return pq.top();

    auto player1 = pq.top();
    pq.pop();
    auto player2 = pq.top();
    pq.pop();

    if (player1.second == player2.second)
      continue;

    auto winner = player1.second > player2.second ? player1 : player2;
    auto loser = player1.second < player2.second ? player1 : player2;
    winner.second = (winner.second - loser.second) * 3;
    pq.push(winner);
  }

  return {-1, -1};
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  int life;
  for (int i = 0; i < n; ++i)
  {
    cin >> life;
    pq.push({i, life});
  }

  auto res = solve();
  if (res.first == -1)
    cout << -1 << endl;
  else
    cout << (res.first + 1) << " " << res.second << endl;

  return 0;
}
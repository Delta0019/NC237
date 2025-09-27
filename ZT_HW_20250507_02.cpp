#include <bits/stdc++.h>
using namespace std;
const int N = 3005;
const int inf = 0x3f3f3f3f;

int root;
vector<vector<int>> tree(N, vector<int>(2));
int dp[N][3];

// 已放置节点: dp[i][0]
// 未放置节点, 且未被覆盖: dp[i][1]
// 未放置节点, 但已被覆盖: dp[i][2]
void dfs(int parent)
{
  if (parent == 0)
    return;

  for (int i = 0; i < 2; ++i)
  {
    int child = tree[parent][i];
    if (child == 0)
      continue;

    dfs(child);
  }

  int child0 = tree[parent][0], child1 = tree[parent][1];
  dp[parent][0] = 1 + min({dp[child0][0], dp[child0][1], dp[child0][2]}) +
                  min({dp[child1][0], dp[child1][1], dp[child1][2]});

  dp[parent][1] = dp[child0][2] + dp[child1][2];

  dp[parent][2] = min({dp[child0][0] + dp[child1][0], dp[child0][0] + dp[child1][2], dp[child0][2] + dp[child1][0]});

  // cout << "parent: " << parent << endl;
  // cout << "child0: " << child0 << ", child1: " << child1 << endl;
  // cout << "dp[" << 0 << "]: " << dp[parent][0] << endl;
  // cout << "dp[" << 1 << "]: " << dp[parent][1] << endl;
  // cout << "dp[" << 2 << "]: " << dp[parent][2] << endl;
}

void solve()
{
  memset(dp, 127, sizeof(dp));
  dp[0][0] = inf;
  dp[0][1] = inf;
  dp[0][2] = 0;

  dfs(root);
}

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
  int cur = 0, idx = 1;
  while (getline(ss, next, ' '))
  {
    if (next == "N" && q.empty())
    {
      cout << 0 << endl;
      return 0;
    }

    int num = (next == "N" ? 0 : idx++);

    if (q.empty())
    {
      root = num;
      q.push(num);
      continue;
    }

    tree[q.front()][cur++] = num;

    if (cur == 2)
    {
      q.pop();
      cur = 0;
    }

    if (num != 0)
      q.push(num);
  }

  solve();

  cout << min(dp[root][0], dp[root][2]) << endl;

  return 0;
}
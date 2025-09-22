#include <bits/stdc++.h>
using namespace std;
const int N = 5e3 + 5;

int n;
int dis[N], vis[N];
// <child_cnt, max_dis>
pair<int, int> info[N];
vector<vector<int>> tree(N, vector<int>());

// <child_cnt, max_dis>
pair<int, int> dfs(int parent)
{
  vis[parent] = true;
  int child_cnt = 1, max_dis = dis[parent];

  for (int child : tree[parent])
  {
    if (vis[child])
      continue;

    dis[child] = dis[parent] + 1;
    auto [c, d] = dfs(child);
    child_cnt += c;
    max_dis = max(max_dis, d);
  }

  info[parent] = {child_cnt, max_dis};
  return info[parent];
}

// return: cur_cnt
int dfs_beta(int parent, int target)
{
  vis[parent] = true;
  if (info[parent].second < target || dis[parent] > target)
    return info[parent].first;

  int cnt = 0;
  for (int child : tree[parent])
  {
    if (vis[child])
      continue;

    cnt += dfs_beta(child, target);
  }

  return cnt;
}

void remove_node()
{
  int ans = n;
  for (int i = 0; i <= info[1].second; ++i)
  {
    memset(vis, 0, sizeof(vis));
    ans = min(ans, dfs_beta(1, i));
  }

  cout << ans << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  int u, v;
  for (int i = 0; i < (n - 1); ++i)
  {
    cin >> u >> v;
    tree[u].push_back(v);
    tree[v].push_back(u);
  }

  dfs(1);
  remove_node();

  return 0;
}
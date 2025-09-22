#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 1e5 + 5;

int n, m;
vector<vector<pair<int, int>>> graph(N, vector<pair<int, int>>());
set<int> path1;
set<int> path2;
int ans = 0;
int vis[N];

bool dfs(int root, const set<int> &path)
{
  // cout << "root: " << root << endl;
  bool found = false;
  vis[root] = true;

  if (path.find(root) != path.end())
    found = true;

  for (pair<int, int> next : graph[root])
  {
    if (vis[next.first])
      continue;

    if (dfs(next.first, path))
    {
      ans += 2 * next.second;
      found = true;
    }
  }

  return found;
}

void solve(set<int> &path)
{
  memset(vis, 0, sizeof(vis));
  dfs(1, path);
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m;
  int u, v, c;
  for (int i = 0; i < (n - 1); ++i)
  {
    cin >> u >> v >> c;
    graph[u].push_back({v, c});
    graph[v].push_back({u, c});
  }

  int s, t;
  for (int i = 0; i < m; ++i)
  {
    cin >> s >> t;
    path1.insert(s);
    path2.insert(t);
  }

  solve(path1);
  solve(path2);
  cout << ans << endl;

  return 0;
}
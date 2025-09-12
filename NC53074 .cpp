#include <bits/stdc++.h>
using namespace std;
const int N = 2e5 + 5;

struct Edge
{
  int fr, to, w;
} edges[N];
auto cmp = [](const Edge &a, const Edge &b)
{
  return a.w < b.w;
};

int n, m;
int ui, vi, wi;
int parent[N];

int getParent(int u)
{
  if (parent[u] == u)
    return u;
  parent[u] = getParent(parent[u]);
  return parent[u];
}

void merge(int u, int v)
{
  int rootu = getParent(u);
  int rootv = getParent(v);
  parent[rootv] = rootu;
}

void solve()
{
  sort(edges, edges + m, cmp);
  int ans = 0;
  for (int i = 0; i < m; ++i)
  {
    int l = i, r = i;
    while ((r + 1) <= (m - 1) && edges[l].w == edges[r + 1].w)
      r++;

    // 不能合成为一个for，保证只处理 if (getParent(edge.fr) != getParent(edge.to)) 的边
    // 如果使用 if() {merge} else {ans += w}，就相当于之前已经加过的更短边，现在出现了更长边替代，但这明显是不合理的
    for (int j = l; j <= r; ++j)
    {
      Edge edge = edges[j];
      if (getParent(edge.fr) != getParent(edge.to))
        ans += edge.w;
    }

    for (int j = l; j <= r; ++j)
    {
      Edge edge = edges[j];
      if (getParent(edge.fr) != getParent(edge.to))
      {
        merge(edge.fr, edge.to);
        ans -= edge.w;
      }
    }
  }
  cout << ans << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> m;
  for (int i = 0; i < m; ++i)
  {
    cin >> ui >> vi >> wi;
    edges[i] = {ui, vi, wi};
    parent[ui] = ui;
    parent[vi] = vi;
  }
  solve();

  return 0;
}
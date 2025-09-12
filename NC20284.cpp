#include <bits/stdc++.h>
using namespace std;
const int N = 1e5 + 5;
const int K = 1e5 + 5;

struct Edge
{
  // type_0 =; type_1 <=; type_2 <;
  int to, w;

  Edge(int to, int w) : to(to), w(w) {};
};

int n, k;
int x, a, b;
vector<vector<Edge>> graph(N, vector<Edge>());
int dis[N];
int loose_cnt[N];

int myStack[N];
int sp = 0;
bool instack[N];

bool spfa()
{
  while (sp)
  {
    int node = myStack[--sp];

    instack[node] = false;
    for (Edge edge : graph[node])
    {
      if (dis[edge.to] < edge.w + dis[node])
      {
        dis[edge.to] = dis[node] + edge.w;
        loose_cnt[edge.to] = loose_cnt[node] + 1;

        if (loose_cnt[edge.to] >= n + 1)
          return false;

        if (!instack[edge.to])
        {
          myStack[sp++] = edge.to;
          instack[edge.to] = true;
        }
      }
    }
  }

  return true;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n >> k;

  for (int i = 0; i < k; ++i)
  {
    cin >> x >> a >> b;
    switch (x)
    {
    case 1:
      graph[a].push_back({b, 0});
      graph[b].push_back({a, 0});
      break;
    case 2:
      graph[a].push_back({b, 1});
      break;
    case 3:
      graph[b].push_back({a, 0});
      break;
    case 4:
      graph[b].push_back({a, 1});
      break;
    case 5:
      graph[a].push_back({b, 0});
      break;
    default:
      cout << "error" << endl;
      break;
    }
  }

  for (int i = 1; i <= n; ++i)
  {
    graph[0].push_back({i, 1});
  }

  myStack[sp++] = 0;
  bool res = spfa();

  int ans = 0;
  for (int i = 1; i <= n; ++i)
  {
    ans += dis[i];
  }

  cout << (res ? ans : -1) << endl;

  return 0;
}
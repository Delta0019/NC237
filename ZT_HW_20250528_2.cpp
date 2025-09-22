#include <bits/stdc++.h>
using namespace std;
const int N = 20;

struct Node
{
  int idx, dis;
  vector<int> path;

  bool operator<(const Node &other) const
  {
    return dis > other.dis;
  }
};

int n, s, d;
int graph[N][N], dis[N];
bool isEntry[N], vis[N];

void dijkstra()
{
  memset(dis, 127, sizeof(dis));
  dis[s] = 0;
  priority_queue<Node> pq;
  Node start = {s, dis[s], vector<int>()};
  start.path.push_back(s);
  pq.push(start);

  while (!pq.empty())
  {
    Node node = pq.top();
    pq.pop();

    if (vis[node.idx])
      continue;
    vis[node.idx] = true;

    if (node.idx == d)
    {
      for (int i : node.path)
        cout << i << " ";
      cout << endl;
      return;
    }

    for (int i = 0; i < n; ++i)
    {
      if (graph[node.idx][i] == 0 || vis[i])
        continue;

      vector<int> new_path = node.path;
      new_path.push_back(i);
      pq.push({i, node.dis + graph[node.idx][i], new_path});
    }
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  for (int i = 0; i < n; ++i)
    for (int j = 0; j < n; ++j)
      cin >> graph[i][j];
  for (int i = 0; i < n; ++i)
  {
    cin >> isEntry[i];
  }
  cin >> s >> d;

  dijkstra();

  return 0;
}
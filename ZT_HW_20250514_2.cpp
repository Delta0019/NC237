#include <bits/stdc++.h>
using namespace std;
const int N = 105;
const int inf = 0x3f3f3f3f;

struct Node
{
  int x, y;
  long long dis;

  bool operator<(const Node &other) const
  {
    return dis > other.dis;
  }
};

int k;
int graph[N][N], vis[N][N];
int move1[] = {-1, 0, 1, 0};
int move2[] = {0, -1, 0, 1};
long long dis[N][N];
vector<string> inputs;
priority_queue<Node> pq;

bool valid(int x1, int y1, int x2, int y2)
{
  if (x2 < 1 || x2 > k || y2 < 1 || y2 > k)
    return false;

  if (abs(graph[x1][y1] - graph[x2][y2]) > 1)
    return false;

  return true;
}

void solve()
{
  for (int i = 1; i <= k; ++i)
    for (int j = 1; j <= k; ++j)
      dis[i][j] = inf;

  pq.push({1, 1, graph[1][1]});
  while (!pq.empty())
  {
    Node node = pq.top();
    pq.pop();

    if (vis[node.x][node.y])
      continue;
    vis[node.x][node.y] = true;

    int x = node.x, y = node.y;
    dis[x][y] = node.dis;

    for (int i = 0; i < 4; ++i)
    {
      int new_x = x + move1[i], new_y = y + move2[i];
      if (!valid(x, y, new_x, new_y))
        continue;
      pq.push({new_x, new_y, node.dis + graph[new_x][new_y]});
    }
  }

  long long ans = inf;
  for (int i = 1; i <= k; ++i)
  {
    ans = min(ans, dis[i][k]);
  }
  cout << (ans == inf ? -1 : ans) << endl;
}

int parseInput()
{
  if (inputs.size() != k)
    return -2;

  bool input_valid = true;
  for (int i = 1; i <= k; ++i)
  {
    stringstream ss(inputs[i - 1]);
    string str;
    for (int j = 1; j <= k; ++j)
    {
      getline(ss, str, ' ');
      if (str.empty())
        return -2;

      graph[i][j] = stoi(str);
      if (graph[i][j] < 0)
        return -2;
    }
  }

  return 0;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> k;
  cin.ignore();

  string line;
  for (int i = 0; i < k; ++i)
  {
    getline(cin, line);
    if (line.empty())
      break;
    inputs.push_back(line);
  }

  int res = parseInput();

  if (res == -2)
  {
    cout << -2 << endl;
    return 0;
  }

  solve();

  return 0;
}
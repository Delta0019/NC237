#include <bits/stdc++.h>
using namespace std;
const int N = 1e5 + 10;

int n, root, ans = -1;
int worker1, worker2;
map<int, pair<int, int>> tree;

void parse_input()
{
  cin.ignore();
  string line;
  getline(cin, line);
  istringstream iss(line);

  iss >> root;
  queue<int> q;
  q.push(root);

  int parent = -1, child1 = -1, child2 = -1;
  while (iss >> child1 && !q.empty())
  {
    child2 = -1;
    iss >> child2;

    parent = q.front();
    q.pop();
    tree[parent].first = child1;
    tree[parent].second = child2;

    if (child1 != -1)
    {
      tree[child1] = {-1, -1};
      q.push(child1);
    }
    if (child2 != -1)
    {
      tree[child2] = {-1, -1};
      q.push(child2);
    }
  }

  cin >> worker1 >> worker2;
}

bool isTarget(int node)
{
  return node == worker1 || node == worker2;
}

bool dfs(int parent)
{
  if (parent == -1)
    return false;

  bool found1 = dfs(tree[parent].first);
  bool found2 = dfs(tree[parent].second);

  // cout << "dfs: " << parent << endl;
  // cout << "  --found: " << found1 << ", " << found2 << "; target: " << isTarget(parent) << endl;

  // case1: share parent
  if (found1 && found2)
  {
    ans = parent;
    return false;
  }

  // case2: direct parent
  if (isTarget(parent) && (found1 || found2))
  {
    ans = parent;
    return false;
  }

  // case3: parent is inrelavent
  return found1 || found2 || isTarget(parent);
}

int dfs_cnt(int parent)
{
  if (parent == -1)
    return 0;

  int cnt = dfs_cnt(tree[parent].first) + dfs_cnt(tree[parent].second) + 1;
  return cnt;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  parse_input();
  dfs(root);

  cout << dfs_cnt(ans) - 1 << endl;

  return 0;
}
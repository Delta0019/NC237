#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 0x3f3f3f3e;

// [1,2,3,null,null,7,8]
// [1,2,3,null,4,null,4,null,4,null,4]

struct Node
{
  int index, val;

  bool operator<(const Node &other) const
  {
    return index < other.index;
  }

  Node() : index(-1), val(N) {};
  Node(int index, int val) : index(index), val(val) {};
};

Node root;
int ans = 0;
map<Node, pair<Node, Node>> tree;

int strtoi(string str)
{
  if (str == "null")
    return N;
  return stoi(str);
}

void dfs(Node root, int mul)
{
  if (root.val == N)
    return;

  // left node
  if (tree.find(root) == tree.end())
  {
    if (mul < 0)
      return;

    if (mul == 0)
    {
      ans++;
      return;
    }

    int base = sqrt(root.val);
    if ((base * base) == root.val)
      ans++;
    return;
  }

  Node left = tree[root].first;
  Node right = tree[root].second;

  if (left.val != N)
    dfs(left, mul * left.val);

  if (right.val != N)
    dfs(right, mul * right.val);
}

void solve()
{
  dfs(root, root.val);
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  string str;
  cin >> str;
  str = str.substr(1, str.length() - 2);

  int index = 0;
  stringstream ss(str);
  string numStr;
  queue<Node> nodes;

  while (getline(ss, numStr, ','))
  {
    if (nodes.empty())
    {
      root = {index++, strtoi(numStr)};
      if (root.val == N)
        break;
      nodes.push(root);
      continue;
    }

    Node num1, num2;
    num1 = {index++, strtoi(numStr)};
    getline(ss, numStr, ',');
    num2 = {index++, strtoi(numStr)};

    Node parent = nodes.front();
    nodes.pop();
    tree[parent].first = num1;
    tree[parent].second = num2;

    if (num1.val != N)
      nodes.push(num1);
    if (num2.val != N)
      nodes.push(num2);
  }

  solve();
  cout << ans << endl;

  return 0;
}
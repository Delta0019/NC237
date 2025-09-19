#include <bits/stdc++.h>
using namespace std;
const int N = 105;

struct Node
{
  int begin, end;

  bool operator<(const Node &other) const
  {
    if (begin != other.begin)
      return begin < other.begin;
    return end < other.end;
  }
};

int n;
set<Node> db;
bool dirty = false;

Node dealStr(string str)
{
  int pos = str.find('-');
  if (pos == string::npos)
  {
    int num = stoi(str);
    return {num, num};
  }

  int left = stoi(str.substr(0, pos));
  int right = stoi(str.substr(pos + 1));
  return {left, right};
}

void dealUndo(string str)
{
  Node undo = dealStr(str);
  for (auto it = db.begin(); it != db.end();)
  {
    if (undo.begin > it->end || undo.end < it->begin)
    {
      ++it;
      continue;
    }

    if (undo.begin <= it->begin && undo.end >= it->end)
    {
      it = db.erase(it);
      continue;
    }

    if (undo.begin > it->begin && undo.end < it->end)
    {
      Node node1 = {it->begin, undo.begin - 1};
      Node node2 = {undo.end + 1, it->end};
      it = db.erase(it);
      db.insert(node2);
      db.insert(node1);
      continue;
    }

    Node node = {0, -1};
    if (undo.begin > it->begin)
      node = {it->begin, undo.begin - 1};
    else if (undo.end < it->end)
      node = {undo.end + 1, it->end};

    it = db.erase(it);
    if (node.begin <= node.end)
    {
      db.insert(node);
    }
  }
}

void merge()
{
  if (!dirty)
    return;
  dirty = false;

  for (auto it = db.begin(); it != db.end();)
  {
    if (it->begin > it->end)
      continue;
    auto nextIt = it;
    nextIt++;
    if (nextIt == db.end())
      break;

    Node newNode = {it->begin, it->end};

    if ((newNode.end + 1) < nextIt->begin)
    {
      ++it;
      continue;
    }

    db.erase(it);
    while (nextIt != db.end() && (newNode.end + 1) >= nextIt->begin)
    {
      newNode.end = max(newNode.end, nextIt->end);
      nextIt = db.erase(nextIt);
    }
    db.insert(newNode);
    it = nextIt;
  }
}

void printDB()
{
  bool flag_first = true;
  for (Node node : db)
  {
    if (!flag_first)
    {
      cout << ',';
    }
    flag_first = false;

    if (node.begin == node.end)
      cout << node.begin;
    else
      cout << node.begin << '-' << node.end;
  }
  if (db.empty())
    cout << 0;
  cout << endl;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  string str;
  for (int i = 0; i < n; ++i)
  {
    cin >> str;
    if (str == "undo")
    {
      // skip "algorithm"
      cin >> str;
      cin >> str;
      merge();
      int end = str.find(',');

      while (end != string::npos)
      {
        string substr = str.substr(0, end);
        str = str.substr(end + 1);
        dealUndo(substr);
        end = str.find(',');
      }
      dealUndo(str);
    }
    else
    {
      cin >> str;
      dirty = true;
      int end = str.find(',');

      while (end != string::npos)
      {
        string substr = str.substr(0, end);
        str = str.substr(end + 1);
        db.insert(dealStr(substr));
        end = str.find(',');
      }
      db.insert(dealStr(str));
    }
  }
  merge();
  printDB();

  return 0;
}
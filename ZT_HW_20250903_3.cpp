#include <bits/stdc++.h>
using namespace std;

stringstream ss;
map<char, int> key_value;

bool isLetter(char ch)
{
  return ch >= 'a' && ch <= 'z';
}

void dealUnnecessary()
{
  int cnt = 1;
  string str;
  while (getline(ss, str, ' '))
  {
    char ch = str[0];
    if (ch == ']')
      cnt--;
    if (ch == '[')
      cnt++;

    if (cnt == 0)
      return;
  }
}

multiset<int> dealNecessary()
{
  string str;
  multiset<int> result;
  while (getline(ss, str, ' '))
  {
    char ch = str[0];
    if (isLetter(ch))
      result.insert(ch);

    if (ch == '{')
    {
      multiset<int> middle = dealNecessary();
      result.merge(middle);
    }

    if (ch == '[')
      dealUnnecessary();

    if (ch == '|')
    {
      multiset<int> right = dealNecessary();
      multiset<int> new_result;
      set_intersection(result.begin(), result.end(), right.begin(), right.end(), inserter(new_result, new_result.begin()));
      result = new_result;
      break;
    }

    if (ch == '}')
      break;
  }

  return result;
}

void solve()
{
  stack<char> s;

  string str;
  while (getline(ss, str, ' '))
  {
    char ch = str[0];

    if (isLetter(ch))
      key_value[ch]++;

    if (ch == '{')
    {
      multiset<int> ms = dealNecessary();
      for (int num : ms)
        key_value[num]++;
    }

    if (ch == '[')
      dealUnnecessary();
  }
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  string input;
  getline(cin, input);
  ss = stringstream(input);
  solve();

  for (auto kv : key_value)
  {
    cout << kv.first << " ";
  }
  cout << endl;
  for (auto kv : key_value)
  {
    cout << kv.second << " ";
  }
  cout << endl;

  return 0;
}
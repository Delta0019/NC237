#include <bits/stdc++.h>
using namespace std;

long long parse_mac(string s)
{
  string hex_str;
  for (char c : s)
  {
    if (c != '-')
    {
      hex_str += c;
    }
  }
  return stoll(hex_str, nullptr, 16);
}

int main()
{
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  cin >> n;
  vector<pair<long long, int>> rules;

  for (int i = 0; i < n; i++)
  {
    string line;
    cin >> line;
    size_t pos = line.find('/');
    string mac_str = line.substr(0, pos);
    int mask_len = stoi(line.substr(pos + 1));
    long long mac_val = parse_mac(mac_str);
    rules.emplace_back(mac_val, mask_len);
  }

  int m;
  cin >> m;

  for (int j = 0; j < m; j++)
  {
    string s;
    cin >> s;
    long long p = parse_mac(s);
    bool is_match = false;

    for (auto &rule : rules)
    {
      long long vip = rule.first;
      int mask = rule.second;
      int shift = 48 - mask;

      if ((p >> shift) == (vip >> shift))
      {
        is_match = true;
        break;
      }
    }

    if (is_match)
      cout << "YES" << endl;
    else
      cout << "NO" << endl;
  }

  return 0;
}

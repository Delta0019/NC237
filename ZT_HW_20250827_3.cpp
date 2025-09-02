#include <bits/stdc++.h>
using namespace std;

const int N = 3e4 + 5, L = 30;
int d, n;
string cmd[N], input;

int strDis(string str)
{
  int dp[L][L];
  memset(dp, 127, sizeof(dp));
  for (int i = 0; i <= input.length(); ++i)
  {
    dp[i][0] = i;
  }
  for (int j = 0; j <= str.length(); ++j)
  {
    dp[0][j] = j;
  }

  for (int i = 1; i <= input.length(); ++i)
  {
    for (int j = 1; j <= str.length(); ++j)
    {
      // case1: 末尾的正好能对上
      // case2: 需要删除
      // case3: 需要增加
      int match = input[i - 1] == str[j - 1] ? 0 : 1;
      dp[i][j] = min({dp[i - 1][j - 1] + match,
                      dp[i - 1][j] + 1,
                      dp[i][j - 1] + 1});
    }
  }

  // cout << "str: " << str << ", dp: " << dp[input.length()][str.length()] << endl;
  return dp[input.length()][str.length()];
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);
  cin >> d >> n;
  for (int i = 0; i < n; ++i)
  {
    cin >> cmd[i];
  }
  cin >> input;

  vector<pair<int, string>> ans;
  for (int i = 0; i < n; ++i)
  {
    int dis = strDis(cmd[i]);
    if (dis == 0)
    {
      cout << cmd[i] << endl;
      return 0;
    }
    else if (dis <= d)
    {
      ans.push_back({dis, cmd[i]});
    }
  }

  if (ans.empty())
  {
    cout << "None" << endl;
  }
  else
  {
    sort(ans.begin(), ans.end(), [](auto a, auto b)
         { if (a.first != b.first) return a.first < b.first;
        return a.second < b.second; });
    for (auto p : ans)
    {
      cout << p.second << " ";
    }
    cout << endl;
  }

  return 0;
}
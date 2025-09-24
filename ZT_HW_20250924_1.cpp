#include <bits/stdc++.h>
using namespace std;
const int N = 405;

int n, new_board;
int boards[N];

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> n;
  for (int i = 0; i < n; ++i)
  {
    cin >> boards[i];
  }
  cin >> new_board;

  int start = 0;
  for (int i = 1; i < n; ++i)
  {
    if (boards[i - 1] > boards[i])
    {
      start = i;
      break;
    }
  }

  int ans = n;

  if (new_board >= boards[n - 1])
  {
    for (int i = 0; i < start; ++i)
    {
      if (new_board <= boards[i])
      {
        ans = min(ans, i);
        break;
      }
    }
    if (ans == n)
      ans = start - 1;
  }
  else
  {
    for (int i = start; i < n; ++i)
    {
      if (new_board <= boards[i])
      {
        ans = min(ans, i);
        break;
      }
    }
  }

  for (int i = 0; i < n; ++i)
  {
    if (i == ans)
    {
      cout << min(new_board, boards[i]) << " ";
      cout << max(new_board, boards[i]) << " ";
    }
    else
      cout << boards[i] << " ";
  }
  cout << endl;

  return 0;
}
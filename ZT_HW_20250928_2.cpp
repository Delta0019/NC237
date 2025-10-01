#include <bits/stdc++.h>
using namespace std;

int main()
{
  int n;
  cin >> n;
  vector<int> values(n);
  for (int i = 0; i < n; i++)
  {
    cin >> values[i];
  }
  int limit;
  cin >> limit;

  sort(values.begin(), values.end(), greater<int>());
  vector<bool> visited(n, false);
  int awards = 0;
  while (true)
  {
    int rem = limit;
    bool any = false;
    for (int i = 0; i < n; i++)
    {
      if (!visited[i] && values[i] <= rem)
      {
        rem -= values[i];
        any = true;
        visited[i] = true;
      }
    }
    if (!any)
      break;
    awards++;
  }
  cout << awards << endl;
}

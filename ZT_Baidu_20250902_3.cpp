#include <bits/stdc++.h>
using namespace std;

const int N = 1e3 + 5;
int n;
bitset<15> a_set[N];

bool solve(int index, int input)
{
  bitset<15> res;
  bitset<15> b(input);
  int bit_cnt = b.count();
  for (int i = 1; i <= index; ++i)
  {
    // 确保 a_set[i] 是 b 的子集
    if ((b | a_set[i]).count() > bit_cnt)
      continue;

    res |= a_set[i];
  }
  if (res.count() == bit_cnt)
    return true;

  return false;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  int T;
  cin >> T;
  while (T--)
  {
    bool flag = true;
    cin >> n;
    for (int i = 1; i <= n; ++i)
    {
      int a;
      cin >> a;
      a_set[i] = bitset<15>(a);
    }

    for (int i = 1; i <= n; ++i)
    {
      int b;
      cin >> b;

      if (!solve(i, b))
      {
        flag = false;
        break;
      }
    }

    cout << (flag ? "Yes" : "No") << endl;
  }

  return 0;
}
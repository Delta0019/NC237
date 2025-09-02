#include <bits/stdc++.h>
using namespace std;

const int N = 1e6 + 5;
bitset<32> target;

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  int n;
  cin >> n;
  bitset<32> a[n], b[n];

  for (int i = 0; i < n; ++i)
  {
    int a_input;
    cin >> a_input;
    a[i] = bitset<32>(a_input);
  }
  target = a[0];

  for (int i = 0; i < n; ++i)
  {
    b[i] = a[0] ^ a[i];
    cout << b[i].to_ulong() << " ";
  }
  cout << endl;

  return 0;
}
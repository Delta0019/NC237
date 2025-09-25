#include <bits/stdc++.h>
using namespace std;
#define int long long
const int N = 1e5 + 5;

int threshold, n;
int record[N];
int anti[N];
int ans = 0;

void analyze(int l, int m, int r)
{
  for (int i = m + 1; i <= r; ++i)
  {
    int pos = lower_bound(record + l, record + m + 1, record[i] + threshold + 1) - record;
    if (pos <= m)
      ans += m - pos + 1;
  }
}

void merge(int l, int m, int r)
{
  analyze(l, m, r);

  vector<int> updated;
  int ptr1 = l, ptr2 = m + 1;

  while (ptr1 <= m && ptr2 <= r)
  {
    if (record[ptr1] <= record[ptr2])
      updated.push_back(record[ptr1++]);
    else
      updated.push_back(record[ptr2++]);
  }

  if (ptr1 > m)
  {
    while (ptr2 <= r)
      updated.push_back(record[ptr2++]);
  }

  if (ptr2 > r)
  {
    while (ptr1 <= m)
      updated.push_back(record[ptr1++]);
  }

  for (int i = l; i <= r; ++i)
    record[i] = updated[i - l];
}

void mergeSort(int l, int r)
{
  if (l == r)
    return;

  int mid = l + (r - l) / 2;
  mergeSort(l, mid);
  mergeSort(mid + 1, r);
  merge(l, mid, r);
}

void solve()
{
  mergeSort(0, n - 1);
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  cin >> threshold >> n;
  for (int i = 0; i < n; ++i)
  {
    cin >> record[i];
  }

  solve();
  cout << ans << endl;

  return 0;
}
#include <bits/stdc++.h>
using namespace std;
#define int long long
const int maxN = 13;

set<int> candidates;
set<int> primes;
int vis[200];

void getPrimes(int maxn)
{
  for (int i = 2; i <= maxn; ++i)
  {
    if (vis[i])
      continue;
    primes.insert(i);
    for (int j = i * i; j <= maxn; j += i)
      vis[j] = true;
  }
}

void dfsCandidates(int cur, int cnt)
{
  candidates.insert(cur);
  if (cnt == maxN)
    return;

  int last = cur % 10;
  for (int i = last; i <= 9; ++i)
    dfsCandidates(cur * 10 + i, cnt + 1);
}

bool Invalid(int num, int n)
{
  if (num > n)
    return true;

  int sum = 0;
  while (num > 0)
  {
    sum += num % 10;
    num /= 10;
  }
  return primes.find(sum) == primes.end();
}

int solve(int n)
{
  getPrimes(180);
  dfsCandidates(0, 0);
  for (auto it = candidates.rbegin(); it != candidates.rend(); ++it)
  {
    if (Invalid(*it, n))
      continue;
    return *it;
  }
  return -1;
}

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  int n;
  cin >> n;

  cout << solve(n) << endl;

  return 0;
}
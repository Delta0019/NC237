#include <bits/stdc++.h>
using namespace std;

struct Charger
{
  int id;
  long long x, y;
  long long dist;
};

int main()
{
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int k, n;
  cin >> k >> n;

  if (k == 0 || k > n)
  {
    cout << "null\n";
    return 0;
  }

  long long car_x, car_y;
  cin >> car_x >> car_y;

  vector<Charger> chargers(n);
  for (int i = 0; i < n; ++i)
  {
    long long x, y;
    cin >> x >> y;
    long long dist = abs(car_x - x) + abs(car_y - y);
    chargers[i] = {i + 1, x, y, dist};
  }

  sort(chargers.begin(), chargers.end(), [](const Charger &a, const Charger &b)
       {
        if (a.dist != b.dist) return a.dist < b.dist;
        return a.id < b.id; });

  for (int i = 0; i < k; ++i)
  {
    const auto &c = chargers[i];
    cout << c.id << ' ' << c.x << ' ' << c.y << ' ' << c.dist << '\n';
  }

  return 0;
}

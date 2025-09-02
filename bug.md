# bug 记录

## Java

1. 由于java的快读快写代码量较长，因此在编写代码前先查看其输入数量级是否大于 1e5，如果是，考虑用 C++ 编写
2. PriorityQueue的for访问并不是顺序访问，使用 poll() 不断弹出才是顺序访问

## Cpp

1. 常见常量
   1. inf: 0x3f3f3f3f;
2. 数组声明: int dis[N];
3. lambda
   1. auto cmp = [](Node a, Node b) { return a.distance > b.distance; };
   2. 注意这里只能使用 > , 因为 cpp 会把所有非 0 的值默认转为 true
4. 优先级问题
   1. true -> 优先级低: 包括 priority_queue 在内的所有堆
      1. 推荐使用重载 operator<(const Node& other) { return a > other.a;}
   2. true -> 优先级高: 包括 sort, set, map, lower_bound, binary_search 在内的结构
      1. 直接在括号内加上 lambda 即可:

      ```cpp
      auto cmp = [](const Node& a, const Node& b) { return a.val > b.val; };
      vector<Node> vec = {{3}, {1}, {5}};
      // 以上比较函数在这些容器/算法中都产生降序排列：

      sort(vec.begin(), vec.end(), cmp);                    // 降序
      stable_sort(vec.begin(), vec.end(), cmp);             // 降序  
      partial_sort(vec.begin(), vec.end(), cmp);            // 降序
      nth_element(vec.begin(), vec.end(), cmp);             // 降序

      set<Node, decltype(cmp)> s(cmp);                      // 降序
      map<Node, int, decltype(cmp)> m(cmp);                 // 降序

      lower_bound(vec.begin(), vec.end(), target, cmp);     // 基于降序查找
      upper_bound(vec.begin(), vec.end(), target, cmp);     // 基于降序查找
      binary_search(vec.begin(), vec.end(), target, cmp);   // 基于降序查找
      ```

5. 常见Map
   1. unordered_map: 哈希表
   2. map: 红黑树
   3. set: 基于红黑树的有序集合
   4. multiSet: 基于红黑树的有序可重复集合
      1. **Note**: erase(val) 会删除所有元素, 如果需要删除某元素且只删除一次, 需要使用 find 获取 it 然后 erase(it).

## 算法

1. 第九章
   1. Dijkstra
      1. 贪心的思想，用于求从某个点到剩下所有点的最短路径, 时间复杂度为 O((V + E) * V)
      2. 应用场景: 只能用于求一个点到所有点的最短距离, 且所有边为非负权边
      3. 思路: 先将所有的点的距离设置为 inf, 接着令 dis[start] = 0, 即令起始点到自身距离为零, 接着不断访问离**已经访问的点**最近的点, 并更新该点所有邻居的距离(Note: 这一步使得)该算法无法处理负权边, 不断重复访问即可。
   2. Floyd
      2. 插点法/动态规划的思想, 用于求所有点两两之间的最短路径, 时间复杂度为 O(V ^ 3)
      3. 应用场景: 可用于处理负权边
      4. 核心代码:

      ```cpp
      for(int k=1;k<=n;k++){
        for(int i=1;i<=n;i++){
          for(int j=1;j<=n;j++){
            if(G[i][j]>G[i][k]+G[k][j]){//与插入后进行比较
              G[i][j]=G[i][k]+G[k][j];
            }
          }
        }
      } 
      ```

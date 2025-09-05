# bug 记录

## 测试

1. 尽量使用大一些的数字进行测试，因为小数字会和其index重合，导致无法分辨输出的是index还是input。

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
   3. Note:
      1. cmp 前最好使用 auto, 不能使用 bool
      2. 不能使用默认构造函数: set<Node, decltype(cmp)> s(cmp); 使用这里时，不仅要在 decltype 中传入 lambda 表达式，还需要 s(cmp) 的构造函数中传入 cmp
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
      1. map 的基本操作: map<int> a;
         1. 插入
            1. a[i] = j;
            2. 注: 此时如果 a[i] 在map中不存在, 则会自动创建
         2. 自增
            1. a[i]++;
            2. 此时会自动创建并令其为1
         3. 迭代器
            1. 存在两种迭代器，这两种迭代器不能直接比较
               1. std::map<int, int>::iterator
                  1. begin()
                  2. end()
               2. std::map<int, int>::reverse_iterator
                  1. rbegin()
                  2. rend()
   3. set: 基于红黑树的有序集合
   4. multiSet: 基于红黑树的有序可重复集合
      1. **Note**: erase(val) 会删除所有元素, 如果需要删除某元素且只删除一次, 需要使用 find 获取 it 然后 erase(it).
6. 位运算
   1. bitset
      1. bitset 是一个固定大小的位集合，在编译时确定大小
      2. 初始化
         1. std::bitset<8> b1;        // 8位，默认全0
         2. std::bitset<16> b2(42);   // 用整数初始化
         3. std::bitset<8> b3("1010"); // 用字符串初始化
      3. 基本操作
         1. b.set();        // 全部设为1：11111111
         2. b.set(3);       // 设置第3位为1
         3. b.set(2, 0);    // 设置第2位为0
         4. b.reset();      // 全部设为0：00000000
         5. b.reset(5);     // 重置第5位为0
         6. b.flip();       // 全部翻转
         7. b.flip(1);      // 翻转第1位
      4. 查询
         1. bool bit = b[3];        // 获取第3位的值
         2. bool bit2 = b.test(4);  // 测试第4位，比[]更安全
         3. 统计函数

         ```cpp
         std::bitset<8> b("10110001");

         b.size();       // 返回8（位数）
         b.count();      // 返回3（设为1的位数）
         b.any();        // 返回true（至少有一位为1）
         b.none();       // 返回false（不是所有位都为0）
         b.all();        // 返回false（不是所有位都为1）
         ```

      5. 位运算
         1. 示例

         ```cpp
         std::bitset<8> b1("10110001");
         std::bitset<8> b2("11001010");

         b1 & b2;        // 按位与：10000000
         b1 | b2;        // 按位或：11111011  
         b1 ^ b2;        // 按位异或：01111011
         ~b1;            // 按位取反：01001110

         b1 <<= 2;       // 左移2位：11000100
         b1 >>= 1;       // 右移1位：01100010
         ```

      6. 转换操作
         1. 示例

         ```cpp
         std::bitset<8> b("10110001");

         // 转为字符串
         std::string str = b.to_string();           // "10110001"
         std::string str2 = b.to_string('0', '1');  // 自定义0和1的字符

         // 转为数字
         unsigned long num = b.to_ulong();          // 177
         unsigned long long num2 = b.to_ullong();   // 177
         ```

      7. 

## 算法

1. DP
   1. 何时使用 DP
      1. 只需求出最终的一个答案, 即能被 dp[][][][] 直接表示, 无需保留中间变量
      2. 问题能被分解为子问题, 且无后向影响, 即子问题无需考虑没有被 dp[] 直接存储 (如状态dp, 或是已经使用的物品) 的前面选择造成的影响
2. 第九章
   1. Dijkstra
      1. 贪心的思想，用于求从某个点到剩下所有点的最短路径, 时间复杂度为 O((V + E) * V)
      2. 应用场景: 只能用于求一个点到所有点的最短距离, 且所有边为非负权边
      3. 思路: 先将所有的点的距离设置为 inf, 接着令 dis[start] = 0, 即令起始点到自身距离为零, 接着不断访问离**已经访问的点**最近的点, 并更新该点所有邻居的距离(Note: 这一步使得)该算法无法处理负权边, 不断重复访问即可。
   2. Floyd
      1. 插点法/动态规划的思想, 用于求所有点两两之间的最短路径, 时间复杂度为 O(V ^ 3)
      2. 应用场景: 可用于处理负权边
      3. 核心代码:

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

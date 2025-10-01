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

1. Small Bug
   1. **先看一遍所有题目!!!**
   2. **运行自动测试样例**: 可能哪里不小心自定义了输出，但未注意
   3. 注意并非输入n就有n行输入，可能有**n-1**行
   4. 注意输入的节点可能**存在重复**(死循环), 使用自增idx来计算节点值
   5. **RE**
      1. 注意看题目数据范围, >= 1e7 需要使用**map**
2. 数据范围
   1. n <= 1e4
      1. **使用O(n^2)**
      2. O(n^2logn): 使用 log 来优化前面的 n, 可能会造成超时问题
3. 优化
   1. bitset
      1. **枚举状态时**
         1. **部分点可能只被部分设备覆盖**, 例如 cover[i] = 0100, 当 mask = 0110时, 可以**通过&运算**, 直接获取该状态下的覆盖
4. 基本类型
   1. pair
      1. 对于基本类型，有默认比较器: **先比较first, 再比较second**
   2. string
      1. 等效于Java的 **StringBuilder**
   3. vector
      1. vector<int> 可以通过 < 进行字典序比较
5. IO
   1. 整行输入
      1. getline(cin, str); // 需要先声明 string str;
   2. 处理分隔符
      1. getline(cin, str, ','); // 假设以 ',' 作为分隔符
6. 二分
   1. 何时使用二分
      1. 二分答案: 对于一个数组，需要**求一个最小值k**，使得在一定次数的移动中，所有值**均大于最小值k**
      2. 二分查找: 快速查找某值
   2. 算法:
      1. 思想: 使用一个 ans 保存当前合法值
      2. 代码

         ```cpp
         int left = 0, right = length, ans;
         while(left <= right) {
            int mid = left + (right - left) /2;
            if (check(mid)) {
               ans = mid;
               left = mid + 1;
            } else {
               right = mid - 1;
            }
         }
         ```

   3. 函数
      1. lower_bound: 寻找**不小于**
         1. 将返回的迭代器 --it 可得**小于**
      2. upper_bound: 寻找**大于**
         1. 将返回的迭代器 --it 可得**不大于**

7. 排序
   1. 就地排序
      1. O(N^2)
         1. 选择排序, 冒泡排序等
      2. O(NlogN)
         1. 快速排序
   2. 查找符合要求的**顺序/逆序对**
      1. **归并排序**: 一边排序一边进行查找，由于只需考虑已经排序后的另一半，因此**对于每个元素查找只需 O(logN)**
8. DP
   1. 何时使用 DP
      1. 只需求出最终的一个答案, 即能被 dp[][][][] 直接表示, 无需保留中间变量
      2. 问题能被分解为子问题, 且无后向影响, 即子问题无需考虑没有被 dp[] 直接存储 (如状态dp, 或是已经使用的物品) 的前面选择造成的影响
      3. **dp一定要事先想好状态，考虑叶子节点是否存在特殊情况，如果存在，需要追加状态**
   2. 树形dp
      1. 思想: 父节点有状态, 取父节点不同状态下的最大值
      2. 核心代码:

         ```cpp
         void calc(int k) {
            vis[k] = 1;
            // 枚举该结点的每个子结点
            for (int i = head[k]; i; i = e[i].next) {  
               if (vis[e[i].v]) continue;
               calc(e[i].v);
               f[k][1] += f[e[i].v][0];
               f[k][0] += max(f[e[i].v][0], f[e[i].v][1]);  // 转移方程
            }
            return;
         }
         ```

9.  第九章
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

   3. SPFA 算法
      1. 场景:
         1. 和 dijkstra 一样用于解决**单源最短路问题**，但能处理负权边
         2. 同时适用于处理对于某点对应的**dis[i]不能在访问即确定**，可能反复更新的题目。
      2. 核心思路:
         1. 和dijkstra一样，初始将所有点 dis = infinit，令 dis[0] = 0，将点0加入队列
         2. 之后不断取出队头，访问其边，如果边的值比原本小，**且不在队列中**，则加入队列。
         3. 直到队列为空。
      3. 判断负权环的存在:
         1. 思路: 统计访问次数链，如果 >= n+1 则存在负权环，此时直接返回即可
         2. 公式: cnt[edge.to] = cnt[node] + 1;
   4. 最短路图
      1. 基本思想: 通过 dijkstra 遍历图，此时图就是从该节点出发的最短路图
      2. 时间复杂度: O(V*(V + E)logV)
      3. 步骤
         1. 使用dijkstra遍历图
            1. 每通过一个 edge 访问到节点，则判断该dis[to]，是否比原本更小
               1. 如果是，则清空最短路图中该节点的边，并将当前边加入
               2. 如果不是，则直接将当前边加入最短路图中该节点的边
         2. 将所有节点的最短路图叠加，就是该图的最短路图
      4. 变种:
         1. 如果需要，也可以建反向图
            1. 应用场景: 对于每条边，计算有多少条不同的最短路经过该道路
            2. 思路: 对于每个节点的最短路图，[边的经过数量] = 正向图中以 [边起点] 为终点的路径数 * 反向图中以 [边终点] 为起点的路径数。对于每个节点分别进行 dijkstra + 反向图拓扑排序，累加即可。
            3. 注意, 对于 A -> B, B的"正向图中以 [边起点] 为终点的路径数" 直接继承自A(反之亦然)
   5. 最小生成树
      1. 场景: 通过尽可能少的边(即 n-1)， 将整个树连接，使得整个树最短
      2. Prim 算法
         1. 步骤
            1. **贪心**地加入最短的**边**(而不是dis[node])即可。
            2. 即维护一个最小堆，每次push({edge.to, edge.w}); 注意这里的 edge.to 必须是未访问过的.
         2. 注意:
            1. 对于**能判定大致朝向的有向边图(如雪山滑雪)**，需要先访问高处的点，因为如果先访问低处的点，会导致部分有向边无法被利用
      3. kruskai 算法
         1. 思路: 对于**排序后的路径**，通过并查集的方法，依次选取能连接不同集合的边并加入，最终结果就是最小生成树
         2. 步骤
            1. 对所有edge按照权重排序
            2. 每次获取相同权重的边，并依次检查是否可加入最小生成树:

            ```cpp
               int l = i, r = i;
               while ((r + 1) <= (m - 1) && edges[l].w == edges[r + 1].w)
                  r++;
            ```

10. 数论
   1. 质数
      1. 筛选一定范围内的质数
         1. 算法:

         ```cpp
         function sieveOfEratosthenes(n) {
            if (n < 2) return [];
            
            const isPrime = new Array(n + 1).fill(true);
            isPrime[0] = isPrime[1] = false;
            
            for (let i = 2; i * i <= n; i++) {
               if (isPrime[i]) {
                     // 从 i² 开始标记，因为更小的倍数已经被标记过了
                     for (let j = i * i; j <= n; j += i) {
                        isPrime[j] = false;
                     }
               }
            }
            
            const primes = [];
            for (let i = 2; i <= n; i++) {
               if (isPrime[i]) {
                     primes.push(i);
               }
            }
            
            return primes;
         }
         ```

      2.

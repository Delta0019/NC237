#include <bits/stdc++.h>
using namespace std;

struct Task
{
  int idx, id, priority, time;

  Task(int idx, int id, int priority, int time) : idx(idx), id(id), priority(priority), time(time) {}

  bool operator<(const Task &other) const
  {
    if (priority == other.priority)
      return idx > other.idx;
    return priority > other.priority;
  }
};

priority_queue<Task> tasks;

signed main()
{
  ios::sync_with_stdio(false);
  cin.tie(0), cout.tie(0);

  string line;
  while (getline(cin, line))
  {
    if (line.empty())
      break;

    stringstream ss(line);
    string str;
    getline(ss, str, ' ');
    int index = 0;
    if (str == "add")
    {
      getline(ss, str, ' ');
      int id = stoi(str);
      getline(ss, str, ' ');
      int priority = stoi(str);
      getline(ss, str, ' ');
      int time = stoi(str);
      Task task = {index++, id, priority, time};
      tasks.push(task);
    }
    else if (str == "run")
    {
      getline(ss, str, ' ');
      int time = stoi(str);
      while (!tasks.empty() && time > 0)
      {
        Task task = tasks.top();
        tasks.pop();
        if (task.time <= time)
          time -= task.time;
        else
        {
          task.time -= time;
          time = 0;
          tasks.push(task);
        }
      }
    }
  }

  cout << (tasks.empty() ? "idle" : to_string(tasks.top().id)) << endl;

  return 0;
}
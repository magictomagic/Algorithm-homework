package algorithmAssignments;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class JobScheduling {
	public static class JobNode implements Comparable {
        int id;// 作业的标号
        int time;// 作业时间

        // 构造方法初始化
        public JobNode(int id, int time) {
            this.id = id;
            this.time = time;
        }

        public int compareTo(Object x) {// 按时间从大到小排列
            int times = ((JobNode) x).time;
            if (time > times)
                return -1;
            if (time == times)
                return 0;
            return 1;
        }
    }

    public static class MachineNode implements Comparable {
        int id;// 机器的标号
        int avail;// 机器空闲的时间（即机器做完某一项工作的时间）

        public MachineNode(int id, int avail) {
            this.id = id;
            this.avail = avail;
        }

        public int compareTo(Object o) {// 升序排序，LinkedList的first为最小的
            int xs = ((MachineNode) o).avail;
            if (avail < xs)
                return -1;
            if (avail == xs)
                return 0;
            return 1;
        }
    }

    public static int greedy(int[] a, int m) {
        int n = a.length - 1;// a的下标从1开始，所以n（作业的数目）=a.length-1
        int sum = 0;
        // 当机器数大于等于作业数
        if (n <= m) {
            sum = a[0];
            for (int i = 0; i < n; i++)
                if (a[i + 1] > a[i]) {
                    sum = a[i + 1];
                }
            // sum += a[i + 1];
            System.out.println("为每个作业分别分配一台机器");
            return sum;
        }
        List<JobNode> d = new ArrayList<JobNode>();// d保存所有的作业
        for (int i = 0; i < n; i++) {// 将所有的作业存入List中，每一项包含标号和时间
            JobNode jb = new JobNode(i + 1, a[i + 1]);
            d.add(jb);// 添加到list
        }
        Collections.sort(d);// 对作业的List进行排序
        LinkedList<MachineNode> h = new LinkedList<MachineNode>();// h保存所有的机器
        for (int i = 1; i <= m; i++) {// 将所有的机器存入LinkedList中
            MachineNode x = new MachineNode(i, 0);// 初始时，每台机器的空闲时间（完成上一个作业的时间）都为0
            h.add(x);
        }
        int test = h.size();// 将机器的大小赋初始值
        for (int i = 0; i < n; i++) {
            Collections.sort(h);// 对机器上正在进行的时间进行排序
            MachineNode x = h.peek(); // 查看栈顶元素 栈顶元素为最小元素。
            System.out.println("将机器" + x.id + "从" + x.avail + "到"
                    + (x.avail + d.get(i).time) + "的时间段分配给作业" + d.get(i).id);
            x.avail += d.get(i).time;
            if (x.avail > sum) {
                sum = x.avail;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // 不同的测试数据
        int[] a = { 0, 1, 14, 4, 16, 6, 5, 3 };
        // int[] a = { 0, 2, 14, 4, 16, 6, 5, 3 };
        // int[] a = { 0, 44, 14, 4 };
        int m = 3;// 机器数
        int sum = greedy(a, m);
        System.out.println("总时间为：" + sum);
    }

}

package algorithmAssignments;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class JobScheduling {
	public static class JobNode implements Comparable {
        int id;// ��ҵ�ı��
        int time;// ��ҵʱ��

        // ���췽����ʼ��
        public JobNode(int id, int time) {
            this.id = id;
            this.time = time;
        }

        public int compareTo(Object x) {// ��ʱ��Ӵ�С����
            int times = ((JobNode) x).time;
            if (time > times)
                return -1;
            if (time == times)
                return 0;
            return 1;
        }
    }

    public static class MachineNode implements Comparable {
        int id;// �����ı��
        int avail;// �������е�ʱ�䣨����������ĳһ�����ʱ�䣩

        public MachineNode(int id, int avail) {
            this.id = id;
            this.avail = avail;
        }

        public int compareTo(Object o) {// ��������LinkedList��firstΪ��С��
            int xs = ((MachineNode) o).avail;
            if (avail < xs)
                return -1;
            if (avail == xs)
                return 0;
            return 1;
        }
    }

    public static int greedy(int[] a, int m) {
        int n = a.length - 1;// a���±��1��ʼ������n����ҵ����Ŀ��=a.length-1
        int sum = 0;
        // �����������ڵ�����ҵ��
        if (n <= m) {
            sum = a[0];
            for (int i = 0; i < n; i++)
                if (a[i + 1] > a[i]) {
                    sum = a[i + 1];
                }
            // sum += a[i + 1];
            System.out.println("Ϊÿ����ҵ�ֱ����һ̨����");
            return sum;
        }
        List<JobNode> d = new ArrayList<JobNode>();// d�������е���ҵ
        for (int i = 0; i < n; i++) {// �����е���ҵ����List�У�ÿһ�������ź�ʱ��
            JobNode jb = new JobNode(i + 1, a[i + 1]);
            d.add(jb);// ��ӵ�list
        }
        Collections.sort(d);// ����ҵ��List��������
        LinkedList<MachineNode> h = new LinkedList<MachineNode>();// h�������еĻ���
        for (int i = 1; i <= m; i++) {// �����еĻ�������LinkedList��
            MachineNode x = new MachineNode(i, 0);// ��ʼʱ��ÿ̨�����Ŀ���ʱ�䣨�����һ����ҵ��ʱ�䣩��Ϊ0
            h.add(x);
        }
        int test = h.size();// �������Ĵ�С����ʼֵ
        for (int i = 0; i < n; i++) {
            Collections.sort(h);// �Ի��������ڽ��е�ʱ���������
            MachineNode x = h.peek(); // �鿴ջ��Ԫ�� ջ��Ԫ��Ϊ��СԪ�ء�
            System.out.println("������" + x.id + "��" + x.avail + "��"
                    + (x.avail + d.get(i).time) + "��ʱ��η������ҵ" + d.get(i).id);
            x.avail += d.get(i).time;
            if (x.avail > sum) {
                sum = x.avail;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // ��ͬ�Ĳ�������
        int[] a = { 0, 1, 14, 4, 16, 6, 5, 3 };
        // int[] a = { 0, 2, 14, 4, 16, 6, 5, 3 };
        // int[] a = { 0, 44, 14, 4 };
        int m = 3;// ������
        int sum = greedy(a, m);
        System.out.println("��ʱ��Ϊ��" + sum);
    }

}

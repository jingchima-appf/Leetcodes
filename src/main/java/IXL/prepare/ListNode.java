package IXL.prepare;

public class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode generate(int[] nums) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        for (int num : nums) {
            prev.next = new ListNode(num);
            prev = prev.next;
        }
        return dummy.next;
    }

    @Override
    public String toString() {
        ListNode cur = this;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.val).append(" ");
            cur = cur.next;
        }
        return sb.toString();
    }
}
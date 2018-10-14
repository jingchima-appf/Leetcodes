package recursionII;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
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

public class ReverseLinkedListInPairs {

    // Question 1: reverse singly-linked list in pairs

    public ListNode reverseInPairs(ListNode head) {

        // prev -> 6 -> null
        //         cur
        // prev -> 6 -> null

        // prev -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
        //        cur
        // prev -> 2 -> 1 -> 3 -> 4 ...
        // Assume cur.next != null
        // prev.next = cur.next;
        // ListNode next = cur.next.next;
        // prev.next.next = cur
        // cur.next = next;
        // prev = cur;
        // cur = next;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        ListNode next;
        while (cur != null && cur.next != null) {
            prev.next = cur.next;
            next = cur.next.next;
            cur.next.next = cur;
            cur.next = next;
            prev = cur;
            cur = next;
        }
        return dummy.next;
    }


    // Question 2: reverse nodes in K group

    public ListNode reverseKGroup(ListNode head, int k) {
        // prev -> 1 -> 2 -> 3 -> ... -> k -> k+1 -> k+2
        // prev -> k -> ... -> 1 -> k+1 -> k+2 -> ...
        //                    prev

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev != null) {
            ListNode next = prev.next;
            prev.next = reverseKNodes(prev.next, k);
            if (prev.next == next) {
                break;
            }
            prev = next;
        }
        return dummy.next;
    }

    // return null if there's no need to reverse => WRONG! Because we use prev.next = null, this will cut the list
    // 这个接口的设计不好，应该把prev的传进来，直接进行操作。
    private ListNode reverseKNodes(ListNode head, int k) {
        ListNode cur = head;
        int count = 0; // init should be 0 because we haven't check cur yet.
        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }
        if (count < k) {
            return head; // no enough nodes, should stop
        }
        ListNode dummy = new ListNode(0);
        dummy.next = null;
        cur = head;
        count = 0;
        while (count < k) {
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
            count++;
        }
        head.next = cur; // forgot to connect with the latter nodes.
        return dummy.next; // return newHead;
    }

    // 用这种做法比较好：

    //
    // prev -> 1 -> 2 -> 3 ... -> k-1 -> k -> k+1 -> k+2
    // prev -> k -> ...               -> 1 -> k+1 -> k+2
    //                                  prev
    // return prev.next
    //
    // if the number < k, just return the last node as prev
    // prev -> 1 -> 2 -> 3 ... -> k - m -> null
    //                             prev -> null


    public ListNode reverseInKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (prev != null) {
            prev = reverseKNodesII(prev, k);
        }
        return dummy.next;
    }

    /*
    @param: prev != null
    @param: k >= 1
    @return: the last node of the reverse linked list or return null if there's no enough nodes
     */
    private ListNode reverseKNodesII(ListNode prev, int k) {
        ListNode head = prev.next;
        ListNode cur = head;
        int count = 0;
        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }
        if (count < k) {
            return null;
        }
        cur = prev.next;
        count = 0;
        prev.next = null;
        ListNode next;
        while (count < k) {
            next = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            cur = next;
            count++;
        }
        // don't forget to connect to the rest of the nodes
        head.next = cur;
        return head; // return the last node of new list
    }

    private static ListNode create(int[] nums) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        for (int num: nums) {
            prev.next = new ListNode(num);
            prev = prev.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ReverseLinkedListInPairs instance = new ReverseLinkedListInPairs();
        ListNode head = create(new int[] {1, 2, 3, 4, 5});
        System.out.println(instance.reverseInKGroup(head,2));
    }
}

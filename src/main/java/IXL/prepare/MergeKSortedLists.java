//package IXL.prepare;
//
//import java.util.Comparator;
//import java.util.PriorityQueue;
//
//
//public class MergeKSortedLists {
//
//    public ListNode mergeKSortedLists(ListNode[] lists) {
//        ListNode dummy = new ListNode(0);
//        ListNode prev = dummy;
//        PriorityQueue<ListNode> pq = new PriorityQueue<>(11, new Comparator<ListNode>() {
//            @Override
//            public int compare(ListNode node1, ListNode node2) {
//                if (node1.val == node2.val) {
//                    return 0;
//                }
//                return node1.val < node2.val ? -1 : 1;
//            }
//        }); // minHeap
//        for (ListNode head: lists) {
//            if (head != null) {
//                pq.offer(head);
//            }
//        }
//        while (!pq.isEmpty()) {
//            prev.next = pq.poll();
//            prev = prev.next;
//            if (prev.next != null) {
//                pq.offer(prev.next);
//            }
//        }
//        return dummy.next;
//    }
//
//    public static void main(String[] args) {
//        MergeKSortedLists instance = new MergeKSortedLists();
//        int[][] nums = {{1, 3, 5, 7, 8}, {2, 4, 8, 10, 12, 14}, {3, 7}, {}};
//        // 1 2 3 3 4 5 7 7 8 8 10 12 14 100 10000
//        ListNode[] lists = new ListNode[nums.length];
//        for (int i = 0; i < lists.length; i++) {
//            lists[i] = ListNode.generate(nums[i]);
//            System.out.println(lists[i]);
//        }
//        System.out.println(instance.mergeKSortedLists(lists));
//    }
//}

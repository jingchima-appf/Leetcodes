//package IXL.prepare;
//
//public class LinkedListPalindrome {
//    // assumption: input is allowed to be modified
//    public boolean isPalindrome(ListNode head) {
//        if (head == null || head.next == null) {
//            return true;
//        }
//        // find the middle node 1 2 .  1 2 1
//
//        ListNode slow = head;
//        ListNode fast = head;
//        while (fast != null && fast.next != null) {
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        ListNode secondHead = slow;
//        if (fast != null) {
//            secondHead = secondHead.next;
//        }
//
//        // reverse the 2nd part
//        secondHead = reverse(secondHead);
//
//        // compare two parts
//        while (head != slow) { // slow is the exclusive end for the first part!! not null !!!!!!
//            if (head.val != secondHead.val) {
//                return false;
//            }
//            head = head.next;
//            secondHead = secondHead.next;
//        }
//        return true;
//    }
//
//    private ListNode reverse(ListNode secondHead) {
//        ListNode dummy = new ListNode(0);
//        while (secondHead != null) {
//            ListNode tmp = secondHead.next;
//            secondHead.next = dummy.next;
//            dummy.next = secondHead;
//            secondHead = tmp;
//        }
//        return dummy.next;
//    }
//}

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
    You are given the heads of two sorted linked lists list1 and list2.

        Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

        Return the head of the merged linked list.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/*
- approach: make a third linked list. use while loop where it will run until both lists point to null. include edge
            case got one or two empty lists. LISTS ALREADY SORTED. don't worry about things like (5,2,4,6,1)

- what won't work: can't simply check each respective "index" value and then order those two. suppose we had two lists
{1,2,3,4,5} and {5,6,7,8,10}. simply taking the respective indexes and ordering would result int {1,5,2,6, etc.}

- will need a way to save the value. supposing two lists A and B and merge list C. If A(1) < B(1), then C(1) = A(1).
    B(1) is saved. if A(2) is < B(1), then C(2) = A(2), if not C(2) = B(1) and A(2) now saved as the temp.
- to achieve this, the while loop code block must say. temp = 0. If A(current) < B(current) -> C(1) = A(current).temp
= B(current) then A(current) = A.next. A second if block will be the opposite if B is less.
- a second edge case for equivalent values to be included in the loop

 */

public class Main {
    public static void main(String[] args) {

        LinkedList<ListNode> firstList = new LinkedList<>();
        LinkedList<ListNode> secondList = new LinkedList<>();

        firstList.add(new ListNode(1));
        firstList.add(new ListNode(3));
        firstList.add(new ListNode(4));

        secondList.add(new ListNode(2));
        secondList.add(new ListNode(5));

//        System.out.println(firstList.getLast().val);


        System.out.println(new Solution().mergeTwoLists(firstList.getFirst(), secondList.getFirst()));

    }
}


class Solution {
//    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        System.out.println(list2.val); //value is showing here
//        LinkedList<ListNode> mergedList = new LinkedList<>();
//
//        while(list1 != null && list2 != null){
//            System.out.println(list1.val); //both values printing here
//            System.out.println(list2.val);
//            if (list1.val < list2.val){ // but when comparison happens, nothing works
//                mergedList.add(list1);
//                list1 = list1.next;
//            } else if (list2.val < list1.val){
//                mergedList.add(list2);
//                list2 = list2.next;
//            } else{
//                mergedList.add(list1);
//                mergedList.add(list2);
//            }
//        }
//        System.out.println((mergedList.size()));
//        return mergedList.getFirst();
//    }

// this solution is similar to what i was trying to do but no bugs and more efficient. Instead of 'creating' a new linked
    // list and using an inbuilt method, this person used a stored variable of a negative value as the head to ensure
    // ordering. technically the flaw is a new value was added that was not in either sorted list. this does well with
    // breaking the loop though when one list reaches null and then simply links the remainder of the non-null list
    // to the merged list. This also avoids inbuilt List method ".add", so can go either way i imagine eithere using
    // new merged list and inbuilt features or not but needing the extra value
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode prehead = new ListNode(-1);
//        ListNode cur = prehead;
//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                cur.next = l1;
//                l1 = l1.next;
//            } else {
//                cur.next = l2;
//                l2 = l2.next;
//            }
//            cur = cur.next;
//        }
//
//        cur.next = l1 == null ? l2 : l1; // ternary, if l1 == null, then current.next = l2, else l1
//        return prehead.next;
//    }

   // dario says he feels like ternary is a little sloppy and the while criteria should be || based not &&.


    //here is the recursion approach that is not intuitive for me, but does avoid creating a new value and runtime is
    // equivalent. recursion probably more accurate in this case
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if(list1!=null && list2!=null){
            if(list1.val<list2.val){
                list1.next=mergeTwoLists(list1.next,list2);
                return list1;
            }
            else{
                list2.next=mergeTwoLists(list1,list2.next);
                return list2;
            }
        }
        if(list1==null)
            return list2;
        return list1;
    }
}



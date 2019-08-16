package test.lcz.com.list;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: 廖灿中
 * @Email: liaocanzhong@seengene.com
 * @Date: 2019-08-08
 */
public class ListTest {
    public static Node createLinkedList(List<Integer> list) {
        if (list.isEmpty()) {
            return null;
        }
        Node headNode = new Node(list.get(0));
        Node tempNode = createLinkedList(list.subList(1, list.size()));
        headNode.setNext(tempNode);
        return headNode;
    }

    //测试方便的打印函数
    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.getData());
            System.out.print(" ");
            node = node.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node nodePoint = createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 6));
        Node nodeStack = createLinkedList(Arrays.asList(1, 2, 3, 4, 5));
        Node node = createLinkedList(Arrays.asList(1, 2, 3, 4, 5));
        Node circleNode = createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5));
        Node circleNodeTwo = createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5, 5, 4, 3, 2, 1));
        Node insert = createLinkedList(Arrays.asList(1, 2, 3, 4, 5, 7));
        printReversing(nodePoint);
        printList(nodePoint);
        printList(nodeStack);
        printList(node);

        Node midNode = findMiddleNode(nodePoint);
        System.out.println(midNode.getData());
        System.out.println(isCircleLinkedListNode(circleNode));
        System.out.println(isCircleLinkedListNode(circleNodeTwo));
        insetNode(insert, 6);
        insetNode(insert, 2);
        printList(insert);
        deleteLastKth(insert, 4);
        printList(insert);
        printList(mergeTwoLists(nodePoint, insert));

        Node resNodePoint = reverseListNodeByPoint(nodePoint);
        Node resNodeStack = reverserLinkedListByStack(nodeStack);
        Node resNode = reverserLinkedList(node);

        printList(resNodePoint);
        printList(resNodeStack);
        printList(resNode);
    }

    //双指针方式
    public static Node reverseListNodeByPoint(Node head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.getNext() == null) {
            return head;
        }
        //前一个节点指针
        Node preNode = null;
        //当前节点指针
        Node curNode = head;
        while (curNode != null) {
            Node nextNode = curNode.getNext();//nextNode 指向下一个节点
            curNode.setNext(preNode);//将当前节点next域指向前一个节点
            preNode = curNode;//preNode 指针向后移动
            curNode = nextNode;//curNode指针向后移动
        }
        return preNode;
    }

    //链表反转递归方法
    public static Node reverserLinkedList(Node node) {
        if (node.getNext() == null || node == null) {
            return node;
        }
        Node newData = reverserLinkedList(node.getNext());
        node.getNext().setNext(node);
        node.setNext(null);
        return newData;
    }

    // 使用栈
    public static Node reverserLinkedListByStack(Node node) {
        Stack<Node> nodeStack = new Stack<>();
        Node head = null;
        //存入栈中，模拟递归开始的栈状态
        while (node != null) {
            nodeStack.push(node);
            node = node.getNext();
        }
        //特殊处理第一个栈顶元素（也就是反转前的最后一个元素，因为它位于最后，不需要反转，如果它参与下面的while， 因为它的下一个节点为空，如果getNode()， 那么为空指针异常）
        if ((!nodeStack.isEmpty())) {
            head = nodeStack.pop();
        }
        //排除以后就可以快乐的循环
        while (!nodeStack.isEmpty()) {
            Node tempNode = nodeStack.pop();
            tempNode.getNext().setNext(tempNode);
            tempNode.setNext(null);
        }
        return head;
    }

    //找出链表的中间节点
    public static Node findMiddleNode(Node head) {
        if (head == null) {
            return head;
        }
        Node slow = head, fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    /***
     * 用快慢指针判断链表是否有环
     * @param head
     * @return
     */
    public static boolean isCircleLinkedListNode(Node head) {
        if (head == null) {
            return false;
        }
        Node slow = head, fast = head;
        if (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /***
     * 将某个元素插入排序链表中
     * @param head
     * @param target
     * @return
     */
    public static Node insetNode(Node head, int target) {
        Node targetNode = new Node(target, null);
        if (head == null || target <= (int) head.getData()) {
            targetNode.setNext(head);
            return targetNode;
        }
        Node cur = head;
        while (cur != null) {
            if (cur.getNext() == null || target <= (int) cur.getNext().getData()) {
                targetNode.setNext(cur.getNext());
                cur.setNext(targetNode);
                break;
            }
            cur = cur.getNext();
        }
        return head;
    }

    // 删除倒数第K个结点
    public static Node deleteLastKth(Node list, int k) {
        Node fast = list;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.getNext();
            ++i;
        }
        if (fast == null) return list;
        Node slow = list;
        Node prev = null;

        while (fast.getNext() != null) {
            fast = fast.getNext();
            prev = slow;
            slow = slow.getNext();
        }

        if (prev == null) {
            list = list.getNext();
        } else {
            prev.setNext(prev.getNext().getNext());
        }
        return list;
    }

    public static Node mergeTwoLists(Node la, Node lb) {
        if (la == null) return lb;
        if (lb == null) return la;

        Node head = null;
        if ((int) la.getData() <= (int) lb.getData()) {
            head = la;
            head.setNext(mergeTwoLists(la.getNext(), lb));
        } else {
            head = lb;
            head.setNext(mergeTwoLists(la, lb.getNext()));
        }
        return head;
    }

    //从尾到头打印链表（递归和非递归）
    public static void printReversing(Node head) {
        //利用一个栈
        Stack stack = new Stack();
        //将链表的结点压入
        while (head != null) {
            stack.push(head);
            head = head.getNext();
        }
        Node popNode;
        System.out.println("printReversing stack " + stack.empty());
        while (!stack.isEmpty()) {
            //获得最上面的元素
            popNode = (Node) stack.pop();
            //打印
            System.out.print(popNode.getData() + " ");
        }
        System.out.println("printReversing stack end ");
    }

    //求环中相遇结点
    public static Node cycleNode(Node head) {
        //链表为空则返回null
        if (head == null)
            return null;
        Node first = head;
        Node second = head;
        while (first != null && first.getNext() != null) {
            first = first.getNext().getNext();
            second = second.getNext();
            //两指针相遇，则返回相遇的结点
            if (first == second)
                return first;
        }
        //链表无环，则返回null
        return null;
    }

    public static int getCycleLength(Node head) {
        Node node = cycleNode(head);
        //node为空则代表链表无环
        if (node == null)
            return 0;
        int length = 1;
        Node current = node.getNext();
        //再次相遇则循环结束
        while (current != node) {
            length++;
            current = current.getNext();
        }
        return length;
    }

    //给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
    public static Node EntryNodeOfLoop(Node pHead) {
        if (pHead.getNext() == null || pHead.getNext().getNext() == null)
            return null;
        Node slow = pHead.getNext();
        Node fast = pHead.getNext().getNext();
        while (fast != null) {
            if (fast == slow) {
                fast = pHead;
                while (fast != slow) {
                    fast = fast.getNext();
                    slow = slow.getNext();
                }
                return fast;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return null;
    }

    //给定单链表的头指针和一个结点指针，定一个函数在时间复杂度为O(1)删除链表结点
    public static void DeleteNode(Node pListHead, Node pToBeDeleted) {
        if (pListHead == null || pToBeDeleted == null)
            return;

        if (pToBeDeleted.getNext() != null) {
            Node pNext = pToBeDeleted.getNext();
            pToBeDeleted.setNext(pNext.getNext());
            pToBeDeleted.setData(pNext.getData());
        } else { //待删除节点为尾节点
            Node pTemp = pListHead;
            while (pTemp.getNext() != pToBeDeleted) {
                pTemp = pTemp.getNext();
            }
            pTemp.setNext(null);
        }
    }

    //输入两个单链表，找出他们的第一个公共结点。
    public static Node FindFirstCommonNode(Node pHead1, Node pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        int count1 = 0;
        Node p1 = pHead1;
        while (p1 != null) {
            p1 = p1.getNext();
            count1++;
        }
        int count2 = 0;
        Node p2 = pHead2;
        while (p2 != null) {
            p2 = p2.getNext();
            count2++;
        }
        int flag = count1 - count2;
        if (flag > 0) {
            while (flag > 0) {
                pHead1 = pHead1.getNext();
                flag--;
            }
            while (pHead1 != pHead2) {
                pHead1 = pHead1.getNext();
                pHead2 = pHead2.getNext();
            }
            return pHead1;
        }
        if (flag <= 0) {
            while (flag < 0) {
                pHead2 = pHead2.getNext();
                flag++;
            }
            while (pHead1 != pHead2) {
                pHead2 = pHead2.getNext();
                pHead1 = pHead1.getNext();
            }
            return pHead1;
        }
        return null;
    }
}

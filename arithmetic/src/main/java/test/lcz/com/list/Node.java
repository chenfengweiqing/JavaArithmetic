package test.lcz.com.list;

/**
 * @Author: 廖灿中
 * @Email: liaocanzhong@seengene.com
 * @Date: 2019-08-08
 */
public class Node {
    private Object data;//数据域
    private Node next;//指针域

    public Node(Object data){
        this.data = data;
    }

    public Node(Object data,Node next){
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

package bobo.interview;



public class Review {

    public static void main(String[] args) {

        Node<Integer>node1=new Node<>(0,null );
        Node<Integer>node2=new Node<>(1, node1);
        Node<Integer>node3=new Node<>(2, node2);
        Node<Integer>node4=new Node<>(3, node3);
        Node<Integer>node5=new Node<>(4, node4);
        Node<Integer>node6=new Node<>(5, node5);

        LinkedList<Integer>linkedList = new LinkedList<>();

        linkedList.head=node6;

        Node<Integer>currNode=linkedList.head;
        for(int i=0;i<6;i++){
            System.out.println(currNode.element);
            currNode=currNode.next;
        }
        linkedList.reverse();
        currNode=linkedList.head;
        for(int i=0;i<6;i++){
            System.out.println(currNode.element);
            currNode=currNode.next;
        }

    }


}

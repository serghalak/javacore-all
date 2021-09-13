package bobo.interview;

public class LinkedList<T> {
    public Node<T>head;

    public void reverse(){

        if(head != null && head.next != null){
            Node<T>current=head;
            Node<T>next=null;
            Node<T>previous=null;

            while(current.next != null){
                next=current.next;
                current.next=previous;
                //next.next=current;
                previous=current;
                current=next;
            }
            current.next=previous;
            head=current;
            //previous=head;
        }
    }
}

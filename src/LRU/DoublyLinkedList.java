package LRU;

public class DoublyLinkedList {
    private final Node head, tail;

    public DoublyLinkedList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void moveToHead(Node node) {
        remove(node);
        addToHead(node);
    }

    public Node removeTail() {
        Node tailPrev = tail.prev;
        remove(tailPrev);
        return tailPrev;
    }

    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

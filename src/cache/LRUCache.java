package cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private final int capacity;
    private final Map<Integer, Node> cache;
    private final DoublyLinkedList dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dll = new DoublyLinkedList();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        dll.moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            dll.moveToHead(node);
        } else {
            if (cache.size() >= capacity) {
                Node tail = dll.removeTail();
                cache.remove(tail.key);
            }
            Node newNode = new Node(key, value);
            dll.addToHead(newNode);
            cache.put(key, newNode);
        }
    }

    // Metodo para remover uma chave específica do cache
    public void remove(int key) {
        if (!cache.containsKey(key)) {
            System.out.println("Chave não encontrada no cache.");
            return;
        }
        Node node = cache.get(key);
        dll.remove(node); // Remove da lista encadeada
        cache.remove(key); // Remove do HashMap
        System.out.println("Chave " + key + " removida do cache.");
    }
}

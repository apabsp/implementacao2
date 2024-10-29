package LFU;

import java.util.*;

public class LFUCache {
    private final int capacity;
    private int minFrequency;
    private final Map<Integer, CacheNode> cache;
    private final Map<Integer, LinkedHashSet<CacheNode>> frequencyMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1; // Chave não encontrada
        }
        CacheNode node = cache.get(key);
        updateFrequency(node); // Atualiza a frequência
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            CacheNode node = cache.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            if (cache.size() >= capacity) {
                removeLFUNode();
            }
            CacheNode newNode = new CacheNode(key, value);
            cache.put(key, newNode);
            frequencyMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(newNode);
            minFrequency = 1;
        }
    }

    private void updateFrequency(CacheNode node) {
        int freq = node.frequency;
        frequencyMap.get(freq).remove(node);

        if (freq == minFrequency && frequencyMap.get(freq).isEmpty()) {
            minFrequency++;
        }

        node.frequency++;
        frequencyMap.computeIfAbsent(node.frequency, k -> new LinkedHashSet<>()).add(node);
    }

    private void removeLFUNode() {
        LinkedHashSet<CacheNode> nodes = frequencyMap.get(minFrequency);
        CacheNode nodeToRemove = nodes.iterator().next();

        nodes.remove(nodeToRemove);
        if (nodes.isEmpty()) {
            frequencyMap.remove(minFrequency);
        }
        cache.remove(nodeToRemove.key);
    }

    public void displayCache() {
        System.out.println("Cache Atual:");
        for (CacheNode node : cache.values()) {
            System.out.println("Chave: " + node.key + ", Valor: " + node.value + ", Frequência: " + node.frequency);
        }
    }
}

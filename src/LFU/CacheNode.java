package LFU;

public class CacheNode {
    public int key, value, frequency;

    public CacheNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.frequency = 1; // A frequência inicial é 1
    }
}

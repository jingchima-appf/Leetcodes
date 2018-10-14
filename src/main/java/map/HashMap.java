package map;

public class HashMap<K, V> {

    public static class Entry<K, V> {
        private final K key; // have to be final
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) { // non-static no need to add generic types
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private int size;
    private Entry<K, V>[] array;
    private static final int INIT_CAPACITY = 5;
    private static final double THRESHOLD = 0.75;

    public HashMap() {
        size = 0;
        array = (Entry<K, V>[]) new Entry[INIT_CAPACITY];
    }

    public V get(K key) {
        int idx = getIdx(key);
        Entry<K, V> cur = array[idx];
        while (cur != null) {
            if (keysAreEqual(cur.key, key)) {
                return cur.getValue();
            }
            cur = cur.next;
        }
        return null;
    }

    public V put(K key, V value) {
        double currentLoadFactor = (double) size / array.length;
        if (currentLoadFactor >= THRESHOLD) {
            expandAndRehash();
        }
        int idx = getIdx(key);
        Entry<K, V> cur = array[idx];
        while (cur != null) {
            if (keysAreEqual(cur.getKey(), key)) {
                V oldValue = cur.getValue();
                cur.setValue(value);
                return oldValue;
            }
            cur = cur.next;
        }
        Entry<K, V> newEntry = new Entry<>(key, value);
        // inserting into head!
        newEntry.next = array[idx];
        array[idx] = newEntry;
        size++;
        return null;
    }

    public V remove(K key) {
        int idx = getIdx(key);
        Entry<K, V> head = array[idx];
        Entry<K, V> dummy = new Entry<>(key, null);
        dummy.next = head;
        Entry<K, V> prev = dummy;
        Entry<K, V> cur = head;
        while (cur != null && !keysAreEqual(cur.key, key)) {
            prev = cur;
            cur = cur.next;
        }
        if (cur == null) {
            return null;
        } else {
            size--; // found!! size has to decrease!!
            V tmp = cur.getValue();
            prev.next = cur.next;
            return tmp;
        }
    }

    public boolean containsKey(K key) {
        int idx = getIdx(key);
        Entry<K, V> cur = array[idx];
        while (cur != null) {
            if (keysAreEqual(cur.key, key)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (Entry<K, V> entry: array) {
            Entry<K, V> cur = entry;
            while (cur != null) {
                sb.append(cur.getKey() + "=>" + cur.getValue() + ",");
                cur = cur.next;
            }
        }
        return sb.deleteCharAt(sb.length()-1).append(" }").toString();
    }

    private void expandAndRehash() {
        Entry<K, V>[] old = array;
        array = (Entry<K, V>[]) new Entry[array.length * 2];
        for (Entry head: old) {
            Entry<K, V> cur = head;
            while (cur != null) {
                Entry<K, V> tmpNext = cur.next;
                int newIdx = getIdx(cur.getKey());
                // inserting into the head is OK!!
                cur.next = array[newIdx];
                array[newIdx] = cur;
                cur = tmpNext;
            }
        }
    }

    private boolean keysAreEqual(K key1, K key2) { // private helper method doesn't need to be static
        return key1 == key2 || (key1 != null && key1.equals(key2));
    }

    private int getIdx(K key) {
        int hashNumber = getHash(key);
        return hashNumber % array.length;
    }

    private int getHash(K key) {
        if (key == null) {
            return 0;
        }
        int hashNumber = key.hashCode();
        return hashNumber & 0x7FFFFFFF;
    }


    // concurrency
    // distributed hash map

    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1,1);
        hashMap.put(2,2);
        hashMap.put(3,3);
        System.out.println(hashMap);
        hashMap.put(1,10);
        hashMap.put(4,10);
        hashMap.put(5,10);
        hashMap.put(6,10);
        hashMap.put(7,10);
        hashMap.put(8,10);
        hashMap.put(9,10);
        System.out.println(hashMap);
    }
}


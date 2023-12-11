package com.day10;

import java.util.LinkedList;

class MyMapNode<K, V> {
    K key;
    V value;

    /*
     * @desc:contructor for this class
     * @params:K object params
     * @return:none
     */
    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class HashMap<K, V> {

    private final int numBuckets;
    private final LinkedList<MyMapNode<K, V>>[] buckets;

    /*
     * @desc: constructor for MyHashMap class
     * @params: capacity - the initial capacity of the hash map
     * @return:none
     */
    public HashMap(int numBuckets) {
        this.numBuckets = numBuckets;
        this.buckets = new LinkedList[numBuckets];

        // Initialize each bucket with an empty linked list
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /*
     * @desc: private method to calculate the bucket index for a given key
     * @params: key - the key for which the bucket index is calculated
     * @return: the calculated bucket index
     */
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % numBuckets);
    }

    // Method to get the value associated with a key
    private MyMapNode<K, V> getNodeByKey(K key) {
        int index = getBucketIndex(key);
        LinkedList<MyMapNode<K, V>> bucket = buckets[index];

        for (MyMapNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node;
            }
        }

        return null; // Key not found
    }

    /*
     * @desc: inserts a key into the hash map or increments the frequency if the key is already present
     * @params: key - the key to be inserted
     * @return:none
     */
    public void addWord(K word) {
        MyMapNode<K, V> node = getNodeByKey(word);

        if (node == null) {
            // Word not present, add a new node to the bucket
            int index = getBucketIndex(word);
            LinkedList<MyMapNode<K, V>> bucket = buckets[index];
            bucket.add(new MyMapNode<>(word, (V) (Integer) 1));
        } else {
            // Word already present, update the frequency
            node.value = (V) (Integer) ((Integer) node.value + 1);
        }
    }

    /*
     * @desc: retrieves the frequency of a given key in the hash map
     * @params: key - the key for which the frequency is retrieved
     * @return: the frequency of the key, or null if the key is not found
     */
    public void displayWordFrequency() {
        for (int i = 0; i < numBuckets; i++) {
            LinkedList<MyMapNode<K, V>> bucket = buckets[i];
            for (MyMapNode<K, V> node : bucket) {
                System.out.println("Word: " + node.key + ", Frequency: " + node.value);
            }
        }
    }

    public static void main(String[] args) {
        String sentence = "To be or not to be";
        String[] words = sentence.toLowerCase().split("\\s+");

        // Create a WordFrequencyCounter with 10 buckets
        HashMap wordFrequencyCounter = new HashMap(10);

        // Add words to the frequency counter
        for (String word : words) {
            wordFrequencyCounter.addWord(word);
        }

        // Display the frequency of words
        wordFrequencyCounter.displayWordFrequency();
    }
}
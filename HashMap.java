package com.day10;

import java.util.LinkedList;

class MyMapNode<K, V> {
    K key;
    V value;

    public MyMapNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class HashMap<K, V> {

    private final int numBuckets;
    private final LinkedList<MyMapNode<K, V>>[] buckets;

    public HashMap(int numBuckets) {
        this.numBuckets = numBuckets;
        this.buckets = new LinkedList[numBuckets];

        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % numBuckets);
    }

    private MyMapNode<K, V> getNodeByKey(K key) {
        int index = getBucketIndex(key);
        LinkedList<MyMapNode<K, V>> bucket = buckets[index];

        for (MyMapNode<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node;
            }
        }

        return null;
    }

    public void addWord(K word) {
        MyMapNode<K, V> node = getNodeByKey(word);

        if (node == null) {
            int index = getBucketIndex(word);
            LinkedList<MyMapNode<K, V>> bucket = buckets[index];
            bucket.add(new MyMapNode<>(word, (V) (Integer) 1));
        } else {
            node.value = (V) (Integer) ((Integer) node.value + 1);
        }
    }

    public void displayWordFrequency() {
        for (int i = 0; i < numBuckets; i++) {
            LinkedList<MyMapNode<K, V>> bucket = buckets[i];
            for (MyMapNode<K, V> node : bucket) {
                System.out.println("Word: " + node.key + ", Frequency: " + node.value);
            }
        }
    }

    public static void main(String[] args) {
        String paragraph = "Paranoids are not paranoid because they are paranoid but "
                + "because they keep putting themselves deliberately into paranoid avoidable situations";

        // Split the paragraph into words
        String[] words = paragraph.toLowerCase().split("\\s+");

        // Create a WordFrequencyCounter with 10 buckets
        HashMap<String, Integer> wordFrequencyCounter = new HashMap<>(10);

        // Add words to the frequency counter
        for (String word : words) {
            // Remove punctuation marks from the word
            word = word.replaceAll("[^a-zA-Z]", "");
            if (!word.isEmpty()) {
                wordFrequencyCounter.addWord(word);
            }
        }

        // Display the frequency of words
        wordFrequencyCounter.displayWordFrequency();
    }
}
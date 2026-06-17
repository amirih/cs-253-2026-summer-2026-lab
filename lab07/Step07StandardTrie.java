package lab07;

import java.util.HashMap;
import java.util.Map;

/**
 * Lab Step 07: Standard trie.
 * Concept: store words character by character for fast word and prefix lookup.
 */
public class Step07StandardTrie {
    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean wordEnd;
    }

    private final Node root = new Node();

    public void insert(String word) {
        if (word == null)
            throw new IllegalArgumentException("word must not be null");
        Node current = root;
        for (char c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, key -> new Node());
        }
        current.wordEnd = true;
    }

    public boolean containsWord(String word) {
        Node node = findNode(word);
        return node != null && node.wordEnd;
    }

    public boolean containsPrefix(String prefix) {
        return findNode(prefix) != null;
    }

    private Node findNode(String text) {
        if (text == null)
            throw new IllegalArgumentException("text must not be null");
        Node current = root;
        for (char c : text.toCharArray()) {
            current = current.children.get(c);
            if (current == null)
                return null;
        }
        return current;
    }

    public static void main(String[] args) {
        Step07StandardTrie trie = new Step07StandardTrie();
        for (String word : new String[] { "bear", "bell", "bid", "bull", "buy", "sell", "stock", "stop" }) {
            trie.insert(word);
        }
        System.out.println(trie.containsWord("bear"));
        System.out.println(trie.containsWord("be"));
        System.out.println(trie.containsPrefix("be"));
    }
}

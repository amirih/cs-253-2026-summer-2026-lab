package lab07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lab Step 08: Trie autocomplete.
 * Concept: after finding the prefix node, DFS through descendants to list
 * suggestions.
 */
public class Step08TrieAutocomplete {
    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean wordEnd;
    }

    private final Node root = new Node();

    public void insert(String word) {
        Node current = root;
        for (char c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, key -> new Node());
        }
        current.wordEnd = true;
    }

    public List<String> suggestions(String prefix) {
        List<String> result = new ArrayList<>();
        Node node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null)
                return result;
        }
        collect(node, new StringBuilder(prefix), result);
        return result;
    }

    private void collect(Node node, StringBuilder current, List<String> result) {
        if (node.wordEnd)
            result.add(current.toString());
        for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
            current.append(entry.getKey());
            collect(entry.getValue(), current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        Step08TrieAutocomplete trie = new Step08TrieAutocomplete();
        for (String word : new String[] { "bear", "bell", "bid", "bull", "buy", "sell", "stock", "stop" }) {
            trie.insert(word);
        }
        System.out.println(trie.suggestions("b"));
        System.out.println(trie.suggestions("sto"));
    }
}

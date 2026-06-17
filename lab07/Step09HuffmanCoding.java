package lab07;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Lab Step 09: Huffman coding.
 * Concept: greedily merge the two lowest-frequency trees, then encode by
 * root-to-leaf paths.
 */
public class Step09HuffmanCoding {
    private static class Node implements Comparable<Node> {
        char ch;
        int frequency;
        Node left;
        Node right;

        Node(char ch, int frequency) {
            this.ch = ch;
            this.frequency = frequency;
        }

        Node(Node left, Node right) {
            this.ch = '\0';
            this.frequency = left.frequency + right.frequency;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    public static Map<Character, Integer> frequencies(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }

    private static Node buildTree(Map<Character, Integer> freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        if (pq.isEmpty())
            return null;
        while (pq.size() > 1) {
            Node left = pq.remove();
            Node right = pq.remove();
            pq.add(new Node(left, right));
        }
        return pq.remove();
    }

    public static Map<Character, String> buildCodes(String text) {
        Node root = buildTree(frequencies(text));
        Map<Character, String> codes = new HashMap<>();
        buildCodes(root, "", codes);
        return codes;
    }

    private static void buildCodes(Node node, String path, Map<Character, String> codes) {
        if (node == null)
            return;
        if (node.isLeaf()) {
            codes.put(node.ch, path.isEmpty() ? "0" : path);
            return;
        }
        buildCodes(node.left, path + "0", codes);
        buildCodes(node.right, path + "1", codes);
    }

    public static String encode(String text, Map<Character, String> codes) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codes.get(c));
        }
        return encoded.toString();
    }

    public static void main(String[] args) {
        String message = "DEAACAAAAABA";
        Map<Character, String> codes = buildCodes(message);
        System.out.println(codes);
        System.out.println(encode(message, codes));
    }
}

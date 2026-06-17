package lab07;

import java.util.HashMap;
import java.util.Map;

/**
 * Lab Step 03: Boyer-Moore preprocessing.
 * Concept: build the last-occurrence table used by the character-jump
 * heuristic.
 */
public class Step03BoyerMooreLastOccurrence {
    public static Map<Character, Integer> buildLastOccurrence(String pattern) {
        if (pattern == null)
            throw new IllegalArgumentException("pattern must not be null");
        Map<Character, Integer> last = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            last.put(pattern.charAt(i), i);
        }
        return last;
    }

    public static void main(String[] args) {
        Map<Character, Integer> last = buildLastOccurrence("abacab");
        System.out.println(last);
        System.out.println("last index of a = " + last.get('a'));
        System.out.println("last index of z = " + last.getOrDefault('z', -1));
    }
}

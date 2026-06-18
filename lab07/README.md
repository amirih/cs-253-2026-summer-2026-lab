# Lab 07: String Algorithms and Text Data Structures

Lab07 focuses on algorithms for searching text, storing words, compressing characters, and comparing sequences.

## Concepts

| Step | Concept                           | Simple description                                                                          | Example                                                              |
| ---- | --------------------------------- | ------------------------------------------------------------------------------------------- | -------------------------------------------------------------------- |
| 1    | Brute-force string search         | Try every possible starting position in the text until the pattern matches.                 | Text `banana`, pattern `ana` gives first match at index `1`.         |
| 2    | All pattern matches               | Find every place where the pattern appears, including overlapping matches.                  | Text `aaaaa`, pattern `aa` gives `[0, 1, 2, 3]`.                     |
| 3    | Boyer-Moore last-occurrence table | Store the last position of each character in the pattern to help skip ahead.                | Pattern `abacab`: last `a = 4`, last `b = 5`, last `c = 3`.          |
| 4    | Boyer-Moore search                | Compare from right to left and use mismatches to jump forward.                              | Text `the quick brown fox`, pattern `brown` starts at index `10`.    |
| 5    | KMP failure function              | Build an array showing how much of the pattern can be reused after mismatch.                | Pattern `abacab` gives failure array `[0, 0, 1, 0, 1, 2]`.           |
| 6    | KMP search                        | Use the failure function so the search does not restart from scratch.                       | Text `aaaaab`, pattern `aaab` starts at index `2`.                   |
| 7    | Standard trie                     | Store words character by character in a tree.                                               | Insert `bear` and `bell`; prefix `be` exists.                        |
| 8    | Trie autocomplete                 | Find the prefix node, then collect all words below it.                                      | Words `stock`, `stop`, `sell`; prefix `sto` gives `stock`, `stop`.   |
| 9    | Huffman coding                    | Give shorter binary codes to frequent characters and longer codes to rare characters.       | In `AAAAABBC`, `A` should get a short code because it appears often. |
| 10   | Longest common subsequence        | Find the longest sequence appearing in both strings in order, not necessarily contiguously. | `ABCBDAB` and `BDCABA` have LCS length `4`, such as `BCBA`.          |

## Big ideas

- Brute force is simple but may repeat unnecessary comparisons.
- Boyer-Moore skips ahead using mismatched characters.
- KMP avoids repeating work using the failure array.
- Tries make prefix lookup and autocomplete efficient.
- Huffman coding is a greedy compression algorithm.
- LCS uses dynamic programming to compare sequences.

## Quick examples

### Brute-force search

```text
text:    banana
pattern: ana
match at index 1
```

### Trie

```text
Words: bear, bell
Prefix be exists
Word be does not have to exist as a complete word
```

### LCS

```text
ABCBDAB
BDCABA
One common subsequence: BCBA
Length: 4
```

## Study order

Study brute-force search first, then Boyer-Moore and KMP. After that, study tries, Huffman coding, and LCS.

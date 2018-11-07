package dataStructures;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    char key;
    Map<Character, TrieNode> children;
    boolean isWord;

    public TrieNode(char key) {
        this.key = key;
        this.children = new HashMap<>();
        this.isWord = false;
    }

    public TrieNode(char key, boolean isWord) {
        this.key = key;
        this.children = new HashMap<>();
        this.isWord = isWord;
    }
}

public class Trie {
    TrieNode root;
    public Trie() {
        this.root = new TrieNode('\0');
    }

    public boolean search(String target) {
        TrieNode cur = this.root;
        for (int i = 0; i < target.length(); i++) {
            char curChar = target.charAt(i);
            TrieNode next = cur.children.get(curChar);
            if (next == null) {
                return false;
            }
            cur = next;
        }
        return cur.isWord;
    }

    public void insert(String input) {
        TrieNode cur = this.root;
        for (int i = 0; i < input.length(); i++) {
            char curChar = input.charAt(i);
            TrieNode next = cur.children.get(curChar);
            if (next == null) {
                next = new TrieNode(curChar);
                cur.children.put(curChar, next);
            }
            cur = next;
        }
        cur.isWord = true;
    }

    public boolean delete(String input) {
        TrieNode cur = this.root;
        for (int i = 0; i < input.length(); i++) {
            char curChar = input.charAt(i);
            TrieNode next = cur.children.get(curChar);
            if (next == null) {
                return false;
            }
            if (i == input.length() - 1 && next.isWord) {
                cur.children.remove(curChar); // not enough, you may need
            }
        }
        return false;
    }
}

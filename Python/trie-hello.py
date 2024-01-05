"""Showcase the "trie" data structure.

A trie is a tree-based data structure designed for efficient storage and
retrieval of strings, where each node represents a character, forming paths that
represent keys.

You can use tries for spell checking and autocompletion tasks.

Tries are a special case of Radix trees. Radix trees can encompass tries but
specifically emphasize nodes representing sequences of characters, leading to
potential compression benefits compared to tries.

author: andreasl
"""


class TrieNode:
    """Node for the Trie data structure."""

    def __init__(self) -> None:
        """Initialize an empty TrieNode."""
        self.children: dict[str, TrieNode] = {}
        self.is_end_of_word: bool = False


class Trie:
    """Trie data structure."""

    def __init__(self) -> None:
        """Initialize an empty Trie."""
        self.root: TrieNode = TrieNode()

    def insert(self, word: str) -> None:
        """Insert a word into the Trie."""
        node: TrieNode = self.root
        for char in word:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        node.is_end_of_word = True

    def search(self, word: str) -> bool:
        """Search for a word in the Trie."""
        node: TrieNode = self.root
        for char in word:
            if char not in node.children:
                return False
            node = node.children[char]
        return node.is_end_of_word

    def any_word_start_with_prefix(self, prefix: str) -> bool:
        """Check if any word in the Trie starts with the given prefix."""
        node: TrieNode = self.root
        for char in prefix:
            if char not in node.children:
                return False
            node = node.children[char]
        return True


def main() -> None:
    """Trie experiment main function."""
    trie: Trie = Trie()
    trie.insert("apple")
    trie.insert("app")
    trie.insert("apricot")

    print(f'{trie.search("app")=}')  # True
    print(f'{trie.search("orange")=}')  # False
    print(f'{trie.any_word_start_with_prefix("ap")=}')  # True
    print(f'{trie.any_word_start_with_prefix("at")=}')  # False


if __name__ == "__main__":
    main()

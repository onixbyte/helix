/**
 * Represents a single node within a tree structure.
 *
 * Each node holds a data item of type T and an array of its children.
 *
 * @template T the type of the data item stored in the node
 */
export interface TreeNode<T> {
  /**
   * The actual data item contained within this node.
   */
  item: T
  /**
   * A list of child nodes belonging to this node.
   *
   * This array will be empty if the node is a leaf node.
   */
  children: TreeNode<T>[]
}

/**
 * Defines a tree structure, which is simply a top-level tree node.
 *
 * It represents the root of the hierarchical data structure.
 *
 * @template T the type of the data items within the tree
 */
export type Tree<T> = TreeNode<T>

/**
 * Defines a forest, which is an array of zero or more disjoint trees.
 *
 * Each element in the array is the root of an independent tree.
 *
 * @template T the type of the data items within the trees
 */
export type Forest<T> = TreeNode<T>[]

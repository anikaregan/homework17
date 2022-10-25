package com.anika.homework;

class BST_Class {
    //node class that defines BST node
    // BST root node 
    Node root;

    // Constructor for BST =>initial empty tree
    BST_Class() {
        root = null;
    }

    //delete a node from BST
    void deleteKey(int key) {
        delete_Recursive(root, key);
    }

    Node delete_Recursive(Node root, int key) {
        //tree is empty
        if (root == null) {
            return null;
        }

        // Find the node and its parent.
        Node parent = null;
        Node current = root;
        while (current != null && current.key != key) {
            parent = current;
            if (key < current.key)
                current = current.left;
            else
                current = current.right;
        }
        // Delete the node (if any) and return the removed item.
        if (current == null) // no such key
            return null;
        else {
            deleteNode(current, parent);
            return current;
        }
    }

    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left == null || toDelete.right == null) {
            // Cases 1 and 2
            Node toDeleteChild;

            if (toDelete.left != null)
                toDeleteChild = toDelete.left;
            else
                toDeleteChild = toDelete.right;
            // both Cases are included. In case 1 toDeleteChild==null
            if (toDelete == root)
                root = toDeleteChild;
            else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            }
            else {
                parent.right = toDeleteChild;
            }
        } else {
            Node replacementParent = toDelete;
            Node replacement = toDelete.right;
            while (replacement.left != null) {
                replacementParent = replacement;
                replacement = replacement.left;
            }
            toDelete.key = replacement.key;
            deleteNode(replacement, replacementParent);
        }
    }

    int minValue(Node root)  {
        if (root == null) {
            //for when there is an empty tree
            return 0;
        }
        if (root.left == null) {
            return root.key;
        }
        return minValue(root.left);
    }

    // insert a node in BST
    void insert(int key)  {
        root = insert_Recursive(root, key);
    }

    //recursive insert function
    Node insert_Recursive(Node root, int key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }
        //traverse the tree
        if (key < root.key)     //insert in the left subtree
            root.left = insert_Recursive(root.left, key);
        else if (key > root.key)    //insert in the right subtree
            root.right = insert_Recursive(root.right, key);
        // return pointer
        return root;
    }

    boolean search(int key)  {
        if (root == null) {
            return false;
        }
        //check key at the node
        if (root.key == key) {
            return true;
        } else if (root.key > key) {
            root = root.left;
            //will check node to the left if key is smaller than root key
            search(key);
        } else {
            root = root.right;
            //will check node to the left if key is greater than root key
            search(key);
        }
        return false;
    }

    //PostOrder Traversal - Left:Right:rootNode (LRn)
    void postOrder(Node node)  {
        if (node == null)
            return;
        //first traverse left subtree recursively
        postOrder(node.left);

        //next traverse right subtree recursively
        postOrder(node.right);

        //then go for root node
        System.out.print(node.key + " ");

    }
    // InOrder Traversal - Left:rootNode:Right (LnR)
    void inOrder(Node node)  {
        if (node == null)
            return;
        //first traverse left subtree recursively
        inOrder(node.left);

        //then go for root node
        System.out.print(node.key + " ");

        //next traverse right subtree recursively
        inOrder(node.right);
    }
    //PreOrder Traversal - rootNode:Left:Right (nLR)
    void preOrder(Node node)  {
        if (node == null)
            return;

        //then go for root node
        System.out.print(node.key + " ");

        //first traverse left subtree recursively
        preOrder(node.left);

        //next traverse right subtree recursively
        preOrder(node.right);
    }

    // Wrappers for recursive functions
    void postOrder_traversal()  {
        postOrder(root);  }
    void inOrder_traversal() {
        inOrder(root);   }
    void preOrder_traversal() {
        preOrder(root);  }
    void minValue_traversal() {
        int smallest = minValue(root);
        System.out.println(smallest);
    }

}

class Main{
    public static void main(String[] args)  {
        //create a BST object
        BST_Class bst = new BST_Class();
        //insert data into BST
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);
        //print the BST
        System.out.println("The BST Created with input data(Left-root-right):");
        bst.inOrder_traversal();
        //prints the smallest value of the BST
        System.out.println("\nThe smallest value found in the BST:");
        bst.minValue_traversal();

        //delete leaf node
        System.out.println("\nThe BST after Delete 12(leaf node):");
        bst.deleteKey(12);
        bst.inOrder_traversal();
        //delete the node with one child
        System.out.println("\nThe BST after Delete 90 (node with 1 child):");
        bst.deleteKey(90);
        bst.inOrder_traversal();

        //delete node with two children  
        System.out.println("\nThe BST after Delete 45 (Node with two children):");
        bst.deleteKey(45);
        bst.inOrder_traversal();
        //search a key in the BST
        boolean ret_val = bst.search (50);
        System.out.println("\nKey 50 found in BST: " + ret_val );
        ret_val = bst.search (12);
        System.out.println("Key 12 found in BST: " + ret_val );

        //construct a BST
        BST_Class tree = new BST_Class();
        tree.root = new Node(45);
        tree.root.left = new Node(10);
        tree.root.right = new Node(90);
        tree.root.left.left = new Node(7);
        tree.root.left.right = new Node(12);
        //PreOrder Traversal
        System.out.println("BST => PreOrder Traversal:");
        tree.preOrder_traversal();
        //InOrder Traversal
        System.out.println("\nBST => InOrder Traversal:");
        tree.inOrder_traversal();
        //PostOrder Traversal
        System.out.println("\nBST => PostOrder Traversal:");
        tree.postOrder_traversal();

    }
}
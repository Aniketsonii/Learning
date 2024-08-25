// Problem Statement:Reverse a given binary tree

#include <iostream>
using namespace std;
 
// Creating Node to store value and pointers to left and right child
struct Node
{
    int value;
    Node *left, *right;
 
    Node(int data)
    {
        this->value = data;
        this->left = this->right = nullptr;
    }
};
 
// Function to perform preorder traversal on a given binary tree
void preorder(Node* root)
{
    if (root == nullptr) {
        return;
    }
    cout << root->value << " ";
    preorder(root->left);
    preorder(root->right);
}
 
// Function to invert a given binary tree using preorder traversal
void invertBinaryTree(Node* root)
{
    // base case: if the tree is empty
    if (root == nullptr) {
        return;
    }
 
    // swap left subtree with right subtree
    swap(root->left, root->right);
 
    // invert left subtree
    invertBinaryTree(root->left);
 
    // invert right subtree
    invertBinaryTree(root->right);
}
 
int main()
{
    /* Using the following tree as example
              1
            /   \
           /     \
          2       3
         / \     / \
        4   5   6   7
    */
 
    Node* root = new Node(1);
    root->left = new Node(2);
    root->right = new Node(3);
    root->left->left = new Node(4);
    root->left->right = new Node(5);
    root->right->left = new Node(6);
    root->right->right = new Node(7);
 
    invertBinaryTree(root);
    preorder(root);
 
    return 0;
}

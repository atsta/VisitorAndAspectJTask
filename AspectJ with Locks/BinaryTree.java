class Node {
    int value;
    Node left;
    Node right;
    Node parent;

    public Node(int value, Node rigt, Node left) {
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public Node(int value) {
        this(value, null, null);
    }
}

public class BinaryTree {
        Node root;

        public BinaryTree() {
            this.root = null;
        }

        private Node search(Node t, int value) {
            if (t == null)
                return t;
            if (t.value == value) return t;
            if (value < t.value)
                return search(t.left, value);
            else
                return search(t.right, value);
        }

        public Node search(int value) {
            return search(root, value);
        }

        private Node insert(Node t, Node p, int value) {
            if (t == null) {
                t = new Node(value);
            } else {
                if (value < t.value)
                    t.left = insert(t.left, t, value);
                else
                    t.right = insert(t.right, t, value);
            }
            return t;
        }

        public void insert(int value) {
            root = insert(root, null, value);
        }

        private void replace(Node a, Node b) {
            if (a.parent == null)
                root = b;
            else if (a == a.parent.left)
                a.parent.left = b;
            else
                a.parent.right = b;
            if (b != null)
                b.parent = a.parent;
        }

        private void remove(Node t, int value) {
            if (t == null)
                return;
            if (value < t.value)
                remove(t.left, value);
            else if (value > t.value)
                remove(t.right, value);
            else if (t.left != null && t.right != null) {
                Node m = t.right;
                while (m.left != null)
                    m = m.left;
                t.value = m.value;
                t.value = m.value;
                replace(m, m.right);
            } else if (t.left != null) {
                replace(t, t.left);
            } else if (t.right != null) {
                replace(t, t.right);
            } else {
                replace(t, null);
            }
        }

        public void remove(int value) {
            remove(root, value);
        }
    }
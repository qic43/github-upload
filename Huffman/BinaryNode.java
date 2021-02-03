public class BinaryNode<T> {
    BinaryNode<T> left;
    BinaryNode<T> right;
    T value;

    public BinaryNode() {
    }

    public BinaryNode(T value) {
        this.value = value;
    }

    public BinaryNode(BinaryNode<T> left, BinaryNode<T> right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
}

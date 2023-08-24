//Jeevan Anand
//jxa200027

public class Node<AnyType extends Comparable>
{
    private AnyType data;
    private Node<AnyType> left;
    private Node<AnyType> right;

    public Node(AnyType data)
    {
        this.data = data;
        left = right = null;
    }

    public AnyType getData()
    {
        return data;
    }
    public void setData(AnyType data)
    {
        this.data = data;
    }

    public Node<AnyType> getLeft()
    {
        return left;
    }
    public void setLeft(Node<AnyType> left)
    {
        this.left = left;
    }

    public Node<AnyType> getRight()
    {
        return right;
    }
    public void setRight(Node<AnyType> right)
    {
        this.right = right;
    }
}

//Jeevan Anand
//jxa200027

import java.io.PrintStream;

public class BinTree <AnyType extends Comparable>
{
    private Node<AnyType> root;

    public AnyType search(AnyType x)
    {
        return search(x,root);
    }
    private AnyType search(AnyType x, Node<AnyType> t)
    {
        if(t == null)
            return null;
        if(x.compareTo(t.getData()) == 0)
        {
            return t.getData();
        }
        else if(x.compareTo(t.getData()) < 0)
        {
            return search(x,t.getLeft());
        }
        else
            return search(x, t.getRight());
    }

    public void insert(AnyType x)
    {
        root = insert(x,root);
    }
    private Node<AnyType> insert(AnyType x,Node<AnyType> t)
    {
        if(t == null)
        {
            return new Node<>(x);
        }

        if(x.compareTo(t.getData()) < 0)
        {
            t.setLeft(insert(x,t.getLeft()));
        }
        else
        {
            t.setRight(insert(x,t.getRight()));
        }
        return t;
    }
    private Node<AnyType> Leftmost(Node<AnyType> t)
    {
        if(t == null)
            return null;
        else if(t.getLeft() == null)
            return t;
        return Leftmost(t.getLeft());
    }

    public void delete(AnyType x)
    {
        root = delete(x,root);
    }
    private Node<AnyType> delete(AnyType x,Node<AnyType> t)
    {
        if(t == null)
            return t;
        if(x.compareTo(t.getData()) < 0 )
        {
            t.setLeft(delete(x,t.getLeft()));
        }
        else if(x.compareTo(t.getData()) > 0)
        {
            t.setRight(delete(x,t.getRight()));
        }
        else if(t.getLeft() != null && t.getRight() !=null)
        {
            t.setData(Leftmost(t.getRight()).getData());
            t.setRight(delete(t.getData(),t.getRight()));
        }
        else
        {
            if(t.getLeft() == null)
                return t.getRight();
            else if(t.getRight() == null)
                return t.getLeft();
        }
        return t;
    }

    public void print_Tree()
    {
        print_Tree(root);
    }
    private void print_Tree(Node<AnyType> t)
    {
        if(t != null)
        {
            print_Tree(t.getLeft());
            System.out.println(t.getData());
            print_Tree(t.getRight());
        }
    }

    public void print_tree_preOrder(PrintStream f)
    {
        print_tree_preOrder(f, root);
    }
    private void print_tree_preOrder(PrintStream f, Node<AnyType> t)
    {
        if(t != null)
        {
            f.println(t.getData().toString());
            print_tree_preOrder(f,t.getRight());
            print_tree_preOrder(f,t.getLeft());

        }
    }

    public Node<AnyType> getRoot()
    {
        return root;
    }

}

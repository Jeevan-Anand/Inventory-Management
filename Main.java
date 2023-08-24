//Jeevan Anand
//jxa200027

import java.io.*;
import java.util.Scanner;

public class Main
{
    //helps locate the actual node, so you can directly access and edit it
    public static Node<DVD> locate(Node<DVD> name, Node<DVD> r)
    {
        if(name == null)
            return null;
        if(name.getData().compareTo(r.getData()) == 0)
        {
            return r;
        }
        else if(name.getData().compareTo(r.getData()) < 0)
        {
            return locate(name,r.getLeft());
        }
        else
            return locate(name, r.getRight());
    }

    public static void main(String []args) throws IOException
    {
        BinTree<DVD> dvd_inventory = new BinTree<>();
        Scanner scnr = new Scanner(System.in);
        Scanner fromFile;
        String fileInv;
        String fileTrans;
        BufferedReader readFile;

        System.out.println("Enter Inventory File Name: ");
        fileInv = scnr.next();
        String line;
        String []dataArr;
        String operand = "";
        readFile = new BufferedReader(new FileReader(fileInv));
        fromFile = new Scanner(readFile);

        PrintStream output = new PrintStream("inventory.out");

        //creates the intial BST from the inventory
        while (fromFile.hasNextLine())
        {
            line = fromFile.nextLine();
            dataArr = line.split(",");
            dvd_inventory.insert(new DVD(dataArr[0].substring(1,dataArr[0].length()-1),Integer.parseInt(dataArr[1]),Integer.parseInt(dataArr[2])));
        }
        fromFile.close();

        System.out.println("Enter Transaction File Name: ");
        fileTrans = scnr.next();
        readFile = new BufferedReader(new FileReader(fileTrans));
        fromFile = new Scanner(readFile);


        while(fromFile.hasNextLine())
        {
            //get the intial operand (add,remove,rent,return)
            if(fromFile.hasNext())
                operand = fromFile.next();


            line = fromFile.nextLine();
            dataArr = line.split(",");

            //adds the new amount of available movies to rent of a specific movie
            if(operand.equals("add"))
            {
                DVD temp = new DVD(dataArr[0].substring(2,dataArr[0].length()-2),0,0);
                Node<DVD> name = new Node<>(temp);
                Node<DVD> t;
                if(dvd_inventory.search(temp) != null)
                {
                    t = locate(name,dvd_inventory.getRoot());
                    locate(name,dvd_inventory.getRoot()).getData().setAvailable( locate(name,dvd_inventory.getRoot()).getData().getAvailable() + Integer.parseInt(dataArr[1]));
                }
                else
                {
                    dvd_inventory.insert(new DVD(dataArr[0].substring(2,dataArr[0].length()-1),Integer.parseInt(dataArr[1]),0));
                }
            }
            //removes the new amount of available movies to rent of a specific movie
            else if(operand.equals("remove"))
            {

                DVD temp = new DVD(dataArr[0].substring(2,dataArr[0].length()-2),0,0);
                Node<DVD> name = new Node<>(temp);
                if(dvd_inventory.search(temp) != null)
                {
                    if(locate(name,dvd_inventory.getRoot()).getData().getAvailable() - Integer.parseInt(dataArr[1]) <= 0 && locate(name,dvd_inventory.getRoot()).getData().getRented() <= 0)
                    {
                        dvd_inventory.delete(temp);
                    }
                    else
                        locate(name,dvd_inventory.getRoot()).getData().setAvailable( locate(name,dvd_inventory.getRoot()).getData().getAvailable() - Integer.parseInt(dataArr[1]));
                }
            }
            //Increases the amount of rented of a single movie and decreases the amount available by 1
            else if(operand.equals("rent"))
            {
                DVD temp = new DVD(dataArr[0].substring(2,dataArr[0].length()-2),0,0);
                Node<DVD> name = new Node<>(temp);
                if(dvd_inventory.search(temp) != null)
                {
                    locate(name,dvd_inventory.getRoot()).getData().setAvailable(locate(name,dvd_inventory.getRoot()).getData().getAvailable()-1);
                    locate(name,dvd_inventory.getRoot()).getData().setRented(locate(name,dvd_inventory.getRoot()).getData().getRented()+1);
                }

            }

            //Increases the amount of available of a single movie and decreases the amount of rented by 1
            else if(operand.equals("return"))
            {
                DVD temp = new DVD(dataArr[0].substring(2,dataArr[0].length()-2),0,0);
                Node<DVD> name = new Node<>(temp);
                if(dvd_inventory.search(temp) != null)
                {
                    locate(name, dvd_inventory.getRoot()).getData().setAvailable(locate(name, dvd_inventory.getRoot()).getData().getAvailable() + 1);
                    locate(name, dvd_inventory.getRoot()).getData().setRented(locate(name, dvd_inventory.getRoot()).getData().getRented() - 1);
                }
            }
        }

        fromFile.close();
        //prints to console
        dvd_inventory.print_Tree();
        //prints to file
        dvd_inventory.print_tree_preOrder(output);
    }



}

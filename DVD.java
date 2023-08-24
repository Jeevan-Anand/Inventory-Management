//Jeevan Anand
//jxa200027

public class DVD implements Comparable
{
    private String Title;
    private int Available;
    private int Rented;

    public DVD(String title, int available, int rented)
    {
        this.Title = title;
        this.Available = available;
        this.Rented = rented;
    }

    public String getTitle()
    {
        return Title;
    }
    public void setTitle(String title)
    {
        Title = title;
    }

    public int getAvailable()
    {
        return Available;
    }

    public void setAvailable(int available)
    {
        Available = available;
    }

    public int getRented()
    {
        return Rented;
    }

    public void setRented(int rented)
    {
        Rented = rented;
    }

    @Override
    public int compareTo(Object o)
    {
        if(o.toString().charAt(0) < Title.charAt(0) )
            return -1;
        else if(o.toString().charAt(0) > Title.charAt(0))
            return 1;
        return 0;
    }

    @Override
    public String toString()
    {
        String output;
        output = String.format("%-20s %d    %d",Title,Available,Rented);
        return output;
    }
}

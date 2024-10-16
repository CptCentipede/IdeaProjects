public class HurricaneRowData {
    //create instance variables for all columns of data
    private int year;
    private int ace;
    private int stormCount;
    private int hurrCount;
    private int majHurrCount;

    //Constructor
    public HurricaneRowData(int year, int ace, int stormCount, int hurrCount, int majHurrCount)
    {
        this.year = year;
        this.ace = ace;
        this.stormCount = stormCount;
        this.hurrCount = hurrCount;
        this.majHurrCount = majHurrCount;
    }

    //Getters
    public int getyear()
    {
        return this.year;
    }

    public int getace()
    {
        return this.ace;
    }

    public int getstormCount()
    {
        return this.stormCount;
    }

    public int gethurrCount()
    {
        return this.hurrCount;
    }

    public int getmajHurrCount()
    {
        return this.majHurrCount;
    }

    //create a toString method so that it will return something readable
    @Override
    public String toString()
    {
        return ""+year;
    }

}

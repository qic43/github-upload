public class teller extends SimEvent
{
    private double finish_time;
    private int ID;
    public teller(double finish, int teller_ID)
    {
        super(finish);
        ID=teller_ID;
        finish_time=finish;
    }
    public double getFinishtime()
    {
        return finish_time;
    }
    public int getID()
    {
        return ID;
    }
}
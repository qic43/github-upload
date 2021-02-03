public class custom extends SimEvent
{
    private CompletionLocEvent comple;
    private CompletionEvent comEvent;
    private ArrivalEvent AE;
    private double transcation_time;
    private int custom_ID;
    public custom(double arrive, double working_period, int loc)
    {
        super(working_period);
        transcation_time=working_period;
		AE=new ArrivalEvent(arrive);
        custom_ID=loc;
        
	}
    public void beginEvent(double timestamp, int teller_ID)
    {
        comple=new CompletionLocEvent(timestamp, teller_ID);
        comEvent=new CompletionEvent(timestamp+transcation_time);
    }
    public double getarrivetime()
    {
        return AE.get_e_time();
    }
    public double getfinishtime()
    {
        return comEvent.get_e_time();
    }
    public double getbegintime()
    {
        return comple.get_e_time();
    }
    public int getTellerID()
    {
        return comple.getLoc();
    }
    public String toString()
	{
		return AE.toString()+comple.toString();
	}
    public double getTranscation()
    {
        return transcation_time;
    }
    public int getCustomID()
    {
        return custom_ID;
    }

  
}
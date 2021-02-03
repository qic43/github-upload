import java.util.*;
import java.text.DecimalFormat;
import java.lang.*;
public class SimBank
{
    private int ntell,maxq,ncum,custom_ID=1,n=0,leave_people,no_wait=0;
    private boolean option,flag=false;
    private double hrs, arr_rate,t_min;
    private double pass_time,total_wait,sum_of_S_T;//time used for final cal
    private double begin_time,final_time,Mean_value,S_T_A,total_service;
    private int location,i=0;
    private long seed;
    private RandDist RD;
    private custom curr,curr3;
    private teller curr2;
    private LinkedList<custom> queue = new LinkedList<custom>();
    private PriorityQueue<teller> pq = new PriorityQueue<teller>();
    private Queue<custom> queueB = new PriorityQueue<custom>();//people who turned away
    private Queue<custom> queueC = new PriorityQueue<custom>();//all the people who comes
    private custom custom_information;
    private teller teller_information;
    private boolean flag1;
    private DecimalFormat df = new DecimalFormat("#.00");
    public SimBank(int number, boolean choose, double time, double arr, double min, int cap , long s)
    {
        ntell=number;
        maxq=cap;
        option=choose;
        hrs=time;
        arr_rate=arr;
        t_min=min;
        RD=new RandDist(s);
        custom_ID=0;
    }
    public void runSimulation()
    {
        if (option==true)
        {
            System.out.println();
            System.out.println("Customer  Arrival    Service  Queue  Teller  Time Serv  Time Cust  Time Serv  Time Spent");
            System.out.println("    Id      Time       Time     Loc    Loc     Begins      Waits      Ends       in Sys");                        
            System.out.println("--------  -------    -------  -----  ------  ---------  ---------  ---------  ----------");                    
            singlequeue();
           
        }
        else
        {
           flag=true;
            doublequeue();
        }
    }
    public void singlequeue()
    {
       while(pass_time<hrs*60)
       {
           
            double val = RD.exponential(arr_rate);//generate arrive_time in minutes
            double wval=RD.exponential(1/t_min);//service time
            val=val*60+pass_time;
            if(val<hrs*60)
            {
                pass_time=val;
                
                custom_information=new custom(val,wval,custom_ID);
                if (queue.size()<maxq)
                {
                    
                    queue.offer(custom_information);//custom wait in line
                }
                else
                {
                    queueB.offer(custom_information);//custom are lveaing
                }
                
           
                if(pq.size()<ntell)//case where teller is free
                {
                    curr=queue.poll();
                    curr.beginEvent(val,n);
                    teller_information=new teller(curr.getfinishtime(),curr.getTellerID());
                    pq.offer(teller_information);
                    pass_time=curr.getarrivetime();
                    total_service+=curr.getTranscation();
                    n++;
                    no_wait++;
                    queueC.offer(curr);
                    System.out.println("   "+curr.getCustomID()+"	   "+df.format(curr.getarrivetime())+"     "+df.format(curr.getTranscation())+"	  "+"-1"+"	   "+n+
                             "	   "+df.format(curr.getbegintime())+"	   "+df.format((curr.getbegintime()-curr.getarrivetime()))+
                            "	   "+df.format(curr.getfinishtime())+"	   "+df.format((curr.getfinishtime()-curr.getarrivetime())));
                }
                else
                {
                    curr2=pq.peek();
                    curr=queue.peekLast();
                    if (curr2.getFinishtime()<curr.getarrivetime())
                    {
                        curr2=pq.poll();//teller
                        curr=queue.poll();//custom
                        if(curr2.getFinishtime()<curr.getarrivetime())//case where custom does not need to wait
                        {
                            no_wait++;
                            curr.beginEvent(curr.getarrivetime(),curr2.getID());
                            teller_information=new teller(curr.getfinishtime(),curr.getTellerID());
                            pq.offer(teller_information);
                            queueC.offer(curr);
                            total_wait+=curr.getbegintime()-curr.getarrivetime();
                            total_service+=curr.getTranscation();
                            System.out.println("   "+curr.getCustomID()+"	   "+df.format(curr.getarrivetime())+"     "+df.format(curr.getTranscation())+"	  "+"-1"+"	   "+curr2.getID()+
                             "	   "+df.format(curr.getbegintime())+"	   "+df.format((curr.getbegintime()-curr.getarrivetime()))+
                            "	   "+df.format(curr.getfinishtime())+"	   "+df.format((curr.getfinishtime()-curr.getarrivetime())));
                        }
                        else//case where custom can do serive
                        {
                            curr.beginEvent(curr2.getFinishtime(),curr2.getID());
                            teller_information=new teller(curr.getfinishtime(),curr.getTellerID());
                            pq.offer(teller_information);
                            queueC.offer(curr);
                            total_wait+=curr.getbegintime()-curr.getarrivetime();
                            total_service+=curr.getTranscation();
                            System.out.println("   "+curr.getCustomID()+"	   "+df.format(curr.getarrivetime())+"     "+df.format(curr.getTranscation())+"	  "+"0"+"	   "+curr2.getID()+
                             "	   "+df.format(curr.getbegintime())+"	   "+df.format((curr.getbegintime()-curr.getarrivetime()))+
                            "	   "+df.format(curr.getfinishtime())+"	   "+df.format((curr.getfinishtime()-curr.getarrivetime())));
                        }
                    }
                    else
                    {
                        if(curr2.getFinishtime()<pass_time)
                        {
                            curr2=pq.poll();//teller
                            curr=queue.poll();//custom 
                            curr.beginEvent(pass_time,curr2.getID());
                            queueC.offer(curr);
                            teller_information=new teller(curr.getfinishtime(),curr.getTellerID());
                            pq.offer(teller_information);  
                            total_service+=curr.getTranscation();
                            System.out.println("   "+curr.getCustomID()+"	   "+df.format(curr.getarrivetime())+"     "+df.format(curr.getTranscation())+"	  "+"0"+"	   "+curr2.getID()+
                             "	   "+df.format(curr.getbegintime())+"	   "+df.format((curr.getbegintime()-curr.getarrivetime()))+
                            "	   "+df.format(curr.getfinishtime())+"	   "+df.format((curr.getfinishtime()-curr.getarrivetime())));
                        }
                       
                    }
                
                    
                }
                custom_ID++;
            }
            else
            {
                break;
            }
            
            
        }
    }
       

    public void doublequeue()
    {
        pass_time=0;
        custom_ID=1;
        LinkedList<custom> queue = new LinkedList<custom>();
        Queue <custom> Cqueue=new LinkedList<custom>();
        PriorityQueue<teller> pq = new PriorityQueue<teller>();
        Queue<custom> queueB = new PriorityQueue<custom>();//people who turned away
        Queue<custom> queueC = new PriorityQueue<custom>();//all people who waited.
        ArrayList<Queue<custom>>queues=new ArrayList<Queue<custom>>(ntell);
        System.out.println();
        while(pass_time<hrs*60)
        {
            double val = RD.exponential(arr_rate);//generate arrive_time in minutes
            double wval=RD.exponential(1/t_min);//service time
            val=val*60+pass_time;
            boolean flag4=true,flag2=false;//this flag figure out whether there is somebody wait front you.
            int size1=0,place=0,place2=0,q=0;//the size+where is the shorest line,place2 is the varirable define location who should leave,;
            custom_information=new custom(val,wval,custom_ID);
            pass_time=val;
            if(n<ntell)//case 1:full the first item in queue
            {
                Cqueue.offer(custom_information);//add the custom information into the first items
                custom_information.beginEvent(val,n);//begin the event at once
                pass_time=custom_information.getarrivetime();
                queues.add(n,Cqueue);
                n++;
                System.out.println("Custom ID "+custom_information.getCustomID()+" Arrive time "+
                custom_information.getarrivetime()+" service time "+custom_information.getTranscation()+" -1 "
                +" Teller ID "+ custom_information.getTellerID()+" Service begin time "+custom_information.getbegintime()+
                "  Time ends "+custom_information.getfinishtime()) ;
            }
            else// now at least one ahead
            {
                for (int n=0;n<queues.size();n++)//first, let people who finish their job leave first
                {
                    Cqueue=queues.get(n);
                    curr=Cqueue.poll();
                    if(curr.getfinishtime()<pass_time)//he should leave 
                    {
                        if(Cqueue.size()>0)//which means there should be somebody waiting behind him
                        {
                            curr=Cqueue.poll();
                            curr3=Cqueue.peek();
                            curr3.beginEvent(curr.getfinishtime(),curr.getTellerID());
                            System.out.println("Custom ID "+curr3.getCustomID()+" Arrive time "+
                            curr3.getarrivetime()+" service time "+curr3.getTranscation()+" -1 "
                            +" Teller ID "+ curr3.getTellerID()+" Service begin time "+curr3.getbegintime()+
                            "  Time ends "+curr3.getfinishtime());
                        }
                        else//he is the last in the line
                        {
                            flag4=false;
                        }
                    }
                }
                for (int n=0;n<queues.size();n++)//this for loop find which line has shortest people
                {
                    Cqueue=queues.get(n);
                    if(Cqueue.size()<maxq)
                    {
                        flag2=true;
                        if(n==0)
                        {
                            size1=Cqueue.size();
                            place=n;
                        }
                        else
                        {
                            if(size1>Cqueue.size())
                            {
                                size1=Cqueue.size();
                                place=n;
                            }
                        }
                    }
                }
                if(flag2==true)//which means there is not at maxq
                {
                    if (flag4==false)//which means this lucky guys go into an empty rows
                    {
                        Cqueue=queues.get(place);
                        custom_information.beginEvent(val,place);
                        Cqueue.offer(custom_information);
                        System.out.println("Custom ID "+custom_information.getCustomID()+" Arrive time "+
                custom_information.getarrivetime()+" service time "+custom_information.getTranscation()+" -1 "
                +" Teller ID "+ custom_information.getTellerID()+" Service begin time "+custom_information.getbegintime()+
                "  Time ends "+custom_information.getfinishtime());
                    }
                    else
                    {
                        Cqueue=queues.get(place);
                        Cqueue.offer(custom_information);
                    }
                    
                }
                else//this custom should leave
                {
                    queueB.offer(custom_information);
                }
            }
            
            custom_ID++;
            
        }
        
        
    }
    public void showResults()
    {
        System.out.println("Customers who did not stay:");
        System.out.println("Customer  Arrival    Service");
        System.out.println("    Id      Time       Time ");
        System.out.println("--------  -------    -------");
        leave_people=queueB.size();
        for (int i=0;i<queueB.size();i++)
        {
            curr=queueB.poll();
            System.out.println(df.format(curr.getCustomID())+"   "+df.format(curr.getarrivetime())+"  "+df.format(curr.getTranscation()));
        }
        if (flag==false)
        {
            System.out.println("Number of Queues: 1");
        }
        else
        {
            System.out.println("Number of Queues: "+ ntell);
        }
        System.out.println("Max number allowed to wait: "+maxq);
        System.out.println("Customer arrival rate (per hr): "+arr_rate);
        System.out.println("Customer service time (ave min): "+t_min);
        System.out.println("Number of customers arrived: "+custom_ID);
        System.out.println("Number of customers served: "+(custom_ID-leave_people));
        System.out.println("Num. Turned Away: "+leave_people );
        System.out.println("Num. who waited: "+(custom_ID-leave_people-no_wait));
        System.out.println("Average Wait: "+(total_wait/(custom_ID-leave_people))+" min");
        Mean_value=(total_wait/(custom_ID-leave_people));
        for (int i=0;i<queueC.size();i++)
        {
            curr=queueC.poll();
            sum_of_S_T+=((curr.getbegintime()-curr.getarrivetime())-Mean_value)*((curr.getbegintime()-curr.getarrivetime())-Mean_value);
        }
        S_T_A=Math.sqrt(sum_of_S_T/(custom_ID-leave_people));
        System.out.println("Std. Dev. Wait: "+ S_T_A);
        System.out.println("Ave. Service: "+(total_service/(custom_ID-leave_people)));
        System.out.println("Ave. Waiter Wait: ");
    }
    
}
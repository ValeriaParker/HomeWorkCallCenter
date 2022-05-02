package callcenter;

public class CallCenterSimulator extends Thread
{
    static boolean allAgentsAreBusy(ServiceAgent s1, ServiceAgent s2, ServiceAgent s3){
        return  s1.getStatus() != ServiceAgentStatus.FREE &&
                s2.getStatus() != ServiceAgentStatus.FREE &&
                s3.getStatus() != ServiceAgentStatus.FREE;
    }
    public static void main( String... args )
    {
        ServiceAgent sa1 = new ServiceAgent(1);
        ServiceAgent sa2 = new ServiceAgent(2);
        ServiceAgent sa3 = new ServiceAgent(3);
        sa1.start();
        sa2.start();
        sa3.start();

        CallGenerator cg = new CallGenerator();
        cg.start();
        //while (all agents are free)

        while (cg.getCounterCalls() <= 10){
           try
           {
               Thread.sleep(1000);
           }
           catch ( InterruptedException e )
           {
               e.printStackTrace();
           }

        }
        while (allAgentsAreBusy(sa1,sa2,sa3)){
            try
            {
                Thread.sleep(1000);
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }

        }
        sa1.stop();
        sa2.stop();
        sa3.stop();
        cg.stop();
    }
}

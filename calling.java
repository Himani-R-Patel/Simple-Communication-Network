
import java.util.*;

class calling  extends Thread
{
	
	List<String> listOfReceiver = null;
    exchange t;
	
   public calling(List<String> list , exchange te)
	{  
	   this.listOfReceiver = list;
	   t= te;
		
	} 
	 public void run()
	  {	 
		 
		 for(int i=0 ; i<listOfReceiver.size() ; i++)
		 { 
			 try
			 {
			   Thread.sleep((long)(Math.random() * 1000));
			 } 
			 catch (InterruptedException e)
			 {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			 
			 introMsg(listOfReceiver.get(i),this.getName(),System.currentTimeMillis());
		 }
//			 try 
//			  {
//				Thread.sleep((long)(Math.random() * 1000));
//			  } 
//			 catch (InterruptedException e) 
//			  {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			  }
//			  
//			  receiveMsg(this.getName(),listOfReceiver.get(i),System.currentTimeMillis());
			 
			  try 
			   {
				Thread.sleep(1000);
			   }
			  catch (InterruptedException e)
			   {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   }
			  
			  try
			  {
				t.stop(this.getName());
			  }
			  catch (InterruptedException e)
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
	  }
   
	private String receiveMsg(String sender, String receiver, long timestamp) {
		// TODO Auto-generated method stub
		try
		 {
		   Thread.sleep((long)(Math.random() * 1000));
		 } 
		 catch (InterruptedException e)
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		String replymessage = "reply";
		return replymessage;
		
	}
	private void introMsg(String sender, String receiver, long timestamp) {
		// TODO Auto-generated method stub
		t.intropaster(sender, receiver,timestamp);
		String k = receiveMsg(sender, receiver,timestamp);
		t.receivepaster(sender, receiver, timestamp);
		
		
	}
}
		
	
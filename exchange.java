import java.util.*;
import java.io.*;


public class exchange extends Thread {
	
  private LinkedHashMap<String, List<String>> data = null;
	
  public exchange(LinkedHashMap<String, List<String>> list) {
	  data = list;
	}
	
  public void run()
   {
	  
	  Set keys = data.keySet();
      Iterator itr = keys.iterator();
      calling c;
      String key;
     

      while(itr.hasNext())
      {
          key = (String)itr.next();
       
          System.out.println(key + " : "+ data.get(key));
          
          
      }
      System.out.println("");

      Iterator itr2 = keys.iterator();
      while(itr2.hasNext())
      {
    	  key = (String)itr2.next();

          	c = new calling (data.get(key),this);
			c.setName(key);
			c.start();
      }
		
	}

   public static void main(String a[])
         {
    	
    	 System.out.println("** Calls to be made **\n");
    	
    	 try {
        	
        	 String exchange =  System.getProperty("user.dir");
        	 BufferedReader  br = new BufferedReader( new FileReader(exchange+"/calls.txt"));
        	 String strLine;
        	 String str;
        	 LinkedHashMap<String, List<String>> list = new LinkedHashMap<String,List<String>>();
        	 
        	 while( (strLine = br.readLine()) != null)
        	 {
        	
        	    str = strLine.replaceAll("[{}\\[\\]. ]", "");
        		ArrayList<String> receiver = new ArrayList(Arrays.asList( str.split(",")));
        		
        		String caller = receiver.get(0);
        		receiver.remove(0);       		
        		list.put(caller,receiver);        		
        		
             }
        	
        	exchange t = new exchange(list);
      	    t.start();
     		
        	 
        }
    	catch (IOException e)
    	   {
            System.err.println("Unable to read the file: fileName");
           }
    	 }   
      
	public void intropaster(String receiver,String senderName, long timeStamp) {
		System.out.println(receiver +" received intro message from "+senderName+"[ "+ timeStamp+" ]");
	}
	
	public  void receivepaster(String senderName,String receiver, long timeStamp) {
		System.out.println(senderName+" received reply message from "+receiver +"[ "+ timeStamp+" ]");
	}
	
	
	
	int temp,temp2=0;
	public  void stop(String senderName) throws InterruptedException {
		
		 synchronized (data)
		 {
			if(temp<data.size()-1)
			{
				temp++;
				   try 
				   { data.wait(); }
				   catch(Exception e) {}
			}
			else {
				    data.notifyAll();
			     }}
			System.out.println("\nProcess "+ senderName +" has received no calls for 1 second, ending...\n");
			
			temp2++;
			if(temp2==temp+1)
			 {
				System.out.println("Master has received no reply for 1.5 seconds, ending...");
			 }
		
			}			
		//System.out.println("Process "+ senderName +" has received no calls for 1 second, ending...\n");
	}

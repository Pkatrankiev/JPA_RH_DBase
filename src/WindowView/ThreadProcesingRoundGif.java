package WindowView;

public class ThreadProcesingRoundGif extends Thread {
	   private Thread t;
	   private String threadName;
	   
	   public ThreadProcesingRoundGif( String name) {
	      threadName = name;
	     
	   }
	   
	   public void run() {
	        new TranscluentWindow();
	      
	   }
	   
	   public void start () {
	      
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
}

 
	


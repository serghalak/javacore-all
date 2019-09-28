package shild.ch11;

public class CurrentThreadDemo {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Thread t = Thread.currentThread();
//		System.out.println("Current thread: " + t);
//		t.setName("My Thread");
//		System.out.println("After name change of thread: " + t);
//		try{
//			for(int n=5;n>0;n--){
//				System.out.println(n);
//				Thread.sleep(1000);
//			}
//		}catch(InterruptedException e){
//			System.out.println("Main thread was interrupted");
//		}
//	}
	
	
//	public static void main(String args[]) {
//	    NewThread t1 = new NewThread("first"); // create a new thread
//	    NewThread t2 = new NewThread("second"); // create a new thread
//	    NewThread t3 = new NewThread("thirt"); // create a new thread
//
//	    //nt.t.start(); // Start the thread
//	    	t1.t.start();
//	    	t2.t.start();
//	    	t3.t.start();
//	    try {
////	      for(int i = 5; i > 0; i--) {
////	        System.out.println("Main Thread: " + i);
////	        Thread.sleep(1000);
////	      }
//	    	t1.t.join();
//	 	    t2.t.join();
//	 	    t3.t.join();
//	    } catch (InterruptedException e) {
//	      System.out.println("Main thread interrupted.");
//	    }
//	    
//	   
//	    System.out.println("Main thread exiting.");
//	  }
	
	
		  public static void main(String args[]) {
//		    Callme target1 = new Callme();
//		    Callme target2= new Callme();
//		    Callme target3 = new Callme();
		    
		    Callme target = new Callme();
		    
		    Caller ob1 = new Caller(target, "Hello");
		    Caller ob2 = new Caller(target, "Synchronized");
		    Caller ob3 = new Caller(target, "World");

		    // Start the threads.
		    ob1.t.start();
		    ob2.t.start();
		    ob3.t.start();

		    // wait for threads to end
		    try {
		      ob1.t.join();
		      ob2.t.join();
		      ob3.t.join();
		    } catch(InterruptedException e) {
		      System.out.println("Interrupted");
		    }
		  }
		

}


class NewThread implements Runnable {
	String name;
	  Thread t;

	  NewThread(String threadName) {
	    // Create a new, second thread
		  name=threadName;
	    t = new Thread(this, name);
	    System.out.println("Child thread: " + t);
	  }

	  // This is the entry point for the second thread.
	  public void run() {
	    try {
	      for(int i = 5; i > 0; i--) {
	        System.out.println("Child Thread: " + i);
	        Thread.sleep(500);
	      }
	    } catch (InterruptedException e) {
	      System.out.println("Child interrupted.");
	    }
	    System.out.println("Exiting child thread.");
	  }
	}

//class NewThread extends Thread {
//	 // Thread t;
//
//	  NewThread() {
//	    // Create a new, second thread
//	    //t = new Thread(this, "Demo Thread");
//	    super("Demo thread");
//		  start();
//		System.out.println("Child thread: " + this);
//	  }
//
//	  // This is the entry point for the second thread.
//	  public void run() {
//	    try {
//	      for(int i = 5; i > 0; i--) {
//	        System.out.println("Child Thread: " + i);
//	        this.sleep(500);
//	      }
//	    } catch (InterruptedException e) {
//	      System.out.println("Child interrupted.");
//	    }
//	    System.out.println("Exiting child thread.");
//	  }
//	}

class Callme {
	/*synchronized*/  void call(String msg) {
	    System.out.print("[" + msg);
	    try {
	      Thread.sleep(1000);
	    } catch(InterruptedException e) {
	      System.out.println("Interrupted");
	    }
	    System.out.println("]");
	  }
	}

class Caller implements Runnable {
	  String msg;
	  Callme target;
	  Thread t;

	  public Caller(Callme targ, String s) {
	    target = targ;
	    msg = s;
	    t = new Thread(this);
	  }

	  public void run() {
		  synchronized(target){
			  target.call(msg);
		  }
	  }
	}




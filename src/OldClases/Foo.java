package OldClases;

public class Foo implements Runnable {
    private volatile int value;

    @Override
    public void run() {
      for (int i = 0; i < 300000000; i++) {
    	  value=i;
	}
    }

    public int getValue() {
        return value;
    }
}

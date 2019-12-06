package MainWindow;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.SwingWorker;

public class Task extends SwingWorker<Void, Void>  {
    private MySuperAwesomeLongRunningTask taskToBeDone;
    private JButton startButton;
    
    public Task(MySuperAwesomeLongRunningTask taskToBeDone, JButton startButtonView) {
        this.taskToBeDone = taskToBeDone;
        startButton = startButtonView;
    }

    /*
     * Main task. Executed in background thread.
     */
    @Override
    public Void doInBackground() {
        taskToBeDone.performTask((Progressable) this);
        return null;
    }

    /*
     * Executed in event dispatch thread
     */
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        startButton.setEnabled(true);
  
    }
}

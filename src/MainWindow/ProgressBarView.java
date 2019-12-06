package MainWindow;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;


public class ProgressBarView   {

	
	private static JProgressBar progressBar;
	private static JButton startButton;
	private static Task task;
	private static MySuperAwesomeLongRunningTask runningTask;


	public static void ProgressBarView(JButton startButonView, JProgressBar progressBarView) {
	
		
		startButton = startButonView;
		startButton.setActionCommand("start");
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startButton.setEnabled(false);
				System.out.println("************************");
				// Instances of javax.swing.SwingWorker are not reusuable, so
				// we create new instances as needed.
				runningTask = new MySuperAwesomeLongRunningTask();
				task = new Task(runningTask, startButton);
				task.addPropertyChangeListener(new PropertyChangeListener(){

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if ("progress".equals(evt.getPropertyName())) {
							int progress = (Integer) evt.getNewValue();
							progressBar.setValue(progress);

						}
					}
					
				});
				task.execute();
				
			}
			
		});

		progressBar = progressBarView;

		
	}


}

	



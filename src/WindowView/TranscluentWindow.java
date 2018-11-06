package WindowView;

import java.awt.Color;
import java.util.concurrent.CountDownLatch;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class TranscluentWindow {
	private JWindow frame;

	public TranscluentWindow() {
		frame = new JWindow();
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);
				frame.setBackground(new Color(0, 0, 0, 0));
				JPanel pan = new JPanel();
				pan.setOpaque(false);
				frame.setContentPane(pan);
				ImageIcon pic = new ImageIcon("ajax-loader (3).gif");
				frame.getContentPane().add(new JLabel(pic));
				frame.pack();
				frame.setLocationRelativeTo(null);

			}

	public void StopWindow() {
		SwingUtilities.getWindowAncestor(frame).dispose();
	
		}
	
} 

package WindowView;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import GlobalVariable.GlobalPathForIcons;

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
				ImageIcon pic = new ImageIcon(getClass().getClassLoader().getResource(GlobalPathForIcons.get_destination_ajaxLoader()));
				frame.getContentPane().add(new JLabel(pic));
				frame.pack();
				frame.setLocationRelativeTo(null);

			}

	public void StopWindow() {
		SwingUtilities.getWindowAncestor(frame).dispose();
	
		}
	
} 

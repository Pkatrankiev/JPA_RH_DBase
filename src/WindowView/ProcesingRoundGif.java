package WindowView;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ProcesingRoundGif extends JFrame {

	public ProcesingRoundGif(){
	JFrame frame = new JFrame();
	setAlwaysOnTop(true);

	frame.setBackground(new Color(0, 0, 0, 0));
	JPanel pan = new JPanel();
	pan.setOpaque(false);
	frame.setContentPane(pan);
	ImageIcon pic = new ImageIcon("ajax-loader (3).gif");
	frame.getContentPane().add(new JLabel(pic));
	frame.pack();
	frame.setLocationRelativeTo(null);
	}
}

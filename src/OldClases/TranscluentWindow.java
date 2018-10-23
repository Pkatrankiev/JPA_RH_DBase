package OldClases;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TranscluentWindow {

	public static void main(String[] args) {
		new TranscluentWindow();
	}

	public TranscluentWindow() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
				}

				JWindow frame = new JWindow();
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);
				frame.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							SwingUtilities.getWindowAncestor(e.getComponent()).dispose();
						}
					}
				});
				frame.setBackground(new Color(0, 0, 0, 0));
				JPanel pan = new JPanel();
				pan.setOpaque(false);
				frame.setContentPane(pan);
				ImageIcon pic = new ImageIcon("loading.gif");
				frame.getContentPane().add(new JLabel(pic));
				frame.pack();
				frame.setLocationRelativeTo(null);

			}
		});
	}
	public void StartWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}}
	
//	public class TranslucentPane4 extends JPanel {
//
//		public TranslucentPane4() {
//			setOpaque(false);
//		}
//
//		@Override
//		protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
//
//			Graphics2D g2d = (Graphics2D) g.create();
//			g2d.setComposite(AlphaComposite.SrcOver.derive(0.85f));
//			g2d.setColor(getBackground());
//			g2d.fillRect(0, 0, getWidth(), getHeight());
//
//		}
//
//	}

}

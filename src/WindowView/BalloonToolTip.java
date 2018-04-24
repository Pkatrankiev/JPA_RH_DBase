package WindowView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JToolTip;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

class BalloonToolTip extends JToolTip {
	  private HierarchyListener listener;
	  @Override public void updateUI() {
	    removeHierarchyListener(listener);
	    super.updateUI();
	    listener = e -> {
	      Component c = e.getComponent();
	      if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && c.isShowing()) {
	        Window w = SwingUtilities.getWindowAncestor(c);
	        if (w instanceof JWindow) {
	          ((JWindow) w).setBackground(new Color(0x0, true));
	        }
	      }
	    };
	    addHierarchyListener(listener);
	    setOpaque(false);
	    setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 5));
	  }
	  @Override public Dimension getPreferredSize() {
	    Dimension d = super.getPreferredSize();
	    d.height = 28;
	    return d;
	  }
	  @Override public void paintComponent(Graphics g) {
	    Shape s = makeBalloonShape();
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                        RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(getBackground());
	    g2.fill(s);
	    g2.setColor(getForeground());
	    g2.draw(s);
	    g2.dispose();
	    super.paintComponent(g);
	  }
	  private Shape makeBalloonShape() {
	    Insets i = getInsets();
	    int w = getWidth() - 1;
	    int h = getHeight() - 1;
	    int v = i.top / 2;
	    Polygon triangle = new Polygon();
	    triangle.addPoint(i.left + v + v, 0);
	    triangle.addPoint(i.left + v, v);
	    triangle.addPoint(i.left + v + v + v, v);
	    Area area = new Area(new RoundRectangle2D.Float(
	        0, v, w, h - i.bottom - v, i.top, i.top));
	    area.add(new Area(triangle));
	    return area;
	  }
	}
	
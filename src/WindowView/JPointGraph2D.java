package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.geom.Line2D;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class JPointGraph2D extends JPanel {
	 
	 
	private static final long serialVersionUID = 1L;

	protected JLabel titleLabel;

	  protected ChartPanel chartPanel;

	  protected int dataLength;

	  protected double[] xData;

	  protected double[] yData;

	  protected double xMin_custom;

	  protected double xMax_custom;

	  protected double yMin_custom;

	  protected double yMax_custom;

	  protected double xMin;

	  protected double xMax;

	  protected double yMin;

	  protected double yMax;
	  protected double[] pieData;

	  protected double choice;;
	  protected Stroke stroke;

	  protected GradientPaint gradient;

	  protected Image foregroundImage;

	  protected Color pointColor = Color.black;

	  protected Color lineColor = Color.blue;

	  public JPointGraph2D( double[] yD, String text, int choice1) {
	    super(new BorderLayout());
	    choice = choice1;
	    setBackground(Color.white);
	    titleLabel = new JLabel(text, JLabel.CENTER);
	    add(titleLabel, BorderLayout.NORTH);
	    int nData = yD.length;
	    if (xData == null) {
	      xData = new double[nData];
	      for (int k = 0; k < nData; k++)
	        xData[k] = k;
	    }
	
	    dataLength = nData;
	
	    yData = yD;

	    xMin_custom = xMax_custom = xData[0]; // To include 0 into the interval
	    yMin_custom = yMax_custom = yData[0];
	    for (int k = 1; k < dataLength-1; k++) {
	      xMin_custom = Math.min(xMin_custom, xData[k]);
	      xMax_custom = Math.max(xMax_custom, xData[k]);
	      yMin_custom = Math.min(yMin_custom, yData[k]);
	      yMax_custom = Math.max(yMax_custom, yData[k]);
	     }
	    for (int k = 1; k < dataLength; k++) {
	    	 xMin = Math.min(xMin, xData[k]);
		      xMax = Math.max(xMax, xData[k]);
		      yMin = Math.min(yMin, yData[k]);
		      yMax = Math.max(yMax, yData[k]);
		     }
	    if (xMin == xMax)
	      xMax++;
	    if (yMin == yMax)
	      yMax++;



	    chartPanel = new ChartPanel();
	    add(chartPanel, BorderLayout.CENTER);
	  }


	  public void setStroke(Stroke s) {
	    stroke = s;
	    chartPanel.repaint();
	  }


	  public void setLineColor(Color c) {
	    pointColor = c;
	    chartPanel.repaint();
	  }

	  public Color getLineColor() {
	    return pointColor;
	  }


	  class ChartPanel extends JComponent {
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		double xMargin = 25;

		  double yMargin = 25;
	    
		  double m_x;

		  double m_y;

		  double m_w;

		  double m_h;

		

	    ChartPanel() {
	      enableEvents(ComponentEvent.COMPONENT_RESIZED);
	    }

	    protected void processComponentEvent(ComponentEvent e) {
	      calcDimensions();
	    }

	    public void calcDimensions() {
	      Dimension d = getSize();
	      m_x = xMargin;
	      m_y = yMargin;
	      m_w = d.width - 2 * xMargin;
	      m_h = d.height - 2 * yMargin;

	    }

	    public double xChartToScreen(double x) {
	      return m_x + (x - xMin) * m_w / (xMax - xMin);
	    }

	    public double yChartToScreen(double y) {
	      return m_y + (yMax - y) * m_h / (yMax - yMin);
	    }

	    public void paintComponent(Graphics g) {
	   
	      Graphics2D g2 = (Graphics2D) g;
	      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	          RenderingHints.VALUE_ANTIALIAS_ON);
	      g2.setRenderingHint(RenderingHints.KEY_RENDERING,
	          RenderingHints.VALUE_RENDER_QUALITY);

	      if (stroke != null)
	        g2.setStroke(stroke);
	      
	        g2.setColor(lineColor);
	        g2.draw(new Line2D.Double(xChartToScreen(xMin_custom), yChartToScreen(yMin_custom),xChartToScreen(xMax_custom), yChartToScreen(yMin_custom)));
	        g2.draw(new Line2D.Double(xChartToScreen(xMin_custom), yChartToScreen(yMax_custom),xChartToScreen(xMax_custom), yChartToScreen(yMax_custom)));
	        g2.setColor(pointColor);
	        for (int k = 0; k < dataLength; k++) {
	        	
	        		if(k==choice ) {
	        			g2.setColor(Color.red);
	        		}else {
	        			g2.setColor(pointColor);	
	        		}
	        		if(k==dataLength-1) {
	        			g2.setColor(Color.red);		
	        		}
	        	 g2.draw(new Line2D.Double(xChartToScreen(xData[k]), yChartToScreen(yData[k]),xChartToScreen(xData[k]), yChartToScreen(yData[k])));
	        }
	      
	    }

	  }
	}

package OldClases;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Charts2D extends JFrame {

 
	private static final long serialVersionUID = 1L;

public Charts2D() {
    super("2D Charts");
    setSize(720, 280);
    getContentPane().setLayout(new GridLayout(1, 3, 10, 0));
    getContentPane().setBackground(Color.white);

    int[] xData = new int[8];
    int[] yData = new int[8];
    for (int i = 0; i < xData.length; i++) {
      xData[i] = i;
      yData[i] = (int) (Math.random() * 100);
      if (i > 0)
        yData[i] = (yData[i - 1] + yData[i]) / 2;
    }

    JChart2D chart = new JChart2D( xData.length, xData, yData, "Line Chart");
    chart.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_MITER));
    chart.setLineColor(new Color(0, 28, 28));
    getContentPane().add(chart);

    WindowListener wndCloser = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    };
    addWindowListener(wndCloser);

    setVisible(true);
  }

  public static void main(String argv[]) {
    new Charts2D();
  }

}

class JChart2D extends JPanel {
 
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected JLabel titleLabel;

  protected ChartPanel chartPanel;

  protected int dataLength;

  protected int[] xData;

  protected int[] yData;

  protected int xMin;

  protected int xMax;

  protected int yMin;

  protected int yMax;

  protected double[] pieData;


  protected Stroke stroke;

  protected GradientPaint gradient;

  protected Image foregroundImage;

  protected Color pointColor = Color.black;

  protected Color lineColor = Color.blue;

  public JChart2D( int nData, int[] xD, int[] yD, String text) {
    super(new BorderLayout());
    setBackground(Color.white);
    titleLabel = new JLabel(text, JLabel.CENTER);
    add(titleLabel, BorderLayout.NORTH);

 
    if (xData == null) {
      xData = new int[nData];
      for (int k = 0; k < nData; k++)
        xData[k] = k;
    }
    if (yD == null)
      throw new IllegalArgumentException("yData can't be null");
    if (nData > yD.length)
      throw new IllegalArgumentException("Insufficient yData length");
    if (nData > xD.length)
      throw new IllegalArgumentException("Insufficient xData length");
    dataLength = nData;
    xData = xD;
    yData = yD;

    xMin = xMax = 0; // To include 0 into the interval
    yMin = yMax = yData[0];
    for (int k = 0; k < dataLength; k++) {
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

	int xMargin = 25;

    int yMargin = 25;
    
    int m_x;

    int m_y;

    int m_w;

    int m_h;

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

    public int xChartToScreen(int x) {
      return m_x + (x - xMin) * m_w / (xMax - xMin);
    }

    public int yChartToScreen(int y) {
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
        g2.drawLine(xChartToScreen(xMin), yChartToScreen(yMin),xChartToScreen(xMax), yChartToScreen(yMin));
        g2.drawLine(xChartToScreen(xMin), yChartToScreen(yMax),xChartToScreen(xMax), yChartToScreen(yMax));
        g2.setColor(pointColor);
        for (int k = 0; k < dataLength; k++)
        	g2.drawLine(xChartToScreen(xData[k]), yChartToScreen(yData[k]),xChartToScreen(xData[k]), yChartToScreen(yData[k]));

      
    }

  }
}

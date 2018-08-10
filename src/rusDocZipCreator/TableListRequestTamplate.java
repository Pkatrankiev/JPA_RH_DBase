package rusDocZipCreator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableCellRenderer;

import javax.swing.table.TableColumn;

import WindowViewAplication.RequestViewAplication;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;

import javax.swing.DefaultCellEditor;

import java.text.MessageFormat;

public class TableListRequestTamplate extends JDialog {
	private boolean DEBUG = true;
	private static JFrame frame;
	private JPanel panel_Main;
	private static int index = 0;

	public  TableListRequestTamplate(Frame parent, String[] columnNames, Object[][] data) {
		super(parent, "Информация за пробите", true);
		
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		int[] columnSizes = initColumnSizes(columnNames);
		int dimPanelLabel = dimensionXpanelLabel(columnNames, columnSizes);
		setBounds(100, 100, 900, 170);
		panel_Main = new JPanel();
		panel_Main.setSize(new Dimension(900, 170));
		panel_Main.setLayout(new BoxLayout(panel_Main, BoxLayout.Y_AXIS));
		/** Create the scroll pane and add the table to it. **/
		JScrollPane scrollPane = new JScrollPane(panel_Main);
		JPanel[] panel_Label = new JPanel[data.length + 1];
		JLabel[][] lbl_collum = new JLabel[data.length + 1][columnNames.length];
		Color colBackgr = panel_Main.getBackground();
		for (int row_index = 0; row_index < data.length + 1; row_index++) {
			panel_Label[row_index] = new JPanel();
			panel_Main.add(panel_Label[row_index]);
			FlowLayout flowLayout = (FlowLayout) panel_Label[row_index].getLayout();


			panel_Label[row_index].setMaximumSize(new Dimension(dimPanelLabel, 20));
			panel_Label[row_index].setAlignmentY(Component.TOP_ALIGNMENT);
			panel_Label[row_index].setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			flowLayout.setHgap(0);
			flowLayout.setVgap(0);
			int k = row_index;
			if (k > 0) {
				panel_Label[k].addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						panel_Label[k].setBackground(Color.LIGHT_GRAY);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						panel_Label[k].setBackground(colBackgr);
					}

					public void mousePressed(MouseEvent e) {

						try {
							index = k - 1;
							removeAll();
							dispose();

						} catch (NumberFormatException e1) {

						}
					}
				});
			}

			for (int collum_index = 0; collum_index < columnNames.length; collum_index++) {

				if (row_index == 0) {
					lbl_collum[row_index][collum_index] = new JLabel(columnNames[collum_index].toString());
				} else {
					lbl_collum[row_index][collum_index] = new JLabel(data[row_index - 1][collum_index].toString());
				}
				if (collum_index == 0) {
					lbl_collum[row_index][collum_index]
							.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(0, 0, 0)));
				} else {
					lbl_collum[row_index][collum_index]
							.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
				}
				lbl_collum[row_index][collum_index]
						.setPreferredSize(new Dimension(columnSizes[collum_index] * 10 + 10, 20));
				lbl_collum[row_index][collum_index].setHorizontalAlignment(JLabel.CENTER);
				lbl_collum[row_index][collum_index].setAlignmentY(Component.TOP_ALIGNMENT);

				// lbl_collum[row_index][collum_index].setBorder(null);
				panel_Label[row_index].add(lbl_collum[row_index][collum_index]);
			}
		}
				add(scrollPane);

			}

	private int dimensionXpanelLabel(String[] columnNames, int[] columnSizes) {
		int dimensionXpanelLabel = 0;
		for (int i = 0; i < columnNames.length; i++) {
			dimensionXpanelLabel = dimensionXpanelLabel + columnSizes[i] * 10 + 10;
		}
		return dimensionXpanelLabel;
	}

	
	private int[] initColumnSizes(String[] columnNames) {
		int[] columWidth = new int[columnNames.length];
		for (int i = 0; i < columnNames.length; i++) {
			columWidth[i] = columnNames[i].length();
			System.out.println(columWidth[i]);
		}
		return columWidth;
	}

	public int getChoiceTamplateRequest() {
			return index;
	}

}
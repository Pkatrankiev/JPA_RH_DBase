package LeftPanelInMainWindow;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class StartCreateListForRowInLeftPanelWithProgrssBar extends SwingWorker<List<List<LeftPanelStartWindowClass>>, Integer> {

    JProgressBar jpb;
    JPanel under_panel_Left;
    JLabel lblNewLabel;
    JLabel label;
    JButton btnProgressBar;
    public StartCreateListForRowInLeftPanelWithProgrssBar(JProgressBar jpb, JPanel under_panel_Left,  JLabel lblNewLabel, JButton btnProgressBar) {
       this.jpb = jpb;
       this.under_panel_Left = under_panel_Left;
       this.lblNewLabel = lblNewLabel;
       this.btnProgressBar = btnProgressBar;
    }

    @Override
    protected void process(List<Integer> chunks) {
        int i = chunks.get(chunks.size()-1);
        jpb.setValue(i); // The last value in this array is all we care about.
       
    }

    @Override
    protected List<List<LeftPanelStartWindowClass>> doInBackground() throws Exception {
    	 List<List<LeftPanelStartWindowClass>> ss = CreateListLeftPanelStartWindowClass.createListLeftPanelStartWindowClass(jpb);
        return ss;
    }

    protected void done() {
        try {
            List<List<LeftPanelStartWindowClass>> ss = get();
            VariableFromLeftPanel.setListLeftPanelStartWindow(ss);
            btnProgressBar.setEnabled(true);
            jpb.setValue(100);
            CreateLeftPanelInfo.creatLeftPanel(under_panel_Left,  lblNewLabel);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
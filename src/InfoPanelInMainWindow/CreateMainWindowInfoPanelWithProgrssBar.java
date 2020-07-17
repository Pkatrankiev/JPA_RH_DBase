package InfoPanelInMainWindow;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class CreateMainWindowInfoPanelWithProgrssBar extends SwingWorker<List<List<LeftPanelStartWindowClass>>, Integer> {

    JProgressBar jpb;
    JPanel under_panel_Left;
//    JLabel lblNewLabel;
    JLabel label;
    JButton btnProgressBar;
    JPanel under_panel_Right;
    public CreateMainWindowInfoPanelWithProgrssBar(JProgressBar jProgressBar, JPanel under_panel_Left, 
    		JPanel under_panel_Right , JButton btnProgressBar  ) {
       this.jpb = jProgressBar;
       this.under_panel_Left = under_panel_Left;
//       this.lblNewLabel = lblNewLabel;
       this.btnProgressBar = btnProgressBar;
       this.under_panel_Right = under_panel_Right;
     
    }

    @Override
    protected void process(List<Integer> chunks) {
        int i = chunks.get(chunks.size()-1);
        jpb.setValue(i); // The last value in this array is all we care about.
       
    }

    @Override
    protected List<List<LeftPanelStartWindowClass>> doInBackground() throws Exception {
    	int startCheckYear = Integer.parseInt(CreatRightPanel.getTextStartYear());
    	 List<List<LeftPanelStartWindowClass>> ss = CreateListLeftPanelStartWindowClass.createListLeftPanelStartWindowClass(jpb, startCheckYear);
        return ss;
    }

    protected void done() {
        try {
            List<List<LeftPanelStartWindowClass>> ss = get();
            VariableFromStartWindowPanel.setListLeftPanelStartWindow(ss);
            btnProgressBar.setEnabled(true);
            jpb.setValue(100);
            CreatRightPanel.creatRightPanel(under_panel_Right);
            CreateLeftPanelInfo.creatLeftPanel(under_panel_Left);
           
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
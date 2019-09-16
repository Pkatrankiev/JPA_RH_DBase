package WindowView;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Aplication.ResultsDAO;
import DBase_Class.Results;
import DBase_Class.Sample;
import OldClases.test;

public class MesejePanel implements Runnable {
	static int result=3;
	
	public MesejePanel(List<Results> resultListForSave, List<Results> resultListForDelete) {
	
		
		String[][] saveStr = createmasive(resultListForSave);
		
		Object[] options1 = { "Да", "Отказ" };
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
			if(!resultListForDelete.isEmpty()){
	String[][] deleteStr  = createmasiveDelete(resultListForDelete);
		panel.add(createTextPanel("За изтриване"));
		panel.add(test.creadJPanel(deleteStr));
		}
		panel.add(createTextPanel("За запис"));
		panel.add(test.creadJPanel(saveStr));
		
		result = JOptionPane.showOptionDialog(null, panel, "Данни за базата", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options1, null);
		
	}
	
	public static int getResultMeseje (){
		return result;
	}
	private static String[][] createmasive(List<Results> resultListForSave) {
		String[][] masive = new String[resultListForSave.size()+1][3];
		masive[0] = new String[] { "Нуклид", "Активност", "МДА" };
		int i =1;
		for (Results res : resultListForSave) {
			masive[i][0] = res.getNuclide().getSymbol_nuclide();
			masive[i][1] = res.getValue_result().toString();
			masive[i][2] = res.getMda().toString();
			i++;
		}
		return masive;
	}
	
	private static String[][] createmasiveDelete(List<Results> resultListForDelete) {
		String[][] masive = new String[resultListForDelete.size()+1][3];
		masive[0] = new String[] { "Нуклид", "Активност", "МДА" };
		int i =1;
		for (Results res : resultListForDelete) {
			Results result = ResultsDAO.getValueResultsById(res.getId_results());
			masive[i][0] = result.getNuclide().getSymbol_nuclide();
			masive[i][1] = result.getValue_result().toString();
			masive[i][2] = result.getMda().toString();
			i++;
		}
		return masive;
	}
	
	private static JPanel createTextPanel(String str) {
		JPanel panel2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel2.add(new JLabel(str));
		return panel2;
	}
	
	public void run(Sample samp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	 
}

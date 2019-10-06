package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import WindowView.RequestViewFunction;

public class IzpitvanProduktAddDobivSection{

	public static void choiceIzpitProdListener (Choice choiceIzpitProd) {
		RequestViewFunction.setDataIn_Choice_Izpitvan_Produkt(choiceIzpitProd, null);
		choiceIzpitProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceIzpitProd.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				choiceIzpitProd.setBackground(Color.WHITE);
			}

		});
	}
	
}

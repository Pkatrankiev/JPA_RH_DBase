package WindowView;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class BasicWindow extends JFrame{


	
	public BasicWindow() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        Login loginDlg = new Login();
                        loginDlg.setVisible(true);
                        // if logon successfully
                        if(loginDlg.isSucceeded()){
                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                            System.out.println("Hi " + loginDlg.getUsername() + "!");
                        }else{
                        	 System.out.println("Hi NOT " + loginDlg.getUsername() + "!");
                        }
                    }
                });
		
		menuBar.add(btnLogin);
		
	}
	
	public boolean Login (){
		
		
		
		boolean corectUser = false;
		
		return corectUser;
	} 

}

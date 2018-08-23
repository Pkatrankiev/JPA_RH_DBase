package ChoiceFile;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;




public class Choiser extends JFrame {
	String description;
	  String extensions[];
	  public Choiser() {
		   try {
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(this);
		        this.pack();
		    } catch (Exception ex) {
		        Logger.getLogger(JFileChooser.class.getName()).log(Level.SEVERE, null, ex);
		    }
	    JFileChooser fileChooser = new JFileChooser();

	    fileChooser.setDialogTitle("Choose a file");

//	    FileFilter filter = new FileNameExtensionFilter("c files", "zip");
	    FileFilter filter = new ExtensionFileFilter(null, new String[] { "JPG", "zip" });

	    fileChooser.addChoosableFileFilter(filter);
	    this.getContentPane().add(fileChooser);
	    fileChooser.setVisible(true);
	   
	    int ret = fileChooser.showDialog(null, "Open file");

	    if (ret == JFileChooser.APPROVE_OPTION) {
	      File file = fileChooser.getSelectedFile();
	      System.out.println(file);
	    }
	    	    	
	  }
	
	  class ExtensionFileFilter extends FileFilter {
		  String description;

		  String extensions[];

		  public ExtensionFileFilter(String description, String extension) {
		    this(description, new String[] { extension });
		  }

		  public ExtensionFileFilter(String description, String extensions[]) {
		    if (description == null) {
		      this.description = extensions[0] + "{ " + extensions.length + "} ";
		    } else {
		      this.description = description;
		    }
		    this.extensions = (String[]) extensions.clone();
		    toLower(this.extensions);
		  }

		  private void toLower(String array[]) {
		    for (int i = 0, n = array.length; i < n; i++) {
		      array[i] = array[i].toLowerCase();
		    }
		  }

		  public String getDescription() {
		    return description;
		  }

		  public boolean accept(File file) {
		    if (file.isDirectory()) {
		      return true;
		    } else {
		      String path = file.getAbsolutePath().toLowerCase();
		      for (int i = 0, n = extensions.length; i < n; i++) {
		        String extension = extensions[i];
		        if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
		          return true;
		        }
		      }
		    }
		    return false;
		  }
		}


	  public static void main(String[] args) {
	    JFrame frame = new Choiser();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    frame.pack();
	    frame.setVisible(true);
	  }
	}
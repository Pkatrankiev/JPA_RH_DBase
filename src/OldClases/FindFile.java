package OldClases;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;
public class FindFile 
{
    public String findFile(String name, File file)
    {
    	
    	String fileName = "...";
        File[] list = file.listFiles();
        if(list!=null){
        for (File fil : list)
        {
            if (fil.isDirectory())
            {
                findFile(name,fil);
            }
            else if (fil.getName().startsWith(name))
            {
            	return fil.getName();
            }
        }
        }else{
        	 JOptionPane.showMessageDialog(null, "Недостигам до директория:" +"");
        		
        }
        return fileName;
    }
    
    
    public static void main(String[] args) 
    {
        FindFile ff = new FindFile();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the file to be searched.. " );
        String name = scan.next();
        System.out.println("Enter the directory where to search ");
        String directory = scan.next();
        ff.findFile(name,new File(directory));
        
    }
}


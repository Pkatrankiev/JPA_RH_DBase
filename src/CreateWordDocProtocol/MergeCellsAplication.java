package CreateWordDocProtocol;

import java.util.ArrayList;
import java.util.List;

import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcPr;
import org.docx4j.wml.Tr;
import org.docx4j.wml.TcPrInner.HMerge;
import org.docx4j.wml.TcPrInner.VMerge;

public class MergeCellsAplication {
	
	

	public static List<Tc> getTrAllCell(Tr tr) {  
	    List<Object> objList = AplicationDocTemplate.getAllElementFromObject(tr, Tc.class);  
	    List<Tc> tcList = new ArrayList<Tc>();  
	    if (objList == null) {  
	        return tcList;  
	    }  
	    for (Object tcObj : objList) {  
	        if (tcObj instanceof Tc) {  
	            Tc objTc = (Tc) tcObj;  
	            tcList.add(objTc);  
	        }  
	    }  
	    return tcList;  
	}
	
	public void mergeCellsHorizontal(Tbl tbl, int row, int fromCell, int toCell) {  
        if (row < 0 || fromCell < 0 || toCell < 0) {  
            return;  
        }  
        List<Tr> trList = getTblAllTr(tbl);  
        if (row > trList.size()) {  
            return;  
        }  
        Tr tr = trList.get(row);  
        List<Tc> tcList = getTrAllCell(tr);  
        for (int cellIndex = fromCell, len = Math  
                .min(tcList.size() - 1, toCell); cellIndex <= len; cellIndex++) {  
            Tc tc = tcList.get(cellIndex);  
            TcPr tcPr = getTcPr(tc);  
            HMerge hMerge = tcPr.getHMerge();  
            if (hMerge == null) {  
                hMerge = new HMerge();  
                tcPr.setHMerge(hMerge);  
            }  
            if (cellIndex == fromCell) {  
                hMerge.setVal("restart");  
            } else {  
                hMerge.setVal("continue");  
            }  
        }  
    }  
	
	 public static void mergeCellsVertically(Tbl tbl, int col, int fromRow, int toRow) {  
	        if (col < 0 || fromRow < 0 || toRow < 0) {  
	            return;  
	        }  
	        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {  
	            Tc tc = getTc(tbl, rowIndex, col);  
	            if (tc == null) {  
	                break;  
	            }  
	            TcPr tcPr = getTcPr(tc);  
	            VMerge vMerge = tcPr.getVMerge();  
	            if (vMerge == null) {  
	                vMerge = new VMerge();  
	                tcPr.setVMerge(vMerge);  
	            }  
	            if (rowIndex == fromRow) {  
	                vMerge.setVal("restart");  
	            } else {  
	                vMerge.setVal("continue");  
	            }  
	        }  
	    }  
	
	 public static TcPr getTcPr(Tc tc) {  
	        TcPr tcPr = tc.getTcPr();  
	        if (tcPr == null) {  
	            tcPr = new TcPr();  
	            tc.setTcPr(tcPr);  
	        }  
	        return tcPr;  
	    }  
	 
	 public static Tc getTc(Tbl tbl, int row, int cell) {  
	        if (row < 0 || cell < 0) {  
	            return null;  
	        }  
	        List<Tr> trList = getTblAllTr(tbl);  
	        if (row >= trList.size()) {  
	            return null;  
	        }  
	        List<Tc> tcList = getTrAllCell(trList.get(row));  
	        if (cell >= tcList.size()) {  
	            return null;  
	        }  
	        return tcList.get(cell);  
	    }  
	 
	  public static List<Tr> getTblAllTr(Tbl tbl) {  
	        List<Object> objList = AplicationDocTemplate.getAllElementFromObject(tbl, Tr.class);  
	        List<Tr> trList = new ArrayList<Tr>();  
	        if (objList == null) {  
	            return trList;  
	        }  
	        for (Object obj : objList) {  
	            if (obj instanceof Tr) {  
	                Tr tr = (Tr) obj;  
	                trList.add(tr);  
	            }  
	        }  
	        return trList;  
	  
	    }  
	 
	
}

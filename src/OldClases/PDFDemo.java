package OldClases;

import java.io.*;
import java.text.*;
import java.util.*;


/** A simple text test of SPDF package
 */
public class PDFDemo {
  public static void main(String[] argv) throws IOException {
    PrintWriter pout;
    if (argv.length == 0) {
      pout = new PrintWriter(System.out);
    } else {
      if (new File(argv[0]).exists()) {
        throw new IOException(
        "Output file " + argv[0] + " already exists");
      }
      pout = new PrintWriter(new FileWriter(argv[0]));
    }
    PDF p = new PDF(pout);
    Page p1 = new Page(p);
    p1.add(new MoveTo(p, 100, 600));
    p1.add(new Text(p, "Hello world, live on the web."));
    p1.add(new Text(p, "Hello world, line 2 on the web."));
    p.add(p1);
    p.setAuthor("Ian F. Darwin");
    p.writePDF();
  }
}
/** The main class for the Darwin Open Systems
 * {Simple,Stupid,Simplistic} PDF API.
 * PDF is Adobe's Portable Document Format, and is probably a trademark
 * of Adobe Systems Inc, Mountain View, California.
 * The Adobe PDF Specification which they publish grants everyone
 * permission to write code to generate and/or process PDF files.
 * A PDF Object represents one PDF file.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: PDF.java,v 1.6 2004/02/09 03:34:02 ian Exp $
 */
class PDF {
  /** The output writer */
  protected PrintWriter out;

  /** The list of pages */
  protected ArrayList<Page> pages;

  /** The list of object xrefs */
  protected ArrayList<Long> xrefs;

  /** The root object */
  PDFObject rootObj = new RootObject(this);

  /** The Info object */
  InfoObject infoObj = new InfoObject(this);

  /** The outlines (outline font) object */
  OutlinesObject outlinesObj = new OutlinesObject(this);

  /** The Pages object */
  PagesObject pagesObj = new PagesObject(this);

  /** The Font Dictionary */
  FontDict fontDict = new FontDict(this);

  /** The object number of the current object */
  protected int currObj = 1;

  /** A flag to avoid writing twice */
  protected boolean startedWriting = false;

  /** A magic number that identifies the output as a PDF file */
  protected final static String PDF_MAGIC = "%PDF-1.0";

  /** Constructor */
  public PDF(PrintWriter o) {
    out = o;

    pages = new ArrayList<Page>();
    xrefs = new ArrayList<Long>();

  }

  public void add(Page p) {
    pages.add(p);
  }

  public void insertPage(int where, Page p) {
    pages.add(where, p);
  }

  // OUTPUT METHODS -- we provide our own print/println, so we
  // can keep track of file offsets (it was either that, or kludgily
  // use a RandomAccessFile and the getFilePointer() method).

  long offset = 0;

  /** Print a String */
  protected void print(String s){
    out.print(s);
    offset += s.length();
  }

  /** Println a String */
  protected void println(String s) {
    print(s);
    print("\n");
  }

  /** Print an Object */
  protected void print(Object o) {
    print(o.toString());
  }

  /** Println an Object */
  protected void println(Object o) {
    println(o.toString());
  }

  /** Print an int */
  protected void print(int i) {
    String s = Integer.toString(i);
    print(s);
  }

  /** Println an int */
  protected void println(int i) {
    String s = Integer.toString(i);
    print(s);
  }

  /** Println with no args */
  protected void println() {
    print("\n");
  }

  // END OF OUTPUT METHODS 

  /** Add an entry into the offset table */
  protected void addXref() {
    xrefs.add(new Long(offset));
  }

  /** Write the entire output */
  public void writePDF() {
    if (startedWriting) {
      throw new IllegalStateException(
        "writePDF() can only be called once.");
    }
    startedWriting = true;

    writePDFHeader();
    writePDFbody();
    writeXrefs();
    writePDFTrailer();
    out.flush();
    out.close();
  }

  protected void writePDFHeader() {
    println(PDF_MAGIC);

    rootObj.print();  // 1

    infoObj.print();  // 2

    outlinesObj.print(); // 3

    pagesObj.print();  // 4
  }

  protected void writePDFbody() {

    for (int i=0; i<pages.size(); i++) {
       ((Page)pages.get(i)).print();    // 5, 6
    }

    addXref();
    print(currObj++); println(" 0 obj");
    println("[/PDF /Text]");
    println("endobj");

    fontDict.print();    // 8
  }

  DecimalFormat nf10 = new DecimalFormat("0000000000");
  DecimalFormat nf5 = new DecimalFormat("00000");

  /** Write one Xref, in the format 0000000000 65535 f */
  protected void printXref(long n, int where, char inUse) {
    println(nf10.format(n) + " " +  nf5.format(where) + " " + inUse);
  }

  long xrefStart;

  /** Write all the xrefs, using the format above */
  protected void writeXrefs() {
    xrefStart = offset;
    println("xref");
    print(0);
    print(" ");
    print(xrefs.size() + 1);
    println();
    // "fake" entry at 0, always 0, -1, f(free).
    printXref(0, 65535, 'f');
    // Remaining xref entries are for real objects.
    for (int i=0; i<xrefs.size(); i++) {
      Long lo = (Long)xrefs.get(i);
      long l = lo.longValue();
      printXref(l, 0, 'n');
    }

  }

  protected void writePDFTrailer() {
    println("trailer");
    println("<<");
    println("/Size " + (xrefs.size() + 1));
    println("/Root 1 0 R");
    println("/Info 2 0 R");
    println(">>");
    println("% startxref");
    println("% " + xrefStart);
    println("%%EOF");
  }

  class RootObject extends PDFDict {
    protected RootObject(PDF m) {
      super(m);
      dict.put("Type", "/Catalog");
      dict.put("Outlines", "3 0 R");
      dict.put("Pages", "4 0 R");
    }
  }

  class InfoObject extends PDFDict {
    protected InfoObject(PDF m) {
      super(m);
      dict.put("Title", "(Sample PDF by SPDF)");
      dict.put("Creator", "(Darwin Open Systems SPDF Software)");
      dict.put("Created", "(D:20000516010203)");
    }
  }
  
  public void setAuthor(String au) {
    infoObj.dict.put("Author", "(" + au + ")");
  }

  class OutlinesObject extends PDFDict {
    protected OutlinesObject(PDF m) {
      super(m);
      dict.put("Type", "/Outlines");
      dict.put("Count", "0");
    }
  }
  class PagesObject extends PDFDict {
    protected PagesObject(PDF m) {
      super(m);
      dict.put("Type", "/Pages");
      dict.put("Count", "1");
      dict.put("Kids", "[5 0 R]");
    }
  }

  class FontDict extends PDFDict {
    protected FontDict(PDF m) {
      super(m);
      dict.put("Type", "/Font");
      dict.put("Subtype", "/Type1");
      dict.put("Name", "/F1");
      dict.put("BaseFont", "/Helvetica");
      dict.put("Encoding", "/MacRomanEncoding");
    }
  }
}

/** A PDFDict ias a PDFObject that is all, or mostly, a Dictionary.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
abstract class PDFDict extends PDFObject {
  /** The Dictionary is a HashTable. Put the keys without a 
   * leading slash, since they always have it. Values can
   * be /names, (strings), or whatever.
   */
  protected Hashtable<String, String> dict;

  PDFDict(PDF m) {
    super(m);
    dict = new Hashtable<String, String>();
  }

  /** Write the object to the Output Writer. The default implementation
   * of this method in PDFDict just calls startObj, printDict, and endObj.
   */
  protected void print() {
    startObj();
    printDict();
    endObj();
  }

  protected void startObj() {
    // Record the starting position of this Obj in the xref table
    master.addXref();

    // Print out e.g., "42 0 obj"
    master.print(master.currObj++);
     master.print(" 0 obj");
    master.println();
  }

  protected void endObj() {
    master.println("endobj");
  }

  protected void printDict() {
    master.println("<<");
    Enumeration<String> e = dict.keys();
    while (e.hasMoreElements()) {
      master.print("\t/");
      String key = (String)e.nextElement();
      master.print(key);
      master.print(" ");
      master.print(dict.get(key));
      master.println();
    }
    master.println(">>");
  }
}
/** Represent one Text object in a PDF file. */
class Text extends PDFObject {
  protected int x, y;
  protected String text;

  public Text(PDF m, String s) {
    super(m);
    text = s;
  }

  public void print() {
    throw new IllegalStateException("print() called on a Text obj");
  }

  public void print(StringBuffer sb) {
    sb.append("0 -18 Td (");
    sb.append(text);  // TODO must substitute escaped characters
    sb.append(") Tj\n");
  }
}
/** A PDFObject represents one node in the tree of a PDF file.
 * @author Ian Darwin, http://www.darwinsys.com/
 */
abstract class PDFObject extends java.lang.Object {
  /** The containing PDF file */
  protected PDF master;

  PDFObject(PDF m) {
    master = m;
  }

  /** Write the object to the Output Writer */
  protected abstract void print();

  protected void startObj() {
    // Record the starting position of this Obj in the xref table
    master.addXref();

    // Print out e.g., "42 0 obj"
    master.print(master.currObj++);
     master.print(" 0 obj");
    master.println();
  }

  protected void endObj() {
    master.println("endobj");
  }
}
/** Represent one Move object ("moveto") in a PDF file. */
class MoveTo extends PDFObject {
  protected int x, y;

  public MoveTo(PDF m, int x, int y) {
    super(m);
    this.x = x;
    this.y = y;
  }

  public void print() {
    throw new IllegalStateException("print() called on a Text obj");
  }

  public void print(StringBuffer sb) {
    sb.append(x);
    sb.append(' ');
    sb.append(y);
    sb.append(" Td\n");
  }
}
/** Represent one Page of a PDF file. */
class Page extends PDFDict {
  protected ArrayList<PDFObject> objects = new ArrayList<PDFObject>();

  public Page(PDF m) {
    super(m);
    dict.put("Type", "/Page");
    dict.put("Parent", "4 0 R");
    dict.put("Resources", "<< /Font << /F1 8 0 R >> /ProcSet 7 0 R >>");
    dict.put("MediaBox", "[0 0 612 792]");
    dict.put("Contents", "6 0 R");
    
  }

  public void add(PDFObject po) {
    objects.add(po);
  }

  /** Print all the objects on the page.
   * For now, just print all the Text objects, as one Stream.
   */
  protected void print() {
    // Print the Page object
    super.print();

    // Now do the Text objects as one PDF obj
    master.addXref();
    startObj();

    StringBuffer sb = new StringBuffer();
    sb.append("BT\n");
    sb.append("/F1 12 Tf\n");

    for (int i=0; i<objects.size(); i++) {
      PDFObject po = (PDFObject)objects.get(i);
      if (po instanceof Text)
        ((Text)po).print(sb);
      else if (po instanceof MoveTo)
        ((MoveTo)po).print(sb);
      // else if (po instanceof Font)
      //  ...
      else
        System.err.println("PDFPage: ignoring " + po);
    }
    sb.append("ET\n");

    master.println("<< /Length " + sb.length() + " >>");
    master.println("stream");
    master.print(sb);
    master.println("endstream");
    endObj();
  }
}

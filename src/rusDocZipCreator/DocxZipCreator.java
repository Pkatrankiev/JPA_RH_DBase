package rusDocZipCreator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import GlobalVariable.ResourceLoader;

/**
 * Creates new docx from existing one.
 * @author gustinmi [at] gmail [dot] com
 */
public class DocxZipCreator {

    public static final Logger log = Logger.getLogger(DocxZipCreator.class.getCanonicalName());

    private static final int BUFFER_SIZE = 4096;


    /** OnTheFly zip creator. Traverses through existing docx zip and creates new one simultaneousl.
     * On the end, custom document.xml is inserted inside
     * @param zipFilePath location of existing docx template (without word/document.xml)
     * @param documentXmlContent content of the word/document.xml
     * @throws IOException
     */
    public void createDocxFromExistingDocx(ZipOutputStream zos, String zipFilePath, String documentXmlContent) throws IOException {

        final FileInputStream fis = new FileInputStream(zipFilePath);
        final ZipInputStream zipIn = new ZipInputStream(fis);

        try{
            log.info("Starting to create new docx zip");
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) { // iterates over entries in the zip file
                copyEntryfromZipToZip(zipIn, zos, entry.getName());
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }

            // add document.xml to existing zip
            addZipEntry(documentXmlContent, zos, "word/document.xml");

        }finally{
            zipIn.close();
            zos.close();
            log.info("End of docx creation");
        }

    }

    /** Copies sin gle entry from zip to zip  */
    public void copyEntryfromZipToZip(ZipInputStream is, ZipOutputStream zos, String entryName)
    {
        final byte [] data = new byte[BUFFER_SIZE]; 
        int len; 
        int lenTotal = 0;
        try {

            final ZipEntry entry = new ZipEntry(entryName); 
            zos.putNextEntry(entry); 

            final CRC32 crc32 = new CRC32(); 
            while ((len = is.read(data)) > -1){ 
                zos.write(data, 0, len); 
                crc32.update(data, 0, len);
                lenTotal += len;
            } 

            entry.setSize(lenTotal); 
            entry.setTime(System.currentTimeMillis()); 
            entry.setCrc(crc32.getValue());

        }
        catch (IOException ioe){
        	ResourceLoader.appendToFile(ioe);
            ioe.printStackTrace();
        }
        finally{
            try { zos.closeEntry();} catch (IOException e) {ResourceLoader.appendToFile(e);}
        }
    }

    /**  Create new zip entry with content
     * @param content content of a new zip entry
     * @param zos 
     * @param entryName name (npr: word/document.xml)
     */
    public void addZipEntry(String content, ZipOutputStream zos, String entryName)
    {
        final byte [] data = new byte[BUFFER_SIZE]; 
        int len; 
        int lenTotal = 0;
        try {

            final InputStream is = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));

            final ZipEntry entry = new ZipEntry(entryName); 
            zos.putNextEntry(entry); 

            final CRC32 crc32 = new CRC32(); 
            while ((len = is.read(data)) > -1){ 
                zos.write(data, 0, len); 
                crc32.update(data, 0, len);
                lenTotal += len;
            } 

            entry.setSize(lenTotal); 
            entry.setTime(System.currentTimeMillis()); 
            entry.setCrc(crc32.getValue());

        }
        catch (IOException ioe){
        	ResourceLoader.appendToFile(ioe);
            ioe.printStackTrace();
        }
        finally{
            try { zos.closeEntry();} catch (IOException e) {ResourceLoader.appendToFile(e);}
        }
    }

}

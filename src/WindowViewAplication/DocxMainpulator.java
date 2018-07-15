package WindowViewAplication;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class DocxMainpulator {

	private static final String MAIN_DOCUMENT_PATH = "word/document.xml";
	private static final String TEMPLATE_DIRECTORY_ROOT = "TEMPLATES_DIRECTORY/";
	private static final String destinationDir = "TEMPLATES/";

	// Generates .docx document from given template and the substitution data
	public static Boolean generateAndSend_Request_Docx(String templateName, String destinationName,
			Map<String, String> substitutionData) {

		String templateLocation = TEMPLATE_DIRECTORY_ROOT + templateName;
		String userTempDir = UUID.randomUUID().toString();
		userTempDir = TEMPLATE_DIRECTORY_ROOT + userTempDir + "/";

		try {

			// Unzip .docx file
			unzip(new File(templateLocation), new File(userTempDir));

			// Change data
			changeData(new File(userTempDir + MAIN_DOCUMENT_PATH), substitutionData);

			// Rezip .docx file
			zip(new File(userTempDir), new File(userTempDir + templateName));

			//Move .docx file to destinationDir with destinationName
			moveFile(new File(destinationDir), destinationName, new File(userTempDir + templateName));
		
			// Clean temp data
			deleteTempData(new File(userTempDir));

			openWordDoc(destinationDir + destinationName + ".docx");

		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			return false;
		}

		return true;
	}

	// Unzipps specified ZIP file to specified directory
	private static void unzip(File zipfile, File directory) throws IOException {

		ZipFile zfile = new ZipFile(zipfile);
		Enumeration<? extends ZipEntry> entries = zfile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			File file = new File(directory, entry.getName());
			if (entry.isDirectory()) {
				file.mkdirs();
			} else {
				file.getParentFile().mkdirs();
				InputStream in = zfile.getInputStream(entry);
				try {
					copy(in, file);
				} finally {
					in.close();
				}
			}
		}
	}

	// Substitutes keys found in target file with corresponding data
	private static void changeData(File targetFile, Map<String, String> substitutionData) throws IOException {

		BufferedReader br = null;
		String docxTemplate = "";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null)
				docxTemplate = docxTemplate + temp;
			br.close();
			targetFile.delete();
		} catch (IOException e) {
			br.close();
			throw e;
		}

		Iterator substitutionDataIterator = substitutionData.entrySet().iterator();
		while (substitutionDataIterator.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) substitutionDataIterator.next();
			if (docxTemplate.contains(pair.getKey())) {
				if (pair.getValue() != null)
					docxTemplate = docxTemplate.replace(pair.getKey(), pair.getValue());
				else
					docxTemplate = docxTemplate.replace(pair.getKey(), "NEDOSTAJE");
			}
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(targetFile);
			fos.write(docxTemplate.getBytes("UTF-8"));
			fos.close();
		} catch (IOException e) {
			fos.close();
			throw e;
		}
	}

	// Zipps specified directory and all its subdirectories
	private static void zip(File directory, File zipfile) throws IOException {

		File dir = new File("TEMPLATES/");
		URI base = directory.toURI();
		Deque<File> queue = new LinkedList<File>();
		queue.push(directory);
		OutputStream out = new FileOutputStream(zipfile);
		Closeable res = out;

		try {
			ZipOutputStream zout = new ZipOutputStream(out);
			res = zout;
			while (!queue.isEmpty()) {
				directory = queue.pop();
				for (File kid : directory.listFiles()) {
					String name = base.relativize(kid.toURI()).getPath();
					if (kid.isDirectory()) {
						queue.push(kid);
						name = name.endsWith("/") ? name : name + "/";
						System.out.println("name: " + name);
						zout.putNextEntry(new ZipEntry(name));
					} else {
						if (kid.getName().contains(".docx"))
							continue;
						zout.putNextEntry(new ZipEntry(name));
						copy(kid, zout);
						zout.closeEntry();
					}
				}
			}
		} finally {
			res.close();
		}
	}

	// Move generate file from sourceFolder to destinationFolder
	private static void moveFile(File destinationFolder, String destinationName, File sourceFolder) {

		if (!destinationFolder.exists()) {
			destinationFolder.mkdirs();
		}

		// Check weather source exists and it is folder.
		if (sourceFolder.exists()) {

			File sourceFile = sourceFolder.getAbsoluteFile();
			File destFile = new File(destinationFolder + "\\" + destinationName + ".docx");
			File delFile = new File(destinationFolder + "\\" + destinationName + ".docx");
			File destOldFile = new File(destinationFolder + "\\" + destinationName + "-old.docx");

			if (destFile.isFile() && destFile.canRead()){
				destOldFile.delete();
				destFile.renameTo(destOldFile);
				
			}
				// Move files to destination folder
				sourceFile.renameTo(destFile);
//				destFile.enforceReadonlyProtection();

			// Add if you want to delete the source folder
			// sourceFolder.delete();
		}

		else {
			System.out.println(sourceFolder + "  Folder does not exists");
		}

	}

	// Deletes directory and all its subdirectories
	private static void deleteTempData(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0)
				file.delete();
			else {
				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					deleteTempData(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0)
					file.delete();
			}
		} else {
			// if file, then delete it
			file.delete();
		}
	}

	private static void copy(InputStream in, OutputStream out) throws IOException {

		byte[] buffer = new byte[1024];
		while (true) {
			int readCount = in.read(buffer);
			if (readCount < 0) {
				break;
			}
			out.write(buffer, 0, readCount);
		}
	}

	private static void copy(File file, OutputStream out) throws IOException {
		InputStream in = new FileInputStream(file);
		try {
			copy(in, out);
		} finally {
			in.close();
		}
	}

	private static void copy(InputStream in, File file) throws IOException {
		OutputStream out = new FileOutputStream(file);
		try {
			copy(in, out);
		} finally {
			out.close();
		}
	}

	private static void openWordDoc(String destinationDir) throws IOException {
		// Process p = Runtime.getRuntime().exec("rundll32
		// url.dll,FileProtocolHandler"+destinationDir);
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(new File(destinationDir));
			}
			// p.waitFor();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// System.out.println("Donenot.");
		// }
		System.out.println(destinationDir);
	}

}


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
 

 

public class Concatenate {

    /** The resulting PDF file. */
    public static final String RESULT
        = "F:/concatenated.pdf";
    public static final String RESULT2
    = "F:/concatenated2.pdf";

    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args)
        throws IOException, DocumentException, SQLException {
        // using previous examples to create PDFs
    	 
        String[] files = { "c:/uno.pdf", "c:/dos.pdf"};
        // step 1
        Document document = new Document();
       /* 
        int permissions = ~(	
    		 	PdfWriter.ALLOW_MODIFY_ANNOTATIONS |	PdfWriter.ALLOW_FILL_IN |
    		PdfWriter.ALLOW_ASSEMBLY | PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_COPY | PdfWriter.AllowModifyAnnotations | PdfWriter.AllowFillIn | PdfWriter.AllowAssembly | PdfWriter.AllowModifyContents | PdfWriter.AllowScreenReaders
    			);
    
    
     PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
 
      writer.setViewerPreferences(PdfWriter.HideMenubar | PdfWriter.CenterWindow | permissions);*/
   
   
        // step 2
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(RESULT));
        
     
        
        // step 3
        document.open();
        // step 4
        PdfReader reader;
        int n;
        // loop over the documents you want to concatenate
        for (int i = 0; i < files.length; i++) {
            reader = new PdfReader(files[i]);
            // loop over the pages in that document
            n = reader.getNumberOfPages();
            for (int page = 0; page < n; ) {
                copy.addPage(copy.getImportedPage(reader, ++page));
            }
            copy.freeReader(reader);
        }
        // step 5
       
        document.close();
        reader = new PdfReader(RESULT);
        OutputStream fout = new FileOutputStream(RESULT2); 
        PdfStamper stamp = new PdfStamper(reader, fout);

        stamp.setEncryption(null, null, 0, true);
        stamp.setViewerPreferences(PdfWriter.HideMenubar);
        stamp.setViewerPreferences(PdfWriter.HideWindowUI);
        stamp.setViewerPreferences(PdfWriter.HideToolbar);
        stamp.close(); 
    }
    
    
    public static byte[] convertTiffToPdf(byte[] archivoTiff) 

    throws DocumentException, MalformedURLException, IOException {



ByteArrayOutputStream outfile = new ByteArrayOutputStream();

Document document = new Document(PageSize.A4.rotate());

PdfWriter writer = PdfWriter.getInstance(document, outfile);

writer.setStrictImageSequence(true);

;      document.open();

Image tiff = Image.getInstance(archivoTiff);

tiff.scaleToFit(800, 600);

document.add(tiff);

document.close();

outfile.flush();

return outfile.toByteArray();

}

public byte[] convertirFileADataBinariaDinamico(final File archivo) {
byte[] dataBinaria = null;
FileInputStream streamArchivo = null;
try {
	streamArchivo = new FileInputStream(archivo);
	dataBinaria = new byte[(int) archivo.length()];
	streamArchivo.read(dataBinaria);
	streamArchivo.close();
} catch (IOException ex) {
	ex.printStackTrace();

} finally {
	if (streamArchivo != null) {
		try {
			streamArchivo.close();
		} catch (IOException ex) {

		}
	}
}
return dataBinaria;
}

public static final File convertirbyteAFileNombre (byte[] fuente,
    String nombre) {
File archivo = null;
try {
    String extensionFile = nombre.substring(nombre.lastIndexOf(".") + 1,
            nombre.length());
    String nombreFile = nombre.substring(0,nombre.lastIndexOf(".") );
    archivo = File.createTempFile(nombreFile, "." + extensionFile);
    archivo.deleteOnExit();
    FileOutputStream archivoSalida = new FileOutputStream(archivo);
    archivoSalida.write(fuente);
    archivoSalida.close();

} catch (IOException ex) {
   
}
return archivo;
}
}

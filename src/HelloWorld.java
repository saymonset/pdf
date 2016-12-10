
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * First iText example: Hello World.
 */
public class HelloWorld {

    /** Path to the resulting PDF file. */
    public static final String RESULT
        = "f:/saymon2.pdf";
    
    /**
     * Creates a PDF file: hello.pdf
     * @param    args    no arguments needed
     */
    public static void main(String[] args)
    	throws DocumentException, IOException {
    	new HelloWorld().createPdf(RESULT);
    }

    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename)
	throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World!"));
        // step 5
        document.close();
        
        
       
        int permissions = ~(	
        		 	PdfWriter.ALLOW_MODIFY_ANNOTATIONS |	PdfWriter.ALLOW_FILL_IN |
        		PdfWriter.ALLOW_ASSEMBLY | PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_COPY | PdfWriter.AllowModifyAnnotations | PdfWriter.AllowFillIn | PdfWriter.AllowAssembly | PdfWriter.AllowModifyContents | PdfWriter.AllowScreenReaders
        			);
        
        
        document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
    //    writer.setEncryption(PdfWriter.STRENGTH128BITS, "", "", permissions  );
        writer.setViewerPreferences(PdfWriter.HideMenubar | PdfWriter.CenterWindow | permissions);
        document.open();
        document.add(new Paragraph("Hello World"));
        document.close();
        
    }
}
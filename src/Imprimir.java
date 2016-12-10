import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

public class Imprimir {
	public static void main(String[] args) {
		// Cogemos el servicio de impresión por defecto (impresora por defecto)
		PrintService service = PrintServiceLookup.lookupDefaultPrintService();
		// Le decimos el tipo de datos que vamos a enviar a la impresora
		// Tipo: bytes Subtipo: autodetectado
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		// Creamos un trabajo de impresión
		DocPrintJob pj = service.createPrintJob();
		// Nuestro trabajo de impresión envía una cadena de texto
		String ss = new String("Aquí lo que vamos a imprimir.");
		byte[] bytes;
		// Transformamos el texto a bytes que es lo que soporta la impresora
		bytes = ss.getBytes();
		// Creamos un documento (Como si fuese una hoja de Word para imprimir)
		Doc doc = new SimpleDoc(bytes, flavor, null);



		//Le damos atributos a la impresion para imprimir.


		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();

		//asigna dos copias a la impresora
		aset.add(new Copies(3));
		// Obligado coger la excepción PrintException
		try {
			// Mandamos a impremir el documento
			pj.print(doc, aset);
		} catch (PrintException e) {
			e.printStackTrace();
		}
	}
}

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "itext.pdf";
        Document document = new Document();
		try
		{
			PdfWriter.getInstance(document, new FileOutputStream(path));

			document.open();
			document.add(new Paragraph("Hello Sakshuu!!!"));
			document.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
    }
}

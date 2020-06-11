/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.utils;
import GOT.service.donation.donationService;
import GOT.service.facture.factureService;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Ali
 */
public class PDF {
     public static final String RESULT= "C:\\Users\\Ali\\Desktop\\TunisianGotTalent-Java-master\\TunisianGotTalent-Java-master\\facture.pdf";
      /**
        * Main method.
        * @param    args    no arguments needed
        * @throws DocumentException 
        * @throws IOException
        */
       public static void main()
           throws IOException, DocumentException, SQLException{
           
           new PDF().createPdf(RESULT);
           Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler sponsoring.pdf");
       }

       /**
        * Creates a PDF with information about the movies
        * @param    filename the name of the PDF file that will be created.
        * @throws    DocumentException 
        * @throws    IOException
        */
       public void createPdf(String filename)
           throws IOException, DocumentException, SQLException {
           // step 1
           Document document = new Document();
           factureService fs = new factureService();
           // step 2
           PdfWriter.getInstance(document, new FileOutputStream(filename));
           // step 3
           document.open();
           document.add(new Paragraph("fullname :"+SessionUser.getUser().getUsername()));
           
           // step 4
           document.add(createFirstTable());
           // step 5
           document.close();
       }

       /**
        * Creates our first table
        * @return our first table
        */
       public static PdfPTable createFirstTable() throws SQLException, BadElementException, IOException {
           PdfPTable table = new PdfPTable(3);
           factureService fs = new factureService();
           donationService ds = new donationService();
           
       PdfPCell c1 = new PdfPCell(new Phrase("id_spon"));
       c1.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c1);
       

       PdfPCell c2 = new PdfPCell(new Phrase("id_facture"));
       c2.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c2);
      PdfPCell c3 = new PdfPCell(new Phrase("Nom_Don"));
       c3.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c3);
      PdfPCell c4 = new PdfPCell(new Phrase("Au nom du : "));
       c4.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c4);
       PdfPCell c5 = new PdfPCell(new Phrase("Valeur du don "));
       c5.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c5);
       PdfPCell c6 = new PdfPCell(new Phrase("De Type "));
       c6.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c6);
     PdfPCell  c7 = new PdfPCell(new Phrase("Le : "));
       c7.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c7);
       

       
       table.setHeaderRows(1);
      
        
 for(int i = 0 ; i<fs.affciher().size(); i++)
 {
       table.addCell(""+fs.affciher().get(i).getId());
       table.addCell(""+ds.affciher().get(i).getLib_donation());
table.addCell(""+SessionUser.getUser().getUsername());
    table.addCell(""+ds.affciher().get(i).getValeur_d());
      table.addCell(""+ds.affciher().get(i).getCategorie());
       table.addCell(""+fs.affciher().get(i).getDate_cr());
        
 }

           return table;
       }
}

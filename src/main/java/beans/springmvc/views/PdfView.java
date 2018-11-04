package beans.springmvc.views;

import com.lowagie.text.Paragraph;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PdfView extends AbstractPdfView {

    protected void buildPdfDocument(
            Map model,
            Document doc,
            PdfWriter writer,
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception {

        List words = (List) model.get("wordList");

        for (int i=0; i<words.size(); i++)
            doc.add( new Paragraph((String) words.get(i)));

        /*
          PdfPTable table = new PdfPTable(3);

        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Date");

        for (Course course : courses){
            table.addCell(String.valueOf(course.getId()));
            table.addCell(course.getName());
            table.addCell(DATE_FORMAT.format(course.getDate()));
        }

        document.add(table);
         */


    }
}

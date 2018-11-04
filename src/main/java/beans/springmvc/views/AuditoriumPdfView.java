package beans.springmvc.views;

import beans.models.Auditorium;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public class AuditoriumPdfView extends AbstractPdfView {

    protected void buildPdfDocument(
            Map model,
            Document doc,
            PdfWriter writer,
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception {

        @SuppressWarnings("unchecked")
        List<Auditorium> list = (List) model.get("auditoriumList");

        PdfPTable table = new PdfPTable(3);
        // header row:
        table.addCell("Name");
        table.addCell("No.seats");
        table.addCell("VIP seats");
        table.setHeaderRows(1);

        list.forEach(a -> {
            table.addCell(a.getName());
            table.addCell(String.valueOf(a.getSeatsNumber()));
            table.addCell(a.getVipSeats());
        });

        doc.add(table);

    }
}

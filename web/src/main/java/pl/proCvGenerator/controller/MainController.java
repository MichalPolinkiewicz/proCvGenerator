package pl.proCvGenerator.controller;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.proCvGenerator.facade.WebFacade;
import pl.proCvGenerator.model.CvContentDto;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private WebFacade facade;

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/pattern")
    public String pattern(@RequestParam(name = "pattern") String pattern, Model model) {
        model.addAttribute("pattern", pattern);
        return "content";
    }

    @PostMapping(path = "/generate")
    public void generate(String pattern, CvContentDto cvContentDto, HttpServletResponse response) {
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        response.setHeader("Cache-Control", "no-store");
        response.setContentType("application/pdf");
        PdfWriter pdfWriter = new PdfWriter(baosPDF);

        Document document = new Document(new PdfDocument(pdfWriter), PageSize.A4);
        facade.generate("simplePattern", cvContentDto, document);
        document.close();

        try {
            ServletOutputStream sos = response.getOutputStream();
            baosPDF.writeTo(sos);
            sos.flush();
            baosPDF.close();
        } catch (IOException e) {

        }
    }

    @ModelAttribute(name = "cvContentDto")
    public CvContentDto init() {
        return facade.init();
    }
}

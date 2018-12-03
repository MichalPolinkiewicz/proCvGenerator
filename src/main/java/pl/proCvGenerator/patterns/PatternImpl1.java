package pl.proCvGenerator.patterns;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.fonts.Fonts;
import pl.proCvGenerator.patterns.helpers.PatternHelper;

public class PatternImpl1 implements Pattern {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl1.class);
    private final Font normalFont = Fonts.TAHOMA_NORMAL;
    private final Font boldFont = Fonts.TAHOMA_BOLD;
    private static final int NORMAL_SIZE = 14;
    private static final int BOLD_SIZE = 16;

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(25, 25, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) {
        try {
            addHeader(document, cvContent.getPersonalInfo());
            createCvBody(document, cvContent);

        } catch (DocumentException e) {

        }
    }

    public void addHeader(Document document, PersonalInfo personalInfo) {
        Paragraph p = PatternHelper.createSimpleParagraph((personalInfo.getName() + " " + personalInfo.getSurname()).toUpperCase(), boldFont, 38);
        try {
            document.add(p);
            p = PatternHelper.createSimpleParagraph(personalInfo.getPosition().toUpperCase(), boldFont, 16);
            boldFont.setColor(new BaseColor(179, 71, 0));
            p.setSpacingAfter(50);
            document.add(p);

        } catch (DocumentException e) {

        }
    }

    public void createCvBody(Document document, CvContent cvContent) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{2, 1.5f});

        PdfPCell left = createLeft(cvContent);
        left.setPaddingRight(40);
        left.setBorder(Rectangle.NO_BORDER);
        table.addCell(left);

        PdfPCell right = createRight(cvContent);
        right.setBorder(Rectangle.NO_BORDER);
        table.addCell(right);

        document.add(table);
    }


    public PdfPCell createLeft(CvContent cvContent) throws DocumentException {
        PdfPCell cell = new PdfPCell();
        Paragraph p;
        p = PatternHelper.createSimpleParagraph("D O Ś W I A D C Z E N I E", boldFont, BOLD_SIZE);
        p.setSpacingBefore(10);
        p.setSpacingAfter(5);
        cell.addElement(p);
        PdfPTable table = new PdfPTable(2);
        table.setWidths(new float[]{1, 2});
        table.setWidthPercentage(100);
        for (int i = 0; i < cvContent.getEmployments().size(); i++) {
            PdfPCell c = new PdfPCell();
            Employment e = cvContent.getEmployments().get(i);
            p = PatternHelper.createSimpleParagraph(e.getStartDate() + " - " + e.getEndDate(), normalFont, NORMAL_SIZE);
            c.addElement(p);
            c.setBorder(Rectangle.NO_BORDER);
            table.addCell(c);

            c = new PdfPCell();
            p = PatternHelper.createSimpleParagraph(e.getPosition().toUpperCase() + ", " + e.getCompany() + "." + "\n" + e.getJobDescription(), normalFont, NORMAL_SIZE);
            c.addElement(p);
            c.setBorder(Rectangle.NO_BORDER);
            table.addCell(c);
        }
        cell.addElement(table);

        p = PatternHelper.createSimpleParagraph("U M I E J Ę T N O Ś C I", boldFont, BOLD_SIZE);
        p.setSpacingBefore(10);
        p.setSpacingAfter(5);
        cell.addElement(p);
        for (int i = 0; i < cvContent.getSkills().size(); i++) {
            if (i == cvContent.getSkills().size() - 1) {
                p = PatternHelper.createSimpleParagraph("- " + cvContent.getSkills().get(i) + ".", normalFont, NORMAL_SIZE);
            } else {
                p = PatternHelper.createSimpleParagraph("- " + cvContent.getSkills().get(i) + ",", normalFont, NORMAL_SIZE);
            }
            cell.addElement(p);
        }

        return cell;
    }

    private PdfPCell createRight(CvContent cvContent) throws DocumentException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = PatternHelper.createSimpleParagraph("O  M N I E", boldFont, BOLD_SIZE);
        p.setSpacingAfter(5);
        cell.addElement(p);
        p = PatternHelper.createSimpleParagraph(cvContent.getPersonalInfo().getDescription(), normalFont, NORMAL_SIZE);
        cell.addElement(p);

        p = PatternHelper.createSimpleParagraph("K O N T A K T", boldFont, BOLD_SIZE);
        p.setSpacingBefore(10);
        p.setSpacingAfter(5);
        cell.addElement(p);
        PersonalInfo personalInfo = cvContent.getPersonalInfo();
        p = PatternHelper.createSimpleParagraph(personalInfo.getPhone(), normalFont, NORMAL_SIZE);
        cell.addElement(p);
        p = PatternHelper.createSimpleParagraph(personalInfo.getEmail(), normalFont, NORMAL_SIZE);
        cell.addElement(p);
        p = PatternHelper.createSimpleParagraph(personalInfo.getCity(), normalFont, NORMAL_SIZE);
        cell.addElement(p);

        p = PatternHelper.createSimpleParagraph("W Y K S Z T A Ł C E N I E", boldFont, BOLD_SIZE);
        p.setSpacingBefore(10);
        p.setSpacingAfter(5);
        cell.addElement(p);
        PdfPTable table;
        table = new PdfPTable(2);
        table.setWidths(new float[]{1, 2});
        table.setWidthPercentage(100);

        for (int i = 0; i < cvContent.getEducationList().size(); i++) {
            PdfPCell c = new PdfPCell();
            Education e = cvContent.getEducationList().get(i);
            p = PatternHelper.createSimpleParagraph(e.getStartDate() + " - " + e.getEndDate(), normalFont, NORMAL_SIZE);
            c.addElement(p);
            c.setBorder(Rectangle.NO_BORDER);
            table.addCell(c);

            c = new PdfPCell();
            p = PatternHelper.createSimpleParagraph(e.getSchoolName() + ", " + e.getSubject() + ", " + e.getDegree(), normalFont, NORMAL_SIZE);
            c.addElement(p);
            c.setBorder(Rectangle.NO_BORDER);
            table.addCell(c);
        }
        cell.addElement(table);

        p = PatternHelper.createSimpleParagraph("H O B B Y", boldFont, BOLD_SIZE);
        p.setSpacingBefore(10);
        p.setSpacingAfter(5);
        cell.addElement(p);

        for (int i = 0; i < cvContent.getHobbies().size(); i++) {
            if (i == cvContent.getSkills().size() - 1) {
                p = PatternHelper.createSimpleParagraph("- " + cvContent.getHobbies().get(i) + ".", normalFont, NORMAL_SIZE);
            } else {
                p = PatternHelper.createSimpleParagraph("- " + cvContent.getHobbies().get(i) + ",", normalFont, NORMAL_SIZE);
            }
            cell.addElement(p);
        }

        return cell;

    }

}

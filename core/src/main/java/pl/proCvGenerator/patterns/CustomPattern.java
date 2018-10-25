package pl.proCvGenerator.patterns;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;

import java.util.List;

public class CustomPattern implements Pattern {

    @Override
    public void generateHeader(Document document) {
        document.add(new Paragraph("Hello from custom pattern"));
    }

    @Override
    public void generatePersonalInfoSection(Document document, PersonalInfo personalInfo) {

    }

    @Override
    public void generateEducationSection(Document document, List<Education> education) {

    }

    @Override
    public void generateEmploymentSection(Document document, List<Employment> employments) {

    }

    @Override
    public void generateHobbySection(Document document, String hobby) {

    }

    @Override
    public void generateClause(Document document) {

    }
}

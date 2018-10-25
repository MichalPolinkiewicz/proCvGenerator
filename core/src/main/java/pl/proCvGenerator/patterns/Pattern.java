package pl.proCvGenerator.patterns;

import com.itextpdf.layout.Document;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;

import java.util.List;

public interface Pattern {

    void generateHeader (Document document);
    void generatePersonalInfoSection(Document document, PersonalInfo personalInfo);
    void generateEducationSection(Document document, List<Education> education);
    void generateEmploymentSection(Document document, List<Employment> employments);
    void generateHobbySection(Document document, String hobby);
    void generateClause(Document document);
}

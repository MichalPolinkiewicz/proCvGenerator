package pl.proCvGenerator.patterns;

import com.itextpdf.text.Document;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;

import java.util.List;

public interface Pattern {

    Document prepareDocument();
    void generateHeader (Document document);
    void generatePersonalInfoSection(Document document, PersonalInfo personalInfo);
    void generateDescriptionSection(Document document, String description);
    void generateEducationSection(Document document, List<Education> education);
    void generateEmploymentSection(Document document, List<Employment> employments);
    void generateSkillsSection(Document document, List<String> skills);
    void generateHobbySection(Document document, List<String> hobbyList);
    void generateClause(Document document, String clause);
}

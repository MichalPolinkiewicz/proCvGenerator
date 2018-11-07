package pl.proCvGenerator.patterns;

import com.itextpdf.text.Document;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;

import java.util.List;

public class CustomPattern implements Pattern {

    @Override
    public Document prepareDocument() {
        return new Document();
    }

    @Override
    public void generateHeader(Document document) {

    }

    @Override
    public void generatePersonalInfoSection(Document document, PersonalInfo personalInfo) {

    }

    @Override
    public void generateDescriptionSection(Document document, String description) {

    }

    @Override
    public void generateEducationSection(Document document, List<Education> education) {

    }

    @Override
    public void generateEmploymentSection(Document document, List<Employment> employments) {

    }

    @Override
    public void generateSkillsSection(Document document, List<String> skills) {

    }

    @Override
    public void generateHobbySection(Document document, List<String> hobbyList) {

    }

    @Override
    public void generateClause(Document document, String clause) {

    }
}

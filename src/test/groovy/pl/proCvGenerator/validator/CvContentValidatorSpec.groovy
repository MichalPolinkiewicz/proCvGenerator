package groovy.pl.proCvGenerator.validator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.config.RootConfig
import pl.proCvGenerator.config.WebConfig
import pl.proCvGenerator.dao.CvContent
import pl.proCvGenerator.dao.Education
import pl.proCvGenerator.dao.Employment
import pl.proCvGenerator.dao.PersonalInfo
import pl.proCvGenerator.validator.CvContentValidator
import spock.lang.Specification

@ContextConfiguration(classes = [WebConfig, RootConfig])
@WebAppConfiguration
@ActiveProfiles("test")
class CvContentValidatorSpec extends Specification{

    @Autowired
    CvContentValidator contentValidator

    def "validateCvContent() should return correct CvContent"(){
        given:
        CvContent contentToConvert = new CvContent(new PersonalInfo(), new ArrayList<Education>(), new ArrayList<Employment>(), new ArrayList<String>(), new ArrayList<String>())

        when:
        CvContent converted = contentValidator.validateCvContent(contentToConvert)

        then:
        converted.getPersonalInfo().getName() == ""
        converted.getPersonalInfo().getSurname() == ""
        converted.getPersonalInfo().getPhone() == ""
        converted.getPersonalInfo().getPhone() == ""
        converted.getPersonalInfo().getCity() == ""
        converted.getEducationList().size() == 1
        converted.getEducationList().get(0).getSchoolName() == ""
        converted.getEducationList().get(0).getDegree() == ""
        converted.getEducationList().get(0).getSubject() == ""
        converted.getEducationList().get(0).getStartDate() == ""
        converted.getEducationList().get(0).getEndDate() == ""
    }
}

package pl.proCvGenerator.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.converter.CvContentConverter
import pl.proCvGenerator.converter.EducationConverter
import pl.proCvGenerator.converter.EmploymentConverter
import pl.proCvGenerator.converter.PersonalInfoConverter
import pl.proCvGenerator.validator.PatternValidator
import spock.lang.Specification

@ContextConfiguration(classes = [WebConfig])
@WebAppConfiguration
class WebConfigSpec extends Specification {

    @Autowired
    PatternValidator patternValidator
    @Autowired
    CvContentConverter contentConverter
    @Autowired
    PersonalInfoConverter personalInfoConverter
    @Autowired
    EducationConverter educationConverter
    @Autowired
    EmploymentConverter employmentConverter

    def "beans should be initialized"() {
        expect:
        patternValidator
        contentConverter
        personalInfoConverter
        educationConverter
        employmentConverter
        patternValidator
    }
}

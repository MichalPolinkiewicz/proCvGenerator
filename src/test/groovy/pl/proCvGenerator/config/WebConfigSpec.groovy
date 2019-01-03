package pl.proCvGenerator.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.converter.CvContentConverter
import pl.proCvGenerator.converter.EducationConverter
import pl.proCvGenerator.converter.EmploymentConverter
import pl.proCvGenerator.converter.PersonalInfoConverter
import pl.proCvGenerator.validator.PatternValidator
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [WebConfig, RootConfig])
@WebAppConfiguration
@ActiveProfiles("test")
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
    @Autowired
    @Qualifier("messages")
    Properties properties

    def "beans should be initialized"() {
        expect:
        patternValidator
        contentConverter
        personalInfoConverter
        educationConverter
        employmentConverter
        patternValidator
        properties
    }

    @Unroll
    def "expected property should be present"() {
        when:
        def result = properties.get(key)

        then:
        result

        where:
        key << ["nameSurnameError", "positionError", "leftSideError", "rightSideError", "totalLeft", "totalRight",
                "employmenstLines", "educationsLines", "skillsLines", "hobbiesLines", "descriptionLines", "contactLines",
                "aboutMe", "contact", "education", "hobby", "skills", "experiance"]
    }
}

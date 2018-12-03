package pl.proCvGenerator.converter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.config.WebConfig
import pl.proCvGenerator.dto.Education
import pl.proCvGenerator.model.EducationListWrapperDto
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [WebConfig])
@WebAppConfiguration
class EducationConverterSpec extends Specification {

    @Autowired
    EducationConverter educationConverter

    @Unroll
    def "convertToList() should return converted object with proper values"() {
        given:
        EducationListWrapperDto educationListWrapperDto = new EducationListWrapperDto()
        educationListWrapperDto.addEducation(new Education(school, subject, degree, startDate, endDate))

        when:
        def result = educationConverter.convertToList(educationListWrapperDto)
        def education = result.get(0)

        then:
        result.size() == educationListWrapperDto.getEducationList().size()
        education.getSchoolName() == school
        education.getSubject() == subject
        education.getDegree() == degree
        education.getStartDate() == startDate
        education.getEndDate() == endDate

        where:
        school | subject | degree | startDate | endDate
        null   | "x"     | ""     | "y"       | "211"
        ""     | "xyz"   | "12"   | "0"       | "-1"
    }

    @Unroll
    def "convertToWrapper() should return converted object with proper values"(){
        given:
        List<Education> educations = new ArrayList<>()
        educations.add(new Education(school, subject, degree, startDate, endDate))

        when:
        def result = educationConverter.convertToWrapper(educations)
        def education = result.getEducationList().get(0)
        education.getSchoolName() == school
        education.getSubject() == subject
        education.getDegree() == degree
        education.getStartDate() == startDate
        education.getEndDate() == endDate

        then:
        result.getEducationList().size() == educations.size()

        where:
        school | subject | degree | startDate | endDate
        null   | "x"     | ""     | "y"       | "211"
        ""     | "xyz"   | "12"   | "0"       | "-1"
    }
}

package pl.proCvGenerator.converter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.config.WebConfig
import pl.proCvGenerator.dao.Employment
import pl.proCvGenerator.model.EmploymentListWrapperDto
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [WebConfig])
@WebAppConfiguration
@ActiveProfiles("test")
class EmploymentConverterSpec extends Specification {

    @Autowired
    EmploymentConverter employmentConverter

    @Unroll
    def "convertToWrapper() should return converted object with proper values"() {
        given:
        List<Employment> employmentList = createEmploymentList(company, position, description, start, end)

        when:
        def result = employmentConverter.convertToWrapper(employmentList)
        def employment = result.getEmploymentList().get(0)

        then:
        result.getClass() == EmploymentListWrapperDto
        result.getEmploymentList().size() == employmentList.size()
        employment.getCompany() == company
        employment.getPosition() == position
        employment.getJobDescription() == description
        employment.getStartDate() == start
        employment.getEndDate() == end

        where:
        company | position | description | start | end
        "x"     | "y"      | "z"         | "ł"   | null
        ""      | "ą"      | "h"         | "k"   | "-"
    }

    @Unroll
    def "convertToList should return converted object with proper values"() {
        given:
        EmploymentListWrapperDto employmentListWrapperDto = new EmploymentListWrapperDto()
        employmentListWrapperDto.setEmploymentList(createEmploymentList(company, position, description, start, end))

        when:
        def result = employmentConverter.convertToList(employmentListWrapperDto)
        def employment = result.get(0)

        then:
        result.size() == employmentListWrapperDto.getEmploymentList().size()
        result.getClass() == ArrayList
        employment.getCompany() == company
        employment.getPosition() == position
        employment.getJobDescription() == description
        employment.getStartDate() == start
        employment.getEndDate() == end

        where:
        company | position | description | start | end
        "x"     | "y"      | "z"         | "ł"   | null
        ""      | "ą"      | "h"         | "k"   | "-"
    }

    List<Employment> createEmploymentList(company, position, description, start, end) {
        List<Employment> employments1 = new ArrayList<>()
        Employment employment = new Employment(company, position, description, start, end)
        employments1.add(employment)

        return employments1
    }
}

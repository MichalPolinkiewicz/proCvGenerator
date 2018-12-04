package pl.proCvGenerator.converter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.config.WebConfig
import pl.proCvGenerator.dto.PersonalInfo
import pl.proCvGenerator.model.PersonalInfoDto
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [WebConfig])
@WebAppConfiguration
class PersonalInfoConverterSpec extends Specification {

    @Autowired
    PersonalInfoConverter personalInfoConverter

    @Unroll
    def "convertToDto should return converted object with proper values"() {
        given:
        PersonalInfo personalInfo = new PersonalInfo(name, surname, position, email, page, phone, city, description)

        when:
        def result = personalInfoConverter.convertToDto(personalInfo)

        then:
        result.class == PersonalInfoDto
        result.getName() == name
        result.getSurname() == surname
        result.getPosition() == position
        result.getEmail() == email
        result.getPhone() == phone
        result.getCity() == city
        result.getDescription() == description

        where:
        name | surname | position | email | page | phone | city | description
        "x"  | "y"     | "b"      | "z"   | "y"  | "c"   | ""   | "b"
        "q"  | "ł"     | "c"      | "e"   | "y"  | "r"   | "ą"  | null
    }

    @Unroll
    def "convertToPersonalInfo should return converted object with proper values"() {
        given:
        PersonalInfoDto personalInfoDto = new PersonalInfoDto(name, surname, position, email, page, phone, city, description)

        when:
        def result = personalInfoConverter.convertToPersonalInfo(personalInfoDto)

        then:
        result.class == PersonalInfo
        result.getName() == name
        result.getSurname() == surname
        result.getEmail() == email
        result.getPhone() == phone
        result.getCity() == city
        result.getDescription() == description

        where:
        name | surname | position | email | page | phone | city | description
        "x"  | "y"     | "b"      | "z"   | "y"  | "c"   | ""   | "b"
        "q"  | "ł"     | "c"      | "e"   | "y"  | "r"   | "ą"  | null
    }
}

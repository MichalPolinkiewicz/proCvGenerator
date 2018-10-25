package pl.proCvGenerator.validator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.config.WebConfig
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [WebConfig])
@WebAppConfiguration
class PatternValidatorSpec extends Specification {

    @Autowired
    PatternValidator patternValidator

    @Autowired
    private ApplicationContext applicationContext

    def "patternValidator should be initialized"() {
        expect:
        patternValidator
    }

    @Unroll
    def "patternValidator should return correct pattern"() {
        when:
        def result = patternValidator.choosePattern(patternId)

        then:
        result == applicationContext.getBean(patternId)

        where:
        patternId << ["simplePattern", "customPattern"]
    }

}

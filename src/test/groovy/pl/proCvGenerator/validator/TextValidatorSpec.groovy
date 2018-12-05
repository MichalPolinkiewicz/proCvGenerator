package pl.proCvGenerator.validator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import pl.proCvGenerator.config.RootConfig
import pl.proCvGenerator.exception.TooMuchCharsException
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration(classes = [RootConfig])
class TextValidatorSpec extends Specification {

    @Autowired
    TextValidator charsValidator

    def "charsValidator bean should be injected"() {
        expect:
        charsValidator
    }

    @Unroll
    def "calculateLinesForSentence() should return correct value when given input is valid"() {
        when:
        def result = charsValidator.calculateLinesForSentence(text, max)

        then:
        result == expected

        where:
        text                                                  | max || expected
        "This is example text. It will be formatted."         | 10  || 5
        "This is example text. It will be formatted."         | 25  || 2
        "This is example text. It will be formatted."         | 20  || 3
        "This is example text. It will be formatted."         | 11  || 5
        "This is example text. It will be formatted."         | 12  || 5
        "This is example text. It will be formatted."         | 15  || 3
        "John Smith"                                          | 5   || 2
        "John Smith"                                          | 10  || 1
        "John"                                                | 4   || 1
    }

    @Unroll
    def """calculateLinesForSentence() should throw exception when sentence contains word with greater
        number of chars than max"""() {
        when:
        TextValidator.calculateLinesForSentence(text, max)

        then:
        thrown(TooMuchCharsException)

        where:
        text                                                    || max
        "a"                                                     || 0
        "ab"                                                    || 1
        "a ab"                                                  || 1
        "a ab abc"                                              || 2
        "ab abc abcd"                                           || 3
        "This is example text"                                  || 4
        "This is example text"                                  || 6
        "This is example text. It will be used for unit-tests." || 10
    }

    @Unroll
    def """calculateLinesForSentence() shouldn't throw exception when given max is bigger or equal than number of
        chars in greatest word"""() {
        when:
        TextValidator.calculateLinesForSentence(text, max)

        then:
        notThrown(TooMuchCharsException)

        where:
        text                                               || max
        "1"                                                || 1
        "12"                                               || 2
        "123"                                              || 3
        "This is example text"                             || 7
        "hello my friend"                                  || 6
        "123456789"                                        || 9
        "This is example text. It will be used for tests." || 7
    }
}
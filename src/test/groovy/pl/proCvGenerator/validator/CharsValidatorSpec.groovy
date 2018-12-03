package pl.proCvGenerator.validator

import pl.proCvGenerator.exception.TooMuchCharsException
import spock.lang.Specification
import spock.lang.Unroll

class CharsValidatorSpec extends Specification {

    @Unroll
    def "calculateLinesForSentence() should return correct value when given input is valid"() {
        when:
        def result = CharsValidator.calculateLinesForSentence(text, max)

        then:
        result == expected

        where:
        text                                          | max || expected
        "This is example text. It will be formatted." | 10  || 5
        "This is example text. It will be formatted." | 25  || 2
        "This is example text. It will be formatted." | 20  || 3
        "This is example text. It will be formatted." | 10  || 5
        "This is example text. It will be formatted." | 12  || 4
        "This is example text. It will be formatted." | 15  || 3
        "John Smith"                                  | 6   || 2
        "John Smith"                                  | 10  || 1
    }

    @Unroll
    def "calculateLinesForSentence() should thrown exception when given max is smaller than at least one of the word's"() {
        when:
        CharsValidator.calculateLinesForSentence(text, max)

        then:
        thrown(TooMuchCharsException)

        where:
        text          || max
        "a"           || 0
        "ab"          || 1
        "abc"         || 2
        "a ab"        || 1
        "a ab abc"    || 2
        "a ab abcd"   || 3
        "ab abc abcd" || 3
    }

    @Unroll
    def """calculateLinesForSentence() shouldn't thrown exception when given max is bigger than word with " +
        max number of length"""() {
        when:
        CharsValidator.calculateLinesForSentence(text, max)

        then:
        notThrown(TooMuchCharsException)

        where:
        text                   || max
        "1"                    || 2
        "12"                   || 3
        "123"                  || 4
        "This is example text" || 8
        "hello my friend"      || 7
        "123456789"            || 10
    }
}
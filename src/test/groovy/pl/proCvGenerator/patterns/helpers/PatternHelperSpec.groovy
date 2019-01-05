package pl.proCvGenerator.patterns.helpers

import spock.lang.Specification
import spock.lang.Unroll

class PatternHelperSpec extends Specification {

    @Unroll
    def "createParagraphWithExtraSpace() should return text with extra space between chars"() {
        when:
        def result = PatternHelper.createParagraphWithExtraSpace(text)

        then:
        result == expected

        where:
        text   || expected
        "a"    || "a"
        "ab"   || "a b"
        "abc"  || "a b c"
        "abcd" || "a b c d"
    }
}

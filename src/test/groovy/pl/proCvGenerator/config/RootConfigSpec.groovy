package pl.proCvGenerator.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import pl.proCvGenerator.patterns.Pattern
import pl.proCvGenerator.pdf.PdfCreator
import spock.lang.Specification

@ContextConfiguration(classes = [RootConfig])
class RootConfigSpec extends Specification {

    @Autowired
    Map<String, Pattern> patterns
    @Autowired
    PdfCreator pdfCreator

    def "expected patterns beans should be created"() {
        expect:
        patterns.get("1")
        patterns.get("2")
        patterns.get("3")
        patterns.size() == 3
    }

    def "pdfCreator bean should be created"() {
        expect:
        pdfCreator
    }

}
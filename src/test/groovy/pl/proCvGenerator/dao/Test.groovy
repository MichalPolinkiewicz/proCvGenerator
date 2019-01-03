package groovy.pl.proCvGenerator.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.config.RootConfig
import pl.proCvGenerator.config.WebConfig
import pl.proCvGenerator.config.persistence.PersistenceConfig
import pl.proCvGenerator.dao.Dog
import pl.proCvGenerator.service.DbService
import spock.lang.Specification

@ContextConfiguration(classes = [PersistenceConfig, RootConfig, WebConfig])
@WebAppConfiguration
@ActiveProfiles("test")
class Test extends Specification {

    @Autowired
    DbService dbService

    def "simple test"() {
        given:
        Dog dog = new Dog("Snorli")

        when:
        dbService.save(dog)

        then:
        dbService.getAllDogs().size() == 1
    }
}

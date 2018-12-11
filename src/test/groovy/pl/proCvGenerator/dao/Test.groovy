package groovy.pl.proCvGenerator.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.config.persistence.PersistenceConfig
import pl.proCvGenerator.config.RootConfig
import pl.proCvGenerator.config.WebConfig
import pl.proCvGenerator.dao.Dog
import pl.proCvGenerator.repository.DogRepository
import spock.lang.Specification

@ContextConfiguration(classes = [PersistenceConfig, RootConfig, WebConfig])
@WebAppConfiguration
@ActiveProfiles("test")
class Test extends Specification{

    @Autowired
    DogRepository dogRepository

    def "simple test"(){
        given:
        Dog dog = new Dog("Snorli")

        when:
        def result = dogRepository.save(dog)

        then:
        result
    }
}

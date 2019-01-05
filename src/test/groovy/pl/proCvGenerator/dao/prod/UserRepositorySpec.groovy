package groovy.pl.proCvGenerator.dao.prod

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import pl.proCvGenerator.config.RootConfig
import pl.proCvGenerator.config.WebConfig
import pl.proCvGenerator.config.persistence.PersistenceConfig
import pl.proCvGenerator.dao.CvContent
import pl.proCvGenerator.dao.Education
import pl.proCvGenerator.dao.Employment
import pl.proCvGenerator.dao.PersonalInfo
import pl.proCvGenerator.dao.User
import pl.proCvGenerator.service.DbService
import spock.lang.Specification

@ContextConfiguration(classes = [PersistenceConfig, RootConfig, WebConfig])
@WebAppConfiguration
@ActiveProfiles("test")
class UserRepositorySpec extends Specification{

    @Autowired
    DbService dbService

    def "test"(){
        given:
        CvContent content = new CvContent()

        PersonalInfo personalInfo = new PersonalInfo("Janusz", "Nowak", "kierowca",
                "janusz.nowak@gmail.com", "nowak.com", "567-345-982", "Wrocław",
                "Przystojny, umieśniony i wysportowany kierowca kat. b gotowy do natychmiastowego działania. Posiadam aktualne psychotesty i wszystkie uprawnienia")
        content.setPersonalInfo(personalInfo)

        Education technikum = new Education("Technikum Samochodowe nr 6 we Wrocławiu","mechanik pojazdów samochodowych","technik-mechanik", "2000", "2004")
        Education pods = new Education("Szkoła Podstawowa nr 23 we Wrocławiu", "klasa ogólna", "wykształcenie podstawowe", "1995", "2000")
        List<Education> educationList = new ArrayList<>()
        educationList.add(technikum)
        educationList.add(pods)
        content.setEducationList(educationList)

        Employment employment = new Employment("company1", "kierowca C + E", "kierowca międzynarodowy - Nirmcy, Belgia, Francja", "2010", "2018")
        Employment employment1 = new Employment("company2", "kierowca kat. B", "przewóz towarów na długich trasach", "2005", "2010")
        List<Employment> employmentList = new ArrayList<>()
        employmentList.add(employment)
        employmentList.add(employment1)
        content.setEmployments(employmentList)

        List<String> skills = new ArrayList<>()
        skills.add("prawo jazdy kat. B, C, E")
        skills.add("znajomość języka niemieckiego - poziom b1")
        skills.add("umiejętność naprawy pojazdów mechanicznych")
        skills.add("wieloletnie doświadczenie")
        skills.add("brak zobowiązań")
        content.setSkills(skills)

        List<String> hobbies = new ArrayList<>()
        hobbies.add("majsterkowanie")
        hobbies.add("picie alkoholu na parkingach")
        content.setHobbies(hobbies)

        User user = new User("janusz123", "password", content)

        when:
        dbService.saveUser(user)

        then:
        dbService.getAllUsers().size() == 1
    }
}

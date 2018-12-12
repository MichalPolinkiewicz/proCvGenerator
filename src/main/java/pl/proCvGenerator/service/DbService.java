package pl.proCvGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.dao.Dog;
import pl.proCvGenerator.repository.DogRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class DbService {

    @Autowired
    private DogRepository dogRepository;

    public void save(Dog dog){
        dogRepository.save(dog);
    }

    public List<Dog> getAllDogs(){
        return dogRepository.findAll();
    }
}

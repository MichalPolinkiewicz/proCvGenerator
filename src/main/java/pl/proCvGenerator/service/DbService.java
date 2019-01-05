package pl.proCvGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.dao.User;
import pl.proCvGenerator.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class DbService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser (User user){
        userRepository.save(user);
    }

    public Optional<User> getUserByLogin(String login){
        return userRepository.getUserByLogin(login);
    }

    public void deleteUser(User user) {userRepository.delete(user);}

    public List<User> getAllUsers(){return userRepository.findAll();}
}

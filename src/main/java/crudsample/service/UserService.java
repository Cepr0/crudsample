package crudsample.service;

import crudsample.domain.User;

import java.util.List;

/**
 * Created by Cepro on 16.07.2016.
 */
public interface UserService {
    User save(User user);
    void delete(int id);
    void delete(User user);
    List<User> getAll();
    List<User> getAllByName(String name);
    List<User> getAllById(int id);
}

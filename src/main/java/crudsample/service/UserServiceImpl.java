package crudsample.service;

import crudsample.domain.User;
import crudsample.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.vaadin.viritin.LazyList;
import org.vaadin.viritin.SortableLazyList;

import java.util.List;

/**
 * Created by Cepro on 16.07.2016.
 */

@Service
public class UserServiceImpl implements UserService {
    private final int PAGE_SIZE = 25;

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User save(User user) {
        user.updateDate();
        return userRepo.save(user);
    }

    @Override
    public void delete(int id) {
        userRepo.delete(id);
    }

    @Override
    public void delete(User user) {
        delete(user.getId());
    }

    @Override
    public List<User> getAll() {
//        return userRepo.findAll();
        return new SortableLazyList<>(
                (SortableLazyList.SortablePagingProvider<User>) (firstRow, asc, sortProperty) -> userRepo.findAll(
                        new PageRequest(
                                firstRow / PAGE_SIZE,
                                PAGE_SIZE,
                                asc ? Sort.Direction.ASC : Sort.Direction.DESC,
                                sortProperty == null ? "id" : sortProperty
                        )
                ).getContent(),
                (LazyList.CountProvider) () -> (int) userRepo.count(),
                PAGE_SIZE
        );
    }

    @Override
    public List<User> getAllByName(String name) {
        if (StringUtils.isEmpty(name))
            return getAll();
        else
            return userRepo.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public List<User> getAllById(int id) {
        return userRepo.findById(id);
    }
}

package app.service;

import app.entities.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" User not found for id :: " + id);
        }
        return user;
    }

    @Override
    public void deleteById(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepository.findAll(pageable);
    }
}

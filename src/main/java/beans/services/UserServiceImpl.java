package beans.services;

import beans.daos.UserDAO;
import beans.models.Ticket;
import beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/1/2016
 * Time: 7:30 PM
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAO") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User register(User user) {
        //TODO: test!
        User test = userDAO.create(user);
        return test;
    }

    public void remove(User user) {
        userDAO.delete(user);
    }

    public User getById(long id) {
        return userDAO.get(id);
    }

    public User getUserByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userDAO.getAllByName(name);
    }

    public User getUserByName(String name) {
        List<User> users = userDAO.getAllByName(name);
        if(users.size()==1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }
    public List<Ticket> getBookedTickets() {
        throw new UnsupportedOperationException("not implemented yet");
    }
}

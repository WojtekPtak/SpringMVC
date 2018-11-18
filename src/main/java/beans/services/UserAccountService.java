package beans.services;

import beans.models.User;
import beans.models.UserAccount;

import java.util.List;


public interface UserAccountService {

    UserAccount create(UserAccount account);

    void remove(UserAccount account);

    UserAccount getById(long id);

    UserAccount getByUser(User user);

    List<UserAccount> getAllAccounts();

}

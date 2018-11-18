package beans.daos;


import beans.models.User;
import beans.models.UserAccount;

import java.util.List;
import java.util.Objects;


public interface UserAccountDAO {

    UserAccount create(UserAccount account);

    UserAccount update(UserAccount account);

    void delete(UserAccount account);

    UserAccount getById(long id);

    UserAccount getByUser(User user);

    List<UserAccount> getAll();

    static void validateUserAccount(UserAccount account) {
        if (Objects.isNull(account)) {
            throw new NullPointerException("UserAccount is [null]");
        }
        if (Objects.isNull(account.getUser())) {
            throw new NullPointerException("Account's user is [null]. UserAccount: [" + account + "]");
        }
    }
}

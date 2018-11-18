package beans.daos.inmemory;

import beans.daos.UserAccountDAO;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Repository("inMemoryUserAccountDAO")
public class InMemoryUserAccountDAO implements UserAccountDAO {
    private static final Map<Long, UserAccount> db = new TreeMap<>();
    private static final Map<User, UserAccount> dbUserIndex = new TreeMap<>();

    @Override
    public UserAccount create(UserAccount account) {
        UserAccountDAO.validateUserAccount(account);

        if (db.containsKey(account.getId()))
            throw new IllegalStateException(
                    String.format("Unable to store UserAccount: [%s]. Account with id: [%s] is already created.", account, account.getId()));
        if (dbUserIndex.containsKey(account.getUser()))
            throw new IllegalStateException(
                    String.format("Unable to store UserAccount: [%s]. Account with userName: [%s] is already created.", account,
                                  account.getUser()));

        final UserAccount accountToStore = account.getId() < 0 ? account.withId(db.size()) : account;

        db.put(accountToStore.getId(), accountToStore);
        dbUserIndex.put(accountToStore.getUser(), accountToStore);

        return accountToStore;
    }

    @Override
    public UserAccount update(UserAccount account) {
        UserAccountDAO.validateUserAccount(account);
        delete(new UserAccount(account.getId(), account.getUser(), account.getBalance()));
        return create(account);
    }

    @Override
    public void delete(UserAccount account) {
        UserAccountDAO.validateUserAccount(account);
        db.remove(account.getId());
        dbUserIndex.remove(account.getUser());
    }

    @Override
    public UserAccount getById(long id) {
        return db.get(id);
    }

    @Override
    public UserAccount getByUser(User user) {
        return dbUserIndex.get(user);
    }

    @Override
    public List<UserAccount> getAll() {
        return db.values().stream().collect(Collectors.toList());
    }

}

package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.models.User;
import beans.models.UserAccount;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;


@Repository(value = "userAccountDAO")
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {

    @Override
    public UserAccount create(UserAccount account) {
        UserAccountDAO.validateUserAccount(account);
        UserAccount byUser = getByUser(account.getUser());
        if (Objects.nonNull(byUser)) {
            throw new IllegalStateException(
                    String.format("Unable to store account: [%s]. UserAccount with user: [%s] is already created.", account,
                                  account.getUser()));
        } else {
            Long accountId = (Long) getCurrentSession().save(account);
            return account.withId(accountId);
        }
    }

    @Override
    public UserAccount update(UserAccount account) {
        return ((UserAccount) getCurrentSession().merge(account));
    }

    @Override
    public void delete(UserAccount account) {
        UserAccountDAO.validateUserAccount(account);
        getCurrentSession().delete(account);
    }

    @Override
    public UserAccount getById(long id) {
        return getCurrentSession().get(UserAccount.class, id);
    }

    @Override
    public UserAccount getByUser(User user) {
        return ((UserAccount) createBlankCriteria(UserAccount.class).add(Restrictions.eq("user", user)).uniqueResult());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserAccount> getAll() {
        return ((List<UserAccount>) createBlankCriteria(UserAccount.class).list());
    }
}

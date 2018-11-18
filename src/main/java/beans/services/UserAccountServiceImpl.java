package beans.services;

import beans.daos.UserAccountDAO;
import beans.models.Ticket;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountDAO accountDAO;

    @Autowired
    public UserAccountServiceImpl(@Qualifier("userAccountDAO") UserAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public UserAccount create(UserAccount account) {
        return accountDAO.create(account);
    }

    @Override
    public void remove(UserAccount account) {
        accountDAO.delete(account);
    }

    @Override
    public UserAccount getById(long id) {
        return accountDAO.getById(id);
    }

    @Override
    public UserAccount getByUser(User user) {
        return accountDAO.getByUser(user);
    }

    @Override
    public List<UserAccount> getAllAccounts() {
        return accountDAO.getAll();
    }
    public List<Ticket> getBookedTickets() {
        throw new UnsupportedOperationException("not implemented yet");
    }


}

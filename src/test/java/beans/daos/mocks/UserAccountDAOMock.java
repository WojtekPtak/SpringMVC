package beans.daos.mocks;

import beans.daos.db.UserAccountDAOImpl;
import beans.models.UserAccount;

import java.util.List;


public class UserAccountDAOMock extends UserAccountDAOImpl {

    private final List<UserAccount> accounts;

    public UserAccountDAOMock(List<UserAccount> accounts) {
        this.accounts = accounts;
    }

    public void init() {
        cleanup();
        accounts.forEach(this :: create);
    }

    public void cleanup() {
        System.out.println("deleting ");
        System.out.println(getAll());
        getAll().forEach(this :: delete);
    }


}

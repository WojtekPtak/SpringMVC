package beans.models;


public class UserAccount {
    private long id;
    private Double balance;
    private User user;

    public UserAccount() {
    }

    public UserAccount(User user) {
        this(-1, user, 0.0);
    }

    public UserAccount(String userName, double balance) {
        this(-1, null, balance);
    }

    public UserAccount(User user, double balance) {
        this(-1, user, balance);
    }

    public UserAccount(long id, User user, double balance) {
        this.id = id;
        this.balance = balance;
        this.user = user;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void withdraw(Double amount) throws IllegalArgumentException {
        if(amount>balance || amount.isInfinite() || amount.isNaN()) {
            throw new IllegalArgumentException(String.format("Value %f exceeds user '%s' balance equals %f!",
                    amount, user.getName(), balance));
        }
        balance -= amount;
    }

    public void topUp(Double amount) {
        if(amount < 0.0 || amount.isInfinite() || amount.isNaN()) {
            throw new IllegalArgumentException(String.format("Illegal parameter %f for top up user '%s' account!",
                    amount, user.getName()));
        }
        this.balance += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserAccount))
            return false;

        UserAccount account = (UserAccount) o;
        if (user != null ? !user.equals(account.user) : account.user != null)
            return false;
        return balance != null ? balance.equals(account.balance) : account.balance == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + balance.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
               "id=" + id +
               ", user=" + user +
               ", balance=" + balance +
               '}';
    }

    public UserAccount withId(long id) {
        return new UserAccount(id, user, balance);
    }
}

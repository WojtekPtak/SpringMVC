package beans.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static beans.models.UserRole.REGISTERED_USER;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/1/2016
 * Time: 7:35 PM
 */
public class User {

    private long      id;
    private String    email;
    private String    name;
    private String    password;
    private String    roles;

    // TODO: problem to solve: without it (and only with specified date format!) binding date in FreeMarker causes error :/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    public User() {
    }

    public User(long id, String email, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.roles = REGISTERED_USER.toString();
    }

    public User(long id, String email, String name, LocalDate birthday, String roles) {
        this(id, email, name, birthday);
        this.roles = roles!=null ? roles : UserRole.REGISTERED_USER.toString();
    }

    public User(String email, String name, LocalDate birthday) {
        this(-1, email, name, birthday);
    }

    public User(String email, String name, LocalDate birthday, String roles) {
        this(-1, email, name, birthday, roles);
    }

    public User withId(long id) {
        return new User(id, email, name, birthday, roles);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getRoles() {
        return roles;
    }

    public List<UserRole> getRolesList() {
        return Arrays.stream(UserRole.values()).collect(Collectors.toList());
    }

    public boolean setRoles(String roles) {
        if(roles == null) {
            this.roles = UserRole.REGISTERED_USER.toString();
            return true;
        }
        if(Arrays.stream(roles.split(","))
                .allMatch( r -> Arrays.stream(UserRole.values()).anyMatch(r2 -> r2.name().equals(r)))) {
            this.roles = roles;
            return true;
        }
        return  false;
    }

    public void addRole(UserRole role) {
        if(!this.roles.isEmpty()) {
            this.roles += ",";
        }
        this.roles += role.toString();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != user.id)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null)
            return false;
        if (name != null ? !name.equals(user.name) : user.name != null)
            return false;
        if (!roles.equals(user.roles))
            return false;
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + roles.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", email='" + email + '\'' +
               ", name='" + name + '\'' +
               ", birthday=" + birthday +
               ", roles=" + roles +
               '}';
    }
}

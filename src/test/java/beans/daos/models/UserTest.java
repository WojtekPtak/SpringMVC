package beans.daos.models;

import beans.configuration.AppConfiguration;
import beans.models.User;
import beans.models.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class})
public class UserTest {

    private final String REGISTERED_USER = UserRole.REGISTERED_USER.toString();
    private final String BOOKING_MANAGER = UserRole.BOOKING_MANAGER.toString();
    private User usr;

    @Before
    public void init() {
        usr = new User("zenek@wp.pl", "Wojciech Ptak", LocalDate.now().minus(30, ChronoUnit.YEARS));
    }

    @After
    public void cleanup() {
    }

    @Test
    public void testUserRole() {
        userHasDefaultRole();
    }

    @Test
    public void testUserRoleAddOK() {
        usr.addRole(UserRole.BOOKING_MANAGER);
        assertEquals(usr.getRoles().split(",").length, 2);

        assertEquals(usr.getRoles().split(",")[0], REGISTERED_USER);
        assertEquals(usr.getRoles().split(",")[1], BOOKING_MANAGER);
    }

    private void userHasDefaultRole() {
        assertEquals(usr.getRoles(), REGISTERED_USER);
    }

    @Test
    public void testUserRoleSetOK() {
        StringBuilder usrRoles = new StringBuilder()
                .append(UserRole.REGISTERED_USER)
                .append(",")
                .append(UserRole.BOOKING_MANAGER);

        assertTrue(usr.setRoles(usrRoles.toString()));
        assertEquals(usr.getRoles().split(",").length, 2);

        assertEquals(usr.getRoles().split(",")[0], REGISTERED_USER);
        assertEquals(usr.getRoles().split(",")[1], BOOKING_MANAGER);
    }

    @Test
    public void testUserRoleSetFAIL() {
        StringBuilder usrRoles = new StringBuilder()
                .append("FakeRole")
                .append(",")
                .append(UserRole.BOOKING_MANAGER);

        assertFalse(usr.setRoles(usrRoles.toString()));
        userHasDefaultRole();


        usrRoles = new StringBuilder()
                .append(UserRole.BOOKING_MANAGER)
                .append(",")
                .append("FakeRole");

        assertFalse(usr.setRoles(usrRoles.toString()));
        userHasDefaultRole();

    }

}


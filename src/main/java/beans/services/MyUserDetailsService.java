package beans.services;


import beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {
        User user =  userService.getUserByName(name);
        //final List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                user.getRolesList());
    }

}

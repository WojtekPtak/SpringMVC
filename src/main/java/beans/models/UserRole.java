package beans.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    REGISTERED_USER,
    BOOKING_MANAGER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}

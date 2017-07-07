package com.github.vlsidlyarevich.unity.db.domain;

import com.github.vlsidlyarevich.unity.web.dto.UserDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
public class User extends DbModel implements UserDetails {

    private static final long serialVersionUID = 5336313491937469684L;

    @Id
    private String id;
    private List<Authority> authorities;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean isEnabled;

    public User(final List<Authority> authorities, final String username,
                final String password, final boolean accountNonExpired,
                final boolean accountNonLocked, final boolean credentialsNonExpired,
                final boolean isEnabled) {
        this.authorities = authorities;
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public static User fromDTO(final UserDTO dto) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_USER);

        return new User(authorities, dto.getUsername(), dto.getPassword(),
                false, false, false,
                true);
    }

    public void addAuthority(final Authority authority) {
        this.authorities.add(authority);
    }
}

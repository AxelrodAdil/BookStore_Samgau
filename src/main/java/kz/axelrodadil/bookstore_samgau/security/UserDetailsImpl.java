package kz.axelrodadil.bookstore_samgau.security;

import kz.axelrodadil.bookstore_samgau.model.Role;
import kz.axelrodadil.bookstore_samgau.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsImpl")
public class UserDetailsImpl implements UserDetailsService {

    private final RoleRepository roleRepository;

    @Autowired
    public UserDetailsImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Role user = roleRepository.findByRoleEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Error! Doesn't exist."));
        return UserSecurity.getFromModelRoles(user);
    }
}

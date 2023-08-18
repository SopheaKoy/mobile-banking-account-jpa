package co.sophea.bankingaccount.security;

import co.sophea.bankingaccount.api.user.User;
import co.sophea.bankingaccount.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user  = userRepository.findByEmailIAndIsDeletedFalse(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "You with this email is not found!!!!!"
                ));

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);

        return customUserDetails;
    }
}

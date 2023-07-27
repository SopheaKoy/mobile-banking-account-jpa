package co.sophea.bankingaccount.init;

import co.sophea.bankingaccount.api.authority.Authority;
import co.sophea.bankingaccount.api.authority.AuthorityRepository;
import co.sophea.bankingaccount.api.authority.Role;
import co.sophea.bankingaccount.api.authority.RoleRepository;
import co.sophea.bankingaccount.api.user.User;
import co.sophea.bankingaccount.api.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitialization {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    @PostConstruct
    public void init(){

        // user authority
        Authority userRead = Authority.builder().name("user:read").build();
        Authority userWrite = Authority.builder().name("user:write").build();
        Authority userUpdate = Authority.builder().name("user:update").build();
        Authority userDelete = Authority.builder().name("user:delete").build();
        // account Authority
        Authority accountRead =Authority.builder().name("account:read").build();
        Authority accountWrite =  Authority.builder().name("account:write").build();
        Authority accountUpdate =  Authority.builder().name("account:update").build();
        Authority accountDelete = Authority.builder().name("account:delete").build();
        //transaction Authority
        Authority transactionRead = Authority.builder().name("transaction:read").build();
        Authority transactionWrite = Authority.builder().name("transaction:write").build();
        Authority transactionUpdate = Authority.builder().name("transaction:update").build();
        Authority transactionDelete = Authority.builder().name("transaction:delete").build();

        List<Authority> authority = List.of(
                userRead , userWrite ,userDelete ,userUpdate ,
                accountRead , accountWrite , accountUpdate ,accountDelete,
                transactionRead,transactionWrite ,transactionDelete ,transactionUpdate
        );
        authorityRepository.saveAll(authority);

        List<Role> roles = List.of(
                Role.builder()
                        .authorities(authority)
                        .name("ADMIN")
                        .build(),
                Role.builder()
                        .authorities(List.of(
                                userRead,userDelete,userUpdate,
                                accountRead,accountDelete,accountUpdate,
                                transactionRead,transactionDelete,transactionUpdate
                        ))
                        .name("MANAGER").build(),
                Role.builder()
                        .authorities(List.of(
                                userRead,userUpdate, userUpdate,
                                accountRead,accountWrite ,accountUpdate,
                                transactionRead, transactionWrite
                        ))
                        .name("CUSTOMER").build()
        );
        roleRepository.saveAll(roles);

        User user = User.builder()
                .uuid(UUID.randomUUID())
                .name("Administrator")
                .email("admin@phea.co")
                .gender("Male")
                .phoneNumber("093 339 993")
                .isVerified(true)
                .isStudent(false)
                .isDeleted(false)
                .build();
    }
}

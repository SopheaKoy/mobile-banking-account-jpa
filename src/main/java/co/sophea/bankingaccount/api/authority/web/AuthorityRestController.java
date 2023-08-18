package co.sophea.bankingaccount.api.authority.web;

import co.sophea.bankingaccount.api.authority.Role;
import co.sophea.bankingaccount.api.authority.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authorities")
public class AuthorityRestController {

    private final RoleRepository roleRepository;
    @GetMapping
    public Iterable<Role> getAuthority(){

        Iterable<Role> roleList = roleRepository.findAll();

        return roleList;
    }
}

package co.sophea.bankingaccount.api.accounttype.web;

import co.sophea.bankingaccount.api.accounttype.AccountType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account_types")
public class AccountTypeRestController {

    @GetMapping
    public List<AccountType> getAll(){
        return null;
    }
}

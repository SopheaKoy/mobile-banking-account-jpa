package co.sophea.bankingaccount.api.account;

import co.sophea.bankingaccount.api.accounttype.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "account_no")
    private String accountNo;
    @Column(name = "account_name")
    private String accountName;
    @Column(name="pin")
    private String pin;
    @Column(name = "transfer_limit")
    private Integer transferLimit;

    @Column(name = "account_type")
    @OneToMany
    @JoinColumn(name = "id")
    private List<AccountType> accountType;

}

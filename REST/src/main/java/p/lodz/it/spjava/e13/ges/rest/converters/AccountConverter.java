package p.lodz.it.spjava.e13.ges.rest.converters;

import p.lodz.it.spjava.e13.ges.dto.accounts.AccountDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateAccountCmd;
import p.lodz.it.spjava.e13.ges.dto.accounts.UpdateAccountCmd;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Account;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountConverter {


    public static AccountDto toDto(Account account) {
        return new AccountDto(account.getId(),
                account.getVersion(),
                account.getCreationDate(),
                account.getLastModificationDate(),
                account.getRole(),
                account.getLogin(),
                account.getEmail(),
                account.getFirstName(),
                account.getLastName(),
                account.getPhoneNumber(),
                account.isActive(),
                account.isConfirmed()
        );

    }

    public static List<AccountDto> toDto(List<Account> accounts) {
        return (null == accounts ? null : accounts.stream()
                .filter(Objects::nonNull)
                .map(element -> toDto(element))
                .collect(Collectors.toList())
        );
    }

//    public static List<AccountDto> toDtoFromAdmin(List<Admin> admins) {
//        return (null == admins ? null : admins.stream()
//                .filter(Objects::nonNull)
//                .map(element -> toDto(element))
//                .collect(Collectors.toList())
//        );
//    }



    public static Account fromUpdateAccountCmd(UpdateAccountCmd cmd) {
        return new Account(null,
                null,
                cmd.getEmail(),
                cmd.getFirstName(),
                cmd.getLastName(),
                cmd.getPhoneNumber());

    }


    //toDto For Serviceman, ServiceManager,



}

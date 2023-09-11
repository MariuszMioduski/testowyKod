package p.lodz.it.spjava.e13.ges.rest.converters;

import p.lodz.it.spjava.e13.ges.dto.accounts.AdminDto;
import p.lodz.it.spjava.e13.ges.dto.accounts.CreateAdminCmd;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Admin;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdminConverter {

    public static AdminDto toDto(Admin admin){
        return new AdminDto(admin.getId(),
                admin.getVersion(),
                admin.getCreationDate(),
                admin.getLastModificationDate(),
                admin.getRole(),
                admin.getLogin(),
                admin.getEmail(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getPhoneNumber(),
                admin.isActive(),
                admin.isConfirmed(),
                admin.getWorkAlarmPhone());


    }

    public static List<AdminDto> toDto(List<Admin> admins) {
        return (null == admins ? null : admins.stream()
                .filter(Objects::nonNull)
                .map(element -> toDto(element))
                .collect(Collectors.toList())
        );
    }
        public static Admin fromCreateAdminCmd(CreateAdminCmd cmd){
            return new Admin(cmd.getLogin(),
                    cmd.getPassword(),
                    cmd.getEmail(),
                    cmd.getFirstName(),
                    cmd.getLastName(),
                    cmd.getPhoneNumber(),
                    cmd.getWorkAlarmPhone());
        }
    }


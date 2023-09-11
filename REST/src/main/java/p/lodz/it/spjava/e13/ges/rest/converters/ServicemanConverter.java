package p.lodz.it.spjava.e13.ges.rest.converters;


import p.lodz.it.spjava.e13.ges.dto.accounts.CreateServicemanCmd;
import p.lodz.it.spjava.e13.ges.dto.accounts.ServicemanDto;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.Serviceman;
import p.lodz.it.spjava.e13.ges.rest.model.accounts.ServicemanType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServicemanConverter {


    public static ServicemanType servicemanTypeFromString(String servicemanTypeStr) {
        return ServicemanType.valueOf(servicemanTypeStr);
    }
    public static String serviceTypeToString(ServicemanType servicemanType) { return servicemanType.name(); }
    public static ServicemanDto toDto(Serviceman serviceman){
        return new ServicemanDto(serviceman.getId(),
                serviceman.getVersion(),
                serviceman.getCreationDate(),
                serviceman.getLastModificationDate(),
                serviceman.getRole(),
                serviceman.getLogin(),
                serviceman.getEmail(),
                serviceman.getFirstName(),
                serviceman.getLastName(),
                serviceman.getPhoneNumber(),
                serviceman.isActive(),
                serviceman.isConfirmed(),
                serviceman.getDepartment(),
                serviceman.getAdditionalWorkingPermissions(),
                serviceTypeToString(serviceman.getServicemanType()));


    }

    public static List<ServicemanDto> toDto(List<Serviceman> servicemen) {
        return (null == servicemen ? null : servicemen.stream()
                .filter(Objects::nonNull)
                .map(element -> toDto(element))
                .collect(Collectors.toList())
        );
    }
        public static Serviceman fromCreateServicemanCmd(CreateServicemanCmd cmd){
            return new Serviceman(cmd.getLogin(),
                    cmd.getPassword(),
                    cmd.getEmail(),
                    cmd.getFirstName(),
                    cmd.getLastName(),
                    cmd.getPhoneNumber(),
                    cmd.getDepartment(),
                    cmd.getAdditionalWorkingPermissions()
                   );
        }
    }


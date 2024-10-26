package com.aissatna.medilinkbackend.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class CurrentUserDTO {
    private  Long id;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String photoUrl;
    private  String roleName;
    private  String cabinetName;
    private  String cabinetAddress;
}
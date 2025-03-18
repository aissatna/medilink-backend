package com.aissatna.medilinkbackend.mapper;

import com.aissatna.medilinkbackend.configuration.app.AppContext;
import com.aissatna.medilinkbackend.dto.user.CurrentUserDTO;
import com.aissatna.medilinkbackend.dto.user.UserLineDTO;
import com.aissatna.medilinkbackend.dto.user.nurse.UserDTO;
import com.aissatna.medilinkbackend.model.User;
import com.aissatna.medilinkbackend.model.enums.GenderEnum;
import com.aissatna.medilinkbackend.model.enums.RoleEnum;
import com.aissatna.medilinkbackend.service.file.IFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final IFileService fileService;
    private final AppContext appContext;

    public CurrentUserDTO mapToCurrentUserDTO(User user){
        return new CurrentUserDTO()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setPhotoUrl(fileService.getFullFilePath(user.getPhotoUrl()))
                .setRoleName(user.getRole().name())
                .setCabinetName(user.getCabinet().getName())
                .setCabinetAddress(user.getCabinet().getAddress());
    }

    public UserLineDTO mapToUserLineDTO(User user){
        return new UserLineDTO()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setGender(user.getGender().name())
                .setPhone(user.getPhone())
                .setEmail(user.getEmail())
                .setPhotoUrl(fileService.getFullFilePath(user.getPhotoUrl()));

    }

    public User mapToUserEntity(UserDTO userDTO, RoleEnum role) {
        GenderEnum gender = userDTO.getGender().equals("M") ? GenderEnum.M : GenderEnum.F;
        return new User()
                .setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setEmail(userDTO.getEmail())
                .setPhone(userDTO.getPhone())
                .setGender(gender)
                .setRole(role)
                .setPhotoUrl(null)
                .setCabinet(appContext.getCurrentUser().getCabinet());
    }
}

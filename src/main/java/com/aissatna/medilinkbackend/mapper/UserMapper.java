package com.aissatna.medilinkbackend.mapper;

import com.aissatna.medilinkbackend.dto.user.CurrentUserDTO;
import com.aissatna.medilinkbackend.model.User;
import com.aissatna.medilinkbackend.service.minio.MinioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final MinioService minioService;

    public CurrentUserDTO mapToDTO(User user){
        return new CurrentUserDTO()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setPhotoUrl(minioService.getUrl(user.getPhotoUrl()))
                .setRoleName(user.getRole().name())
                .setCabinetName(user.getCabinet().getName())
                .setCabinetAddress(user.getCabinet().getAddress());
    }

}

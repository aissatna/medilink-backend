package com.aissatna.medilinkbackend.service.user;

import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.dto.user.UserLineDTO;
import com.aissatna.medilinkbackend.dto.user.nurse.UserDTO;
import com.aissatna.medilinkbackend.model.User;
import com.aissatna.medilinkbackend.model.enums.RoleEnum;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {


    User getUserById(Long userId);
    User getCurrentUser();
    PageDTO<UserLineDTO> getPaginatedUsersByRole(Pageable pageable, String search, RoleEnum roleEnum);
    User addUser(UserDTO userDTO, MultipartFile imageFile, RoleEnum role);
    User updateUser(Long userId, UserDTO userDTO, MultipartFile imageFile);
    void deleteUser(Long userId);
    void sendUsersExportByEmail(String search, RoleEnum role);
}

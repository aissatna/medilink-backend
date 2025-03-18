package com.aissatna.medilinkbackend.service.user;


import com.aissatna.medilinkbackend.configuration.app.AppContext;
import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.dto.user.UserLineDTO;
import com.aissatna.medilinkbackend.dto.user.nurse.UserDTO;
import com.aissatna.medilinkbackend.exception.ResourceNotFoundException;
import com.aissatna.medilinkbackend.mapper.UserMapper;
import com.aissatna.medilinkbackend.model.User;
import com.aissatna.medilinkbackend.model.enums.GenderEnum;
import com.aissatna.medilinkbackend.model.enums.RoleEnum;
import com.aissatna.medilinkbackend.repository.user.UserProjectionRepository;
import com.aissatna.medilinkbackend.repository.user.UserRepository;

import com.aissatna.medilinkbackend.service.email.IEmailService;
import com.aissatna.medilinkbackend.service.export.ExportUserDataService;
import com.aissatna.medilinkbackend.service.file.IFileService;
import com.aissatna.medilinkbackend.util.PasswordUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserProjectionRepository userProjectionRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AppContext appContext;
    private final IFileService fileService;
    private final IEmailService emailService;
    private final ExportUserDataService exportService;


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("user [id = %s] not found", userId)));
    }

    @Override
    public User getCurrentUser() {
        return getUserById(appContext.getCurrentUser().getId());
    }


    @Override
    public PageDTO<UserLineDTO> getPaginatedUsersByRole(Pageable pageable, String search, RoleEnum roleEnum) {
        search = search != null && !search.isBlank() ? search.toLowerCase() : "";
        Page<UserLineDTO> users = userProjectionRepository.getUserLines(
                pageable,
                appContext.getCurrentUser().getCabinet().getId(),
                roleEnum,
                search
        );
        users.map(userLineDTO -> userLineDTO.setPhotoUrl(fileService.getFullFilePath(userLineDTO.getPhotoUrl())));
        return new PageDTO<>(users);
    }


    @Override
    public User addUser(UserDTO userDTO, MultipartFile imageFile, RoleEnum role) {
        User user = userMapper.mapToUserEntity(userDTO, role);
        user = userRepository.save(user);

        user.setPhotoUrl(uploadUserAvatar(user.getId(), imageFile));
        String newPassword = PasswordUtil.generateRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        emailService.sendNewPassword(user.getEmail(), newPassword, true);

        return user;
    }

    @CacheEvict(value = "users", key = "#userId")
    @Override
    public User updateUser(Long userId, UserDTO userDTO, MultipartFile imageFile) {
        var existingUser = getUserById(userId);
        existingUser.setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setEmail(userDTO.getEmail())
                .setPhone(userDTO.getPhone())
                .setGender("M".equals(userDTO.getGender()) ? GenderEnum.M : GenderEnum.F);

        existingUser = userRepository.save(existingUser);

        if (userDTO.isAvatarUpdated()) {
            fileService.deleteFile(existingUser.getPhotoUrl());
            existingUser.setPhotoUrl(uploadUserAvatar(existingUser.getId(), imageFile));
            userRepository.save(existingUser);
        }
        return existingUser;
    }

    @CacheEvict(value = "users", key = "#userId")
    @Override
    public void deleteUser(Long userId) {
        var user = getUserById(userId);
        fileService.deleteFile(user.getPhotoUrl());
        userRepository.delete(user);
    }

    @Override
    public void sendUsersExportByEmail(String search, RoleEnum role) {
        search = search != null && !search.isBlank() ? search.toLowerCase() : "";
        exportService.sendExportAttachment(
                userProjectionRepository.getUserLines(
                        Pageable.unpaged(),
                        appContext.getCurrentUser().getCabinet().getId(),
                        role,
                        search
                ).toList()
        );
    }

    /*
    PRIVATES METHODS
     */

    private String uploadUserAvatar(Long userId, MultipartFile imageFile) {
        String path = String.format("users/%d/avatar/", userId);
        String fileName = "avatar.".concat(fileService.getFileExtension(imageFile));
        fileService.uploadFile(imageFile, path, fileName);
        return path.concat(fileName);
    }
}

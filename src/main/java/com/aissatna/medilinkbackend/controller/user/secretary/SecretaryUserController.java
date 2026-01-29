package com.aissatna.medilinkbackend.controller.user.secretary;

import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.dto.user.UserLineDTO;
import com.aissatna.medilinkbackend.dto.user.nurse.UserDTO;
import com.aissatna.medilinkbackend.mapper.UserMapper;
import com.aissatna.medilinkbackend.model.enums.RoleEnum;
import com.aissatna.medilinkbackend.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/secretaries")
public class SecretaryUserController {
    private final IUserService userService;
    private final UserMapper userMapper;

    @PreAuthorize("hasAnyRole('ROLE_DIRECTOR')")
    @GetMapping("/table")
    public ResponseEntity<PageDTO<UserLineDTO>> getCabinetSecretariesTable(Pageable pageable, @RequestParam(required = false) String search) {
        return ResponseEntity.ok(userService.getPaginatedUsersByRole(pageable, search, RoleEnum.SECRETARY));
    }

    @PreAuthorize("hasAnyRole('ROLE_DIRECTOR')")
    @PostMapping(path = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserLineDTO> addNewSecretary(@RequestPart("secretaryDTO") UserDTO userDTO,
                                                   @RequestPart("image") MultipartFile imageFile) {
        UserLineDTO userLineDTO = userMapper.mapToUserLineDTO(userService.addUser(userDTO, imageFile,RoleEnum.SECRETARY));
        return new ResponseEntity<>(userLineDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_DIRECTOR')")
    @PutMapping("/update/{secretaryId}")
    public ResponseEntity<UserLineDTO> updateSecretary(@PathVariable Long secretaryId,
                                                   @RequestPart("secretaryDTO") UserDTO userDTO,
                                                   @RequestPart(value = "image",required = false) MultipartFile imageFile) {
        UserLineDTO userLineDTO = userMapper.mapToUserLineDTO(userService.updateUser(secretaryId, userDTO, imageFile));
        return ResponseEntity.ok(userLineDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_DIRECTOR')")
    @DeleteMapping("/delete/{secretaryId}")
    public ResponseEntity<Void> deleteSecretary(@PathVariable Long secretaryId) {
        userService.deleteUser(secretaryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PreAuthorize("hasAnyRole('ROLE_DIRECTOR')")
    @PostMapping("/export")
    public ResponseEntity<Void> sendSecretariesExportByEmail(@RequestParam(required = false) String search) {
        userService.sendUsersExportByEmail(search,RoleEnum.SECRETARY );
        return ResponseEntity.ok().build();
    }

}

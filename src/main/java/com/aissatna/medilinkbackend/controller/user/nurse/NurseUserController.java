package com.aissatna.medilinkbackend.controller.user.nurse;

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
@RequestMapping("/nurses")
public class NurseUserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @GetMapping("/table")
    public ResponseEntity<PageDTO<UserLineDTO>> getCabinetNursesTable(Pageable pageable, @RequestParam(required = false) String search) {
        return ResponseEntity.ok(userService.getPaginatedUsersByRole(pageable, search, RoleEnum.NURSE));
    }

    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @PostMapping(path = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UserLineDTO> addNewNurse(@RequestPart("nurseDTO") UserDTO userDTO,
                                                   @RequestPart("image") MultipartFile imageFile) {
        UserLineDTO userLineDTO = userMapper.mapToUserLineDTO(userService.addUser(userDTO, imageFile,RoleEnum.NURSE));
        return new ResponseEntity<>(userLineDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @DeleteMapping("/delete/{nurseId}")
    public ResponseEntity<Void> deleteNurse(@PathVariable Long nurseId) {
        userService.deleteUser(nurseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @PutMapping("/update/{nurseId}")
    public ResponseEntity<UserLineDTO> updateNurse(@PathVariable Long nurseId,
                                                   @RequestPart("nurseDTO") UserDTO userDTO,
                                                   @RequestPart(value = "image",required = false) MultipartFile imageFile) {
        UserLineDTO userLineDTO = userMapper.mapToUserLineDTO(userService.updateUser(nurseId, userDTO, imageFile));
        return ResponseEntity.ok(userLineDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_SECRETARY')")
    @PostMapping("/export")
    public ResponseEntity<Void> sendPatientsExportByEmail(@RequestParam(required = false) String search) {
        userService.sendUsersExportByEmail(search,RoleEnum.NURSE );
        return ResponseEntity.ok().build();
    }
}

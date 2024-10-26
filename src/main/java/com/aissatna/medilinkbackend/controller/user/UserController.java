package com.aissatna.medilinkbackend.controller.user;

import com.aissatna.medilinkbackend.dto.user.CurrentUserDTO;
import com.aissatna.medilinkbackend.mapper.UserMapper;
import com.aissatna.medilinkbackend.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @GetMapping("/current")
    public ResponseEntity<CurrentUserDTO> getCurrentUser(){
        CurrentUserDTO currentUserDTO = userMapper.mapToDTO(userService.getCurrentUser());
        return ResponseEntity.ok(currentUserDTO);
    }

}

package com.aissatna.medilinkbackend.service.user;


import com.aissatna.medilinkbackend.configuration.app.AppContext;
import com.aissatna.medilinkbackend.exception.ResourceNotFoundException;
import com.aissatna.medilinkbackend.model.User;
import com.aissatna.medilinkbackend.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final AppContext appContext;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("user [id = %s] not found", userId)));
    }

    @Override
    public User getCurrentUser() {
        return getUserById(appContext.getCurrentUser().getId());
    }
}

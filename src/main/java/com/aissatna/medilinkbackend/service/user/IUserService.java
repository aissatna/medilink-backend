package com.aissatna.medilinkbackend.service.user;

import com.aissatna.medilinkbackend.model.User;

public interface IUserService {
    User getUserById(Long userId);
    User getCurrentUser();

}

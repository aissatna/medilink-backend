package com.aissatna.medilinkbackend.configuration.app;

import com.aissatna.medilinkbackend.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppContext {
    private User currentUser;
}

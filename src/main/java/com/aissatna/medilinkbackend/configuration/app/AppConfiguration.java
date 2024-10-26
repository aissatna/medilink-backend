package com.aissatna.medilinkbackend.configuration.app;


import com.aissatna.medilinkbackend.model.User;
import com.aissatna.medilinkbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final UserRepository userRepository;
    private final AppContext appContext;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User connectedUser = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format("user [username = %s] not found",username)));
            appContext.setCurrentUser(connectedUser);
            return connectedUser;
        };
    }

}
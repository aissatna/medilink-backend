package com.aissatna.medilinkbackend.configuration.app;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class LazyBeanConfiguration {
    @Bean
    public AppContext appContext(){
        return new AppContext();
    }

}
package tech.gustavomedina.cryptography.config;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.password}")
    private String password;

    @Bean
    public AES256TextEncryptor aes256TextEncryptor(){

        var encryptor = new AES256TextEncryptor();

        encryptor.setPassword(password);

        return encryptor;
    }

}

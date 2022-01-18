package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.erudio.config.FileStorageConfig;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({ FileStorageConfig.class })
@ComponentScan // diz que deve scanear os pacotes e encontrar arquivos de configuracao como o WebConfig
public class Startup {


    // comment just to commit something
    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);

        // p criar uma nova senha usar desse jeito
        //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
        //String result = bCryptPasswordEncoder.encode("admin123");
        //System.out.println("My Hash: " + result);
        
    }
}

package br.com.firebase.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "firebase.credentials")
public class FirebaseProperties {

    /**
     * Caminho do JSON no classpath (ex: firebase/credentials.json)
     */
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

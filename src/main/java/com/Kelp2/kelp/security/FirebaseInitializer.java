package com.Kelp2.kelp.security;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * service class used to initialize the connection between the server and firebase when the server starts up
 */
@Service
@Slf4j
public class FirebaseInitializer {

    /**
     * runs immediately after the service is initialized. tries to call initializeFirebaseApp
     */
    @PostConstruct
    public void onStart() {
        log.info("Initializing Firebase Application");
        try {
            this.initializeFirebaseApp();
        } catch (IOException e) {
            log.error("Initializing Firebase App {}", e);
        }
    }

    /**
     * attempts to set the connection with Firebase that will be used to authenticate the clients' JWTs. Does so using
     * the credentials in firebase-service-credentials.json
     * @throws IOException - could be thrown if google credentials cannot read the credentials file as a stream
     */
    private void initializeFirebaseApp() throws IOException {

        FileInputStream refreshToken =
                new FileInputStream("src/main/resources/firebase-service-credentials.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setDatabaseUrl("https://kelp2-56f64-default-rtdb.firebaseio.com/%22")
                        .build();

        FirebaseApp.initializeApp(options);


    }

    /**
     * returns an InputStream reading the given resource
     * @param resource The resource to be read
     * @return the stream reading "resource"
     */
    protected InputStream returnResourceAsStream(String resource) {
        return FirebaseInitializer.class.getResourceAsStream(resource);
    }

}

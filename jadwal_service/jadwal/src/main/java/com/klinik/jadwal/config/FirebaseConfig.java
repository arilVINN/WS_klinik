package com.klinik.jadwal.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${app.firebase.config-path}")
    private Resource firebaseConfig;

    @PostConstruct
    public void initializeFirebase() {
        if (firebaseConfig == null || !firebaseConfig.exists()) {
            System.err.println("Firebase configuration file not found at the configured path.");
            return;
        }

        try (InputStream serviceAccount = firebaseConfig.getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase App initialized successfully.");
            }
        } catch (IOException e) {
            System.err.println("Failed to read Firebase service account credentials: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error initializing Firebase App: " + e.getMessage());
        }
    }

    @Bean
    public Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}

package com.klinik.jadwal;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FirestoreConfig {

    @Value("${app.firebase.config-path:classpath:serviceAccountKey.json}")
    private Resource credentialsResource;

    @Value("${app.firebase.database-id:(default)}")
    private String databaseId;

    @Bean
    public Firestore firestore() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsResource.getInputStream());

        FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setCredentials(credentials)
                .setDatabaseId(databaseId)
                // Project ID will be automatically loaded from the serviceAccountKey.json
                .setProjectId(((com.google.auth.oauth2.ServiceAccountCredentials) credentials).getProjectId())
                .build();

        return firestoreOptions.getService();
    }
}
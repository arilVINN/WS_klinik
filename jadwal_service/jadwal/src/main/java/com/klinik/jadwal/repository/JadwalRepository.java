package com.klinik.jadwal.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.klinik.jadwal.model.JadwalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class JadwalRepository {

    @Autowired
    private Firestore firestore;

    private static final String COLLECTION_NAME = "jadwal";

    public void save(JadwalModel jadwal) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
        jadwal.setId_jadwal(docRef.getId()); // simpan ID acak tersebut ke objek jadwal
        docRef.set(jadwal);

    }

    public List<JadwalModel> findAll() throws ExecutionException, InterruptedException {
        List<JadwalModel> list = new ArrayList<>();
        CollectionReference collection = firestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> query = collection.get();
        QuerySnapshot querySnapshot = query.get();
        for (QueryDocumentSnapshot doc : querySnapshot.getDocuments()) {
            list.add(doc.toObject(JadwalModel.class));
        }
        return list;
    }

    public JadwalModel findById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(JadwalModel.class);
        }
        return null;
    }
}
package com.example.citizen.service;

import com.example.citizen.model.Incident;
import com.example.citizen.repository.UserRepository;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FirebaseMessagingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Deprecated
    public void sendNotification(int fromId, Incident incident) throws FirebaseMessagingException {
        List<String> registrationTokens = userRepository.findTokens(fromId);
        String longitude = String.valueOf(incident.getLongitude());
        String latitude = String.valueOf(incident.getLatitude());
        String event = incident.getEventDescription();
        MulticastMessage message = MulticastMessage.builder()
                .putData("longitude", longitude)
                .putData("latitude", latitude)
                .putData("event", event)
                .addAllTokens(registrationTokens)
                .build();
        BatchResponse response = firebaseMessaging.sendMulticast(message);
        if (response.getFailureCount() > 0) {
            List<SendResponse> responses = response.getResponses();
            List<String> failedTokens = new ArrayList<>();
            for (int i = 0; i < responses.size(); i++)
                if (!responses.get(i).isSuccessful())
                    failedTokens.add(registrationTokens.get(i));
            System.out.println("Токен, которые выдали ошибку: " + failedTokens);
        }
    }
}

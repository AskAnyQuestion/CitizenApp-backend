package com.example.citizen.service;

import com.example.citizen.model.Incident;
import com.example.citizen.model.User;
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
    public void sendNotification(int fromId, int notificationId, Incident incident) throws FirebaseMessagingException {
        List<String> registrationTokens = userRepository.findTokens(fromId);
        registrationTokens.add("fCHaV58hRU6RjR20j1N1rK:APA91bEDuG43XXfYB2uFZ" +
                "dsv0DxdeDZCXvk7m6idKoPfg7a-qy4Ea6EZPsZXEbmprH8MXZK4X5Gyr_iL" +
                "jGKYmBBYG9XiwJJZFA27exO1W3mGr9TzGsFJxGWe2oVebzIoY1h81yUC2XSX");
        String longitude = String.valueOf(incident.getLongitude());
        String latitude = String.valueOf(incident.getLatitude());
        String event = incident.getEventDescription();

        List<String> failedTokens = new ArrayList<>();
        for (String token : registrationTokens) {
            User user = userRepository.getUserByToken(token);
            String userId = String.valueOf(user.getIdUser());
            String notiId = String.valueOf(notificationId);
            MulticastMessage message = MulticastMessage.builder()
                    .putData("longitude", longitude)
                    .putData("latitude", latitude)
                    .putData("event", event)
                    .putData("userId", userId)
                    .putData("notificationId", notiId)
                    .addToken(token)
                    .build();
            BatchResponse response = firebaseMessaging.sendMulticast(message);
            if (response.getFailureCount() > 0) {
                List<SendResponse> responses = response.getResponses();
                for (int i = 0; i < responses.size(); i++)
                    if (!responses.get(i).isSuccessful())
                        failedTokens.add(registrationTokens.get(i));
            }
        }
        if (failedTokens.size() > 0)
            System.out.println("Токены, которые выдали ошибку: " + failedTokens);
    }
}

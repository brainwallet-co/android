package com.brainwallet.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber


class BrainwalletMessagingService : FirebaseMessagingService() {

    override fun onCreate() {
        super.onCreate()

        Timber.d("timber: BrainwalletMessagingService.onCreate")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Timber.d("timber: onMessageReceived data=${message.data}")
        Timber.d("timber: onMessageReceived notification=${message.notification?.title}, ${message.notification?.body}")

        if (NotificationHandler.handleMessageReceived(this, message)) {
            return
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Timber.d("timber: onNewToken $token")
    }
}
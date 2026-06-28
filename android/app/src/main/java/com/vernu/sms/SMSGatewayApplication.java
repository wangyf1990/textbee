package com.vernu.sms;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import androidx.work.Configuration;
import androidx.work.WorkManager;

public class SMSGatewayApplication extends Application implements Configuration.Provider {
    private static final String TAG = "SMSGatewayApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        ensureFirebaseInitialized(this);
    }

    public static void ensureFirebaseInitialized(Context context) {
        try {
            if (!FirebaseApp.getApps(context).isEmpty()) {
                return;
            }

            FirebaseApp app = FirebaseApp.initializeApp(context);
            if (app != null) {
                return;
            }

            FirebaseOptions options = FirebaseOptions.fromResource(context);
            if (options != null) {
                FirebaseApp.initializeApp(context, options);
            }
        } catch (Exception e) {
            Log.e(TAG, "Firebase initialization failed", e);
        }
    }
    
    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build();
    }
} 

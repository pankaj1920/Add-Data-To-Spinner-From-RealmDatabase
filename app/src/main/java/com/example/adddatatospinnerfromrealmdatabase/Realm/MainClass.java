package com.example.adddatatospinnerfromrealmdatabase.Realm;

import android.app.Application;

import io.realm.Realm;

public class MainClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        //add android:name=".Realm.MainClass" inside <application> in Manifest file
    }
}

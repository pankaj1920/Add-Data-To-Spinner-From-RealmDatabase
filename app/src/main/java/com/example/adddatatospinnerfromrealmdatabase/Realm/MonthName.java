package com.example.adddatatospinnerfromrealmdatabase.Realm;

import io.realm.RealmObject;

public class MonthName extends RealmObject {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

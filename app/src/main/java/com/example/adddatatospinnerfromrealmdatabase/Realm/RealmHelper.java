package com.example.adddatatospinnerfromrealmdatabase.Realm;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    //create constructor
    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //Write
    public void save(final MonthName monthName) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MonthName name = realm.copyToRealm(monthName);
            }
        });

    }

    //Retrive and Read Data into database
    public ArrayList<String> retrive() {
        ArrayList<String> nameOfMonth = new ArrayList<>();
        RealmResults<MonthName> month = realm.where(MonthName.class).findAll();

        for (MonthName i : month) {
            nameOfMonth.add(i.getName());
        }
        return nameOfMonth;
    }

}

package com.example.adddatatospinnerfromrealmdatabase;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.adddatatospinnerfromrealmdatabase.Realm.MonthName;
import com.example.adddatatospinnerfromrealmdatabase.Realm.RealmHelper;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    ArrayList<String> month_name;
    ArrayAdapter adapter;
    Spinner spinner;
    EditText edit_text;
    Button save_btn;
    FloatingActionButton floating_btn;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        edit_text = (EditText) findViewById(R.id.edit_text);
        save_btn = (Button) findViewById(R.id.save_btn);
        spinner = (Spinner) findViewById(R.id.spinner);
        floating_btn = (FloatingActionButton) findViewById(R.id.floating_btn);
        toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      toolbar.setTitle("Add Month Name");


        //to retrive data from RealmHelper Class create object of RealmHelper Class
        RealmHelper helper = new RealmHelper(realm);
        month_name = helper.retrive();

        // this ArrayAdapter will set previously added data to spinner
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, month_name);
        spinner.setAdapter(adapter);

        //Set onClickListener to floatingButton
        floating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Calling DisplayDialog method
                displayDialog();
            }
        });
    }

    public void displayDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Save To REalm");
        dialog.setContentView(R.layout.activity_dialog);

        edit_text = dialog.findViewById(R.id.edit_text);
        save_btn = dialog.findViewById(R.id.save_btn);

        //setting on Click Listner to save Button of dialog box
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_text.length()>0) {
                    //Creating object of MonthName Class
                    MonthName name1 = new MonthName();
                    name1.setName(edit_text.getText().toString());

                    //save
                    RealmHelper helper = new RealmHelper(realm);
                    helper.save(name1);
                    edit_text.setText("");

                    //retrive
                    month_name = helper.retrive();
                    adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1, month_name);
                    spinner.setAdapter(adapter);
                }else {
                    Toast.makeText(MainActivity.this, "Enter Month Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}

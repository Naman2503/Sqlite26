package com.example.naman.sqlite26;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity26 extends AppCompatActivity {


    EditText user, pass;
    Button login, not_reg;
    DatabaseHandler db;
    Cursor cursor;
    CheckBox cbShowPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main26);


        user = (EditText) findViewById(R.id.eduser);
        pass = (EditText) findViewById(R.id.edpass);
        cbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);

        // add onCheckedListener on checkbox
        // when user clicks on this checkbox, this is the handler.
        cbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        login = (Button) findViewById(R.id.login);
        not_reg = (Button) findViewById(R.id.not_reg);

        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                db = new DatabaseHandler(MainActivity26.this, null, null, 2);
                String username = user.getText().toString();
                String password = pass.getText().toString();

                String StoredPassword = db.getregister(username);
                if (password.equals(StoredPassword)) {

                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Username/Password Incorrect", Toast.LENGTH_LONG).show();
                    user.setText("");
                    pass.setText("");
                }


            }
        });

        not_reg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });


    }

    /* @Override
                    public boolean onCreateOptionsMenu(Menu menu) {
MenuInflater inflater = getMenuInflater();
inflater.inflate(R.menu.action_settings, menu);
            return super.onCreateOptionsMenu(menu);
           //
     }
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_settings:
                // search action
                Intent i = new Intent(MainActivity26.this, RegisterActivity.class);
                startActivity(i);
                return true;
        }
        return false;

    }
}

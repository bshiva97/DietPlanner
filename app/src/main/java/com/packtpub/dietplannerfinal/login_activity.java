package com.packtpub.dietplannerfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class login_activity extends AppCompatActivity {
    public EditText uid;public EditText upass;
    public Button btn1;
    public TextView register;
    public DatabaseHelper sqlDbhelper;
    private SessionRecord ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        uid=(EditText)findViewById(R.id.logid);
        upass=(EditText)findViewById(R.id.logpass);
        btn1=(Button)findViewById(R.id.login);
        register=(TextView)findViewById(R.id.reg);
        ss=new SessionRecord(this);
        sqlDbhelper=new DatabaseHelper(this);
        Log.d("checkk", "onCreate: " + ss.isLoggedIn());
        if(ss.isLoggedIn())
        {
            Intent x=new Intent(login_activity.this,front.class);
            startActivity(x);

        }
        btn1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String id=uid.getText().toString();
                        String pass=upass.getText().toString();
                        int count= sqlDbhelper.checkEntry(id,pass);
                        if(count!=0) {
                            ss.setLogggedIn(true);
                            Toast.makeText(getBaseContext(), "you got login", Toast.LENGTH_SHORT).show();
                            //SessionRecord ss=new SessionRecord(getApplicationContext());
                            //   Bundle b=new Bundle();
                            // b.putString("name",id);
                            //HomeFragment fragobj = new HomeFragment();
                            //fragobj.setArguments(b);
                            Intent x=new Intent(login_activity.this,front.class);
                            startActivity(x);
                            //startActivity(x);
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(), "invalid user", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }


    public void register(View view)
    {

        Intent i=new Intent(login_activity.this,signup_activity.class);
        startActivity(i);
    }


}

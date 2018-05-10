package com.packtpub.dietplannerfinal;
        import android.app.Activity;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class signup_activity extends Activity {
    public Button btn;
    public EditText pid;
    public EditText pfname;
    public EditText plname;
    public EditText ppass;
    public DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        pid=(EditText)findViewById(R.id.uid);
        pfname=(EditText)findViewById(R.id.fname);
        plname=(EditText)findViewById(R.id.lname);
        ppass=(EditText)findViewById(R.id.password);
        btn=(Button)findViewById(R.id.register);
        db=new DatabaseHelper(this);
        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String id=pid.getText().toString();
                        String firstname=pfname.getText().toString();
                        String lastname=plname.getText().toString();
                        String pass=ppass.getText().toString();
                        UserEntry u=new UserEntry(id,firstname,lastname,pass);
                        long rowId=db.addUserEntry(u);
                        if(rowId!=-1)
                        {
                            Toast.makeText(getBaseContext(),"you got registered redirecting you to login page,",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(signup_activity.this,login_activity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getBaseContext(),"falied in registering",Toast.LENGTH_SHORT).show();
                        }


                    }
                }
        );



    }

}

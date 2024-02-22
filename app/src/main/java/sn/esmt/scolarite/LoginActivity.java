package sn.esmt.scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText userfirstname;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Recupération des valeurs saisies
        username =(EditText) findViewById(R.id.username);
        userfirstname =(EditText) findViewById(R.id.userfirstname);
        //recuperation de l'id du bouton login
        button =(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String pwd = userfirstname.getText().toString();
                if(email.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Veuillez renseignez tous les champs",Toast.LENGTH_LONG).show();
                }
                else{
                    if(email.equals("esmt@esmt.sn") && pwd.equals("123")){
                        Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"E-mail ou mot de passe incorrect",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }
}
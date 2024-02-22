package sn.esmt.scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sn.esmt.scolarite.http.Api;
import sn.esmt.scolarite.http.EtudiantResponse;

public class InscriptionActivity extends AppCompatActivity {
   private EditText username;
    private EditText userfirstname;
    private EditText useradr;
    private EditText usertel;
    private Button boutton;
    private EditText usermat;
    private EditText userfrais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        username = (EditText) findViewById(R.id.username);
        userfirstname = (EditText) findViewById(R.id.userfirstname);
        useradr = (EditText) findViewById(R.id.useradr);
        usertel = (EditText) findViewById(R.id.usertel);
        usermat = (EditText) findViewById(R.id.usermat);
        userfrais = (EditText) findViewById(R.id.userfrais);
        boutton = (Button) findViewById(R.id.boutton);

        boutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8082/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api = retrofit.create(Api.class);
                //Creation d'un objet etudiant a ajouter dans la base
                EtudiantResponse e = new EtudiantResponse();
                e.setMat(usermat.getText().toString());
                e.setNom(username.getText().toString());
                e.setPrenom(userfirstname.getText().toString());
                e.setAdr(useradr.getText().toString());
                e.setTel(Integer.parseInt(usertel.getText().toString()));
                e.setFrais(Double.parseDouble(userfrais.getText().toString()));

                //Appel de l'Api
                Call<EtudiantResponse> callSave = api.saveEtudiant(e);

                callSave.enqueue(new Callback<EtudiantResponse>() {
                    @Override
                    public void onResponse(Call<EtudiantResponse> call, Response<EtudiantResponse> response) {
                        Toast.makeText(InscriptionActivity.this,"Etudiant inscrit avec success",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<EtudiantResponse> call, Throwable t) {
                        Toast.makeText(InscriptionActivity.this,"Impossible d'acceder au server",Toast.LENGTH_LONG).show();

                    }
                });
                        ;


            }
        });
    }
}
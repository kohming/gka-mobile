package com.gka.gkamobile.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gka.gkamobile.MainActivity;
import com.gka.gkamobile.R;
import com.gka.gkamobile.dao.UserDao;
import com.gka.gkamobile.db.GkaDatabase;
import com.gka.gkamobile.models.Token;
import com.gka.gkamobile.models.User;
import com.gka.gkamobile.service.ApiClient;
import com.gka.gkamobile.service.ApiService;
import com.gka.gkamobile.ui.RegisterActivity;
import com.gka.gkamobile.utils.TokenResponse;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView linkSignup;
    private ApiService apiService;
    private EditText inputPassword;
    private EditText inputEmail;
    private TokenResponse tokenResponse;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.btn_login);
        linkSignup = (TextView) findViewById(R.id.link_signup);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputEmail = (EditText) findViewById(R.id.input_email);
        logo = (ImageView) findViewById(R.id.ivLogo);
        tokenResponse =  new TokenResponse();
        tokenResponse.getStringToken();

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Token token = tokenResponse.getToken();
                Toast.makeText(LoginActivity.this," response " + token.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Token token = tokenResponse.getToken();
                apiService = ApiClient.getClient().create(ApiService.class);
                Call<ResponseBody> result = apiService.postLogin("Bearer " + token.getContent().getAccess_token(), inputEmail.getText().toString(),inputPassword.getText().toString());
                result.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                           // Log.d("login",response.body().string());
                            String result = response.body().string();
                            try {
                                if(response.code()==200){
                                    Gson gson = new Gson();
                                    JSONObject jsonResponse = new JSONObject(result);
                                    String jsonUserIdentifier = jsonResponse.getString("content");
                                    User user = gson.fromJson(jsonUserIdentifier,User.class);
                                    GkaDatabase database = GkaDatabase.getInstance(getApplicationContext());
                                    database.userDao().insertUser(user);
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this," terjadi kesalahan data ",Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException err){
                                Log.d("Error", err.toString());
                                Toast.makeText(LoginActivity.this," error "+ err.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                           Log.d("login",result);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"failure "+t.getMessage().toString(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);;
                startActivity(intent);
            }
        });

    }
}

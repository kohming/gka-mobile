package com.gka.gkamobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import com.gka.gkamobile.R;
import com.gka.gkamobile.models.Token;
import com.gka.gkamobile.service.ApiClient;
import com.gka.gkamobile.service.ApiService;
import com.gka.gkamobile.utils.TokenResponse;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullname;
    private EditText email;
    private EditText password;
    private EditText repassword;
    private EditText username;
    private EditText phone;
    private Button submit;
    private AppCompatCheckBox checkBox;
    private TokenResponse tokenResponse;
    private ApiService apiService;
    private String stringRespons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(getString(R.string.registration));

        fullname = findViewById(R.id.etFullName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword1);
        repassword = findViewById(R.id.etPassword2);
        username = findViewById(R.id.etUserName);
        phone = findViewById(R.id.etPhoneNumber);
        checkBox = findViewById(R.id.checkBox);
        submit = findViewById(R.id.btn_login);

        tokenResponse =  new TokenResponse();
        tokenResponse.getStringToken();

        fullname.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    repassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    repassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isValid()){
                    final Token token = tokenResponse.getToken();
                    apiService = ApiClient.getClient().create(ApiService.class);
                    Call<ResponseBody> result = apiService.postRegister("Bearer " + token.getContent().getAccess_token(),email.getText().toString(),password.getText().toString(),fullname.getText().toString(),username.getText().toString(),phone.getText().toString());
                    result.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {

                                Gson gson = new Gson();
                                Token responsObj = gson.fromJson(response.body().string(),Token.class);
                                Log.d("respons ",response.body().string());
                                Log.d("code ", responsObj.getCode());

                                if(response.isSuccessful()){

                                   if(responsObj.getCode().equalsIgnoreCase("200")){
                                        finish();
                                    }else if(responsObj.getCode().equalsIgnoreCase("400")){
                                        Toast.makeText(getApplicationContext(),"Email sudah terdaftar, forgot password untuk mengaktifkannya",Toast.LENGTH_LONG).show();
                                    }
                                }
                           }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"Error " + e.getMessage(),Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            stringRespons = t.getMessage();
                            Toast.makeText(RegisterActivity.this,"failure "+t.getMessage().toString(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    private boolean isValid() {
        if(!password.getText().toString().contentEquals(repassword.getText().toString())){
            repassword.setError("Password doesn't match");
            return false;
        }
        return true;
    }
}

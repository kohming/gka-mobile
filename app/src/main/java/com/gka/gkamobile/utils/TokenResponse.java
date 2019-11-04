package com.gka.gkamobile.utils;

import android.util.Log;
import com.gka.gkamobile.models.Token;
import com.gka.gkamobile.service.ApiClient;
import com.gka.gkamobile.service.ApiService;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenResponse {

    private ApiService apiService;
    public String stringToken;

    public TokenResponse() {
    }

    public void getStringToken(){
        apiService = ApiClient.getClient().create(ApiService.class);
        Call<ResponseBody> result = apiService.postToken(ApiClient.appId,ApiClient.appKey);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    stringToken = response.body().string();
                    Log.d("respons ",response.body().string());
                   // Toast.makeText(LoginActivity.this," response version "+response.body().string(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    stringToken = e.getMessage();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                stringToken = t.getMessage();
                t.printStackTrace();
            }
        });
    }


    public Token getToken(){
        Token tokenResult = new Token();
        apiService = ApiClient.getClient().create(ApiService.class);
        Call<ResponseBody> result = apiService.postToken(ApiClient.appId,ApiClient.appKey);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    stringToken = response.body().string();


                    Log.d("respons ",response.body().string());
                    // Toast.makeText(LoginActivity.this," response version "+response.body().string(),Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    stringToken = e.getMessage();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                stringToken = t.getMessage();
                t.printStackTrace();
            }
        });


        Gson gson = new Gson();
        tokenResult = gson.fromJson(stringToken,Token.class);

        return tokenResult;
    }
}

package com.gka.gkamobile.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Response;

public interface TokenListener {
    public void success(Response<ResponseBody> response);
    public void failed(String message);
}

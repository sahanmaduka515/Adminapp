package com.example.adminapp.fcmhelper;

import android.os.AsyncTask;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class FCMClient extends AsyncTask<String,String,String> {


    public void sendFCMMessage(String token,String Title,String Mes){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n   \"to\":\""+token+"\",\r\n   \"notification\":{\r\n      \"sound\":\"default\",\r\n      \"body\":\""+Mes+"\",\r\n      \"title\":\""+Title+"\",\r\n      \"content_available\":true,\r\n      \"priority\":\"high\"\r\n   }\r\n}");
        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .addHeader("authorization", "key=AAAA6wGu6Ts:APA91bG9ME0PLNHP0hSB0YC0AKn2FOCTwsbJcOeTh5RexaLOcVjq_yDLwS5ja1Q5j_4rwdXk_rdsvjgOX_UDFLGPxYPwdoniPaHMo11PXYg8Wx6LDdusZydO2wSJ8o9PNropXrashaBm")
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected String doInBackground(String... strings) {
        sendFCMMessage(strings[0],strings[1],strings[2]);
        return null;
    }
}

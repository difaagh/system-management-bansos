package com.msb.app.management.system.bansos.helper;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author difaagh
 */
public class Security {

    private OkHttpClient client;

    public Security() {
        this.client = new OkHttpClient();
    }

    public void login(String username, String password)
            throws IOException {
        System.out.println(password);
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("client_id", "ocbnisp")
                .add("grant_type", "password")
                .build();
        // TODO add env for keycloak var
        HttpUrl keycloakUrl = HttpUrl.parse("http://127.0.0.1:8080/auth/realms/master/protocol/openid-connect/token");
        Request request = new Request.Builder()
                .url(keycloakUrl)
                .post(formBody)
                .build();

        Call call = this.client.newCall(request);
        Response response = call.execute();

    }
}

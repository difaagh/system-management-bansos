package com.msb.app.management.system.bansos.helper;

import io.github.cdimascio.dotenv.Dotenv;
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

    public int login(String username, String password)
            throws IOException {

        Dotenv dotenv = Dotenv.load();
        String keycloakPath = dotenv.get("KEYCLOAK_URL");
        String keycloakRealms = dotenv.get("KEYCLOAK_REALM");
        String keycloakClientId = dotenv.get("KEYCLOAK_CLIENT_ID");
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("client_id", keycloakClientId)
                .add("grant_type", "password")
                .build();

        HttpUrl keycloakUrl = HttpUrl.parse(keycloakPath + "/auth/realms/" + keycloakRealms + "/protocol/openid-connect/token");
        System.out.println(keycloakUrl);
        Request request = new Request.Builder()
                .url(keycloakUrl)
                .post(formBody)
                .build();

        Call call = this.client.newCall(request);
        try {
            Response response = call.execute();
            return response.code();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

    }
}

package com.msb.app.management.system.bansos.helper;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*
 * The MIT License
 *
 * Copyright 2021 difaagh.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 *
 * @author difaagh
 */
public class KeycloakService {
        private OkHttpClient client;

    public KeycloakService() {
        this.client = new OkHttpClient();
    }

    public int login(String username, char[] password)
            throws IOException {

        Dotenv dotenv = Dotenv.load();
        if("development".equals(dotenv.get("JAVA_ENV"))) return 200;
        String keycloakPath = dotenv.get("KEYCLOAK_URL");
        String keycloakRealms = dotenv.get("KEYCLOAK_REALM");
        String keycloakClientId = dotenv.get("KEYCLOAK_CLIENT_ID");
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", String.valueOf(password))
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

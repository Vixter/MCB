package ru.winfected.mcb.network;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by winfe on 27.12.2015.
 */
public class Config {

    private final String BASE_URL = "https://gateway.marvel.com/v1/public/";
    private final String privateKey;
    private final String publicKey;
    private Retrofit retrofit;


    public Config(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OkHttpClient client = retrofit.client();
        client.interceptors().add(new AuthInterceptor(publicKey,privateKey));
    }



    public Retrofit getRetrofit() {
        return retrofit;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    private class AuthInterceptor implements Interceptor {
        private final String TIMESTAMP_KEY = "ts";
        private final String HASH_KEY = "hash";
        private final String APIKEY_KEY = "apikey";

        private final String publicKey;
        private final String privateKey;

        AuthInterceptor(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            String timestamp = String.valueOf(currentTimeMillis());
            String hash = generateHash(timestamp, publicKey, privateKey);
            Request request = chain.request();
            HttpUrl url = request.httpUrl()
                    .newBuilder()
                    .addQueryParameter(TIMESTAMP_KEY, timestamp)
                    .addQueryParameter(APIKEY_KEY, publicKey)
                    .addQueryParameter(HASH_KEY, hash)
                    .build();
            request = request.newBuilder().url(url).build();

            Response response = chain.proceed(request);
            return response;
        }

        String generateHash(String timestamp, String publicKey, String privateKey){
            byte[] md5Bytes = null;
            try {
                String value = timestamp + privateKey + publicKey;
                MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
                md5Bytes = md5Encoder.digest(value.getBytes());

                StringBuilder md5 = new StringBuilder();
                for (int i = 0; i < md5Bytes.length; ++i)
                    md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));

            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if(md5Bytes == null) new NullPointerException("Error of forming MD5 hash");
            return md5Bytes.toString();
        }

        long currentTimeMillis() {
            return System.currentTimeMillis();
        }
    }

}

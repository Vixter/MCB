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

    private final String BASE_URL = "http://gateway.marvel.com:80/v1/public/";
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
                md5Encoder.update(value.getBytes());
                md5Bytes = md5Encoder.digest();
            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            if(md5Bytes == null) throw new NullPointerException("MD5 hash exception");
            return new String(hexEncode(md5Bytes));
        }

        public String hexEncode(byte[] bytes)
        {
            final char[] HEXCHARS = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            char[] result = new char[bytes.length*2];
            int b;
            for (int i = 0, j = 0; i < bytes.length; i++)
            {
                b = bytes[i] & 0xff;
                result[j++] = HEXCHARS[b >> 4];
                result[j++] = HEXCHARS[b & 0xf];
            }
            return new String(result);
        }

        long currentTimeMillis() {
            return System.currentTimeMillis();
        }
    }

}

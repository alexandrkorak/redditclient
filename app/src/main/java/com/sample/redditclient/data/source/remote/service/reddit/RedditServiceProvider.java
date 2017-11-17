package com.sample.redditclient.data.source.remote.service.reddit;

import com.google.gson.GsonBuilder;
import com.sample.redditclient.data.Entry;
import com.sample.redditclient.data.source.remote.service.reddit.deserializer.EntriyArrayJsonDeserializer;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by osarapul on 16.11.2017.
 */

public class RedditServiceProvider {
    private static final String AUTH_ENDPOINT = "https://www.reddit.com";
    private static final String SERVICE_ENDPOINT = "https://oauth.reddit.com";
    private static final Service service = createService();
    private static final String TOP_PATH = "/top";
    private static final String API_PATH = "/api";
    private static final String V1_PATH = "/v1";
    private static final String ACCESS_TOKEN_PATH = "/access_token";
    private static final String LIMIT_PARAM = "limit";

    public static final String INSTALLED_CLIENT_GRANT_TYPE = "https://oauth.reddit.com/grants/installed_client";

    private static Service createService() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Entry[].class, new EntriyArrayJsonDeserializer());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public static Service getService() {
        return service;
    }

    public interface Service {

        @Headers("Authorization: Basic WkdoUGNHMjdIT21jVGc6")
        @POST(AUTH_ENDPOINT + API_PATH + V1_PATH + ACCESS_TOKEN_PATH)
        @FormUrlEncoded
        Single<AccessToken> authorize(@Field("grant_type") String body, @Field("device_id") String deviceId);

        @GET(TOP_PATH)
        Single<Entry[]> getTop(@Header("Authorization") String token, @Query(LIMIT_PARAM) int limit);
    }
}

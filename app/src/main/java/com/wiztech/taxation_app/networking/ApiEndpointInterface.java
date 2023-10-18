package com.wiztech.taxation_app.networking;

import com.wiztech.taxation_app.networking.responses.GetConsumerAccountsResponse;
import com.wiztech.taxation_app.networking.responses.GetCurrentBillAndHistoryResponse;
import com.wiztech.taxation_app.networking.responses.GetCurrentBillResponse;
import com.wiztech.taxation_app.networking.responses.LoginResponse;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface ApiEndpointInterface {
    // Request method and URL specified in the annotation

    @GET("consumer-account/getByUserId/{userId}")
    Call<GetConsumerAccountsResponse> getConsumerAccounts(@Header("Authorization") String token, @Path("userId") Integer userId);

    @GET("bill/getCurrentBill/{consumer_account_id}/{attribute_group_id}")
    Call<GetCurrentBillResponse> getCurrentBill(@Header("Authorization") String token,
                                                @Path("consumer_account_id") int consumer_account_id,
                                                @Path("attribute_group_id") int attribute_group_id );

    @GET("bill/getCurrentBillAndHistory/{consumer_account_id}/{attribute_group_id}")
    Call<GetCurrentBillAndHistoryResponse> getCurrentBillAndHistory(@Header("Authorization") String token,
                                                                    @Path("consumer_account_id") int consumer_account_id,
                                                                    @Path("attribute_group_id") int attribute_group_id );

    @POST("authenticate")
    Call<LoginResponse> login(@Body HashMap<String, String> user);

    @Streaming
    @GET("bill/getPdfBill/{billId}/true")
    Call<ResponseBody> getBillPage(@Header("Authorization") String token, @Path("billId") int billId);
//
//    @POST("users/new")
//    Call<User> createUser(@Body User user);
}
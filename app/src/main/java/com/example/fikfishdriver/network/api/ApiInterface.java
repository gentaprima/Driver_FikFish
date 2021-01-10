package com.example.fikfishdriver.network.api;

import com.example.fikfishdriver.model.MessageOnly;
import com.example.fikfishdriver.model.history.HistoryResponse;
import com.example.fikfishdriver.model.home.CountResponse;
import com.example.fikfishdriver.model.order_transaction.TransactionResponse;
import com.example.fikfishdriver.model.shippinng.ShippingResponse;
import com.example.fikfishdriver.model.users.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("courier/loginCourier")
    Call<LoginResponse> loginUser(@Field("username") String username,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("shipping/getDataShipping")
    Call<ShippingResponse> getDataShipping(@Field("id_courier") String id_courier,
                                           @Field("latitude") String latitude,
                                           @Field("longitude")String longitude,
                                           @Field("date")String date);

    @FormUrlEncoded
    @POST("order/getDataOrderByOrderId")
    Call<TransactionResponse> getDetailDataOrder(@Field("id_order")String id_order);

    @FormUrlEncoded
    @POST("shipping/getDataShippingDelay")
    Call<ShippingResponse> getDataShippingDelay(@Field("id_courier") String id_courier,
                                                @Field("latitude") String latitude,
                                                @Field("longitude")String longitude);

    @FormUrlEncoded
    @POST("shipping/getCountPengiriman")
    Call<CountResponse> getCountShipping(@Field("id_courier") String id_courier,
                                         @Field("date") String date);

    @FormUrlEncoded
    @POST("shipping/delayShipping")
    Call<MessageOnly> addDelayShipping(@Field("id_shipping") String id_shipping);

    @FormUrlEncoded
    @POST("shipping/changeDelay")
    Call<MessageOnly> changeStatusDelay(@Field("id_shipping")String id_shipping);

    @FormUrlEncoded()
    @POST("shipping/orderAccepted")
    Call<MessageOnly> acceptedShipping(@Field("id_shipping")String id_shipping,
                                       @Field("receiver")String receiver,
                                       @Field("order_id")String order_id);

    @FormUrlEncoded
    @POST("history/getDataHistory")
    Call<HistoryResponse> getDataHistory(@Field("id_courier")String id_courier);

    @FormUrlEncoded
    @POST("history/getDataHistoryByDate")
    Call<HistoryResponse> getDataHistoryByDate(@Field("id_courier")String id_courier,
                                               @Field("date")String date);

    @FormUrlEncoded
    @POST("courier/updateProfile")
    Call<MessageOnly> changeProfile(@Field("username")String username,
                                    @Field("full_name")String full_name,
                                    @Field("no_hp")String no_hp);

    @FormUrlEncoded
    @POST("courier/changeEmail")
    Call<MessageOnly> changeEmail(@Field("username")String username,
                                  @Field("oldEmail")String oldEmail,
                                  @Field("newEmail")String newEmail,
                                  @Field("confirmEmail")String confirmEmail);

    @FormUrlEncoded
    @POST("courier/changePassword")
    Call<MessageOnly> changePassword(@Field("username")String username,
                                     @Field("old_password")String oldPassword,
                                     @Field("new_password")String newPassword,
                                     @Field("confirm_password")String confirmPassword);

}

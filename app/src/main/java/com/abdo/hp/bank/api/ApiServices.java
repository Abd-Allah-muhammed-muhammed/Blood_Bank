package com.abdo.hp.bank.api;

import com.abdo.hp.bank.model.donation.donation_creat.DonationRequestCreate;
import com.abdo.hp.bank.model.donation.donation_details.DonationDetails;
import com.abdo.hp.bank.model.donation.donation_request.DonationRequest;
import com.abdo.hp.bank.model.general.connect_us.ConnectUs;
import com.abdo.hp.bank.model.general.list_of_cities.ListOfCities;
import com.abdo.hp.bank.model.general.listof_governorate.ListOfGovernorate;
import com.abdo.hp.bank.model.general.logs.Logs;
import com.abdo.hp.bank.model.general.report.Report;
import com.abdo.hp.bank.model.general.settings.Settings;
import com.abdo.hp.bank.model.list_blood_typ.ListBloodType;
import com.abdo.hp.bank.model.notification.notificationToken.NotificationToken;
import com.abdo.hp.bank.model.notification.notification_count.NotificationCount;
import com.abdo.hp.bank.model.notification.notification_list.NotificationList;
import com.abdo.hp.bank.model.notification.notificationsettings.NotificationSettings;
import com.abdo.hp.bank.model.notification.removenotifications.RemoveNotifications;
import com.abdo.hp.bank.model.posts.postes.Postes;
import com.abdo.hp.bank.model.posts.postes_details.PostesDetails;
import com.abdo.hp.bank.model.posts.postes_toggle_favorite.PostesToggleFavorite;
import com.abdo.hp.bank.model.posts.posts_favorete_list.PostesFavouriteList;
import com.abdo.hp.bank.model.user.login.LoginClient;
import com.abdo.hp.bank.model.user.newpass.NewPass;
import com.abdo.hp.bank.model.user.passwordrest.RestPassword;
import com.abdo.hp.bank.model.user.profile.Profile;
import com.abdo.hp.bank.model.user.register.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {




    @POST("register")
    @FormUrlEncoded
    Call<Register> newRegister(@Field("name") String name
            , @Field("email") String email
            , @Field("birth_date") String birth_date
            , @Field("city_id") long city_id
            , @Field("phone") String phone
            , @Field("donation_last_date") String donation_last_dat
            , @Field("password") String password
            , @Field("password_confirmation") String password_confirmation
            , @Field("blood_type") String blood_type);


    @POST("login")
    @FormUrlEncoded
    Call<LoginClient> login(@Field("phone") String phone
            , @Field("password") String password);

    @POST("reset-password")
    @FormUrlEncoded
    Call<RestPassword> resetPassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPass> newPassword(@Field("password") String password
            , @Field("password_confirmation") String passwordConfirmation
            , @Field("pin_code") String pinCode
    , @Field("phone") String phone);


    @POST("profile")
    @FormUrlEncoded
    Call<Profile> profile(@Field("name") String name
            , @Field("email") String email
            , @Field("birth_date") String birth_date
            , @Field("city_id") long city_id
            , @Field("phone") String phone
            , @Field("donation_last_date") String donation_last_dat
            , @Field("password") String password
            , @Field("password_confirmation") String password_confirmation
            , @Field("blood_type") String blood_type
            , @Field("api_token") String api_token);

    // notification methods api


    @POST("register-token")
    @FormUrlEncoded
    Call<NotificationToken> registerNotification(@Field("token") String token,
                                                 @Field("api_token") String apiToken, @Field("platform") String platform);

    @POST("remove-token")
    @FormUrlEncoded
    Call<RemoveNotifications> removeNotification(@Field("token") String token,
                                                 @Field("api_token") String api_token, @Field("platform") String platform);


    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSettings> settingsNotification(@Field("api_token") String api_token,
                                                    @Field("cities[]") List<String> governorates ,
                                                    @Field("blood_types[]") List<String> blood_types);

    @GET("notifications")
    Call<NotificationList> getNotificationsList(@Query("api_token") String api_token);


    @GET("notifications-count")
    Call<NotificationCount> getNotificationsCount(@Query("api_token") String api_token);


    // donation method

    @POST("donation-request/create")
    @FormUrlEncoded
    Call<DonationRequestCreate> donationRequestCreat(@Field("api_token") String api_token
            , @Field("patient_name") String patient_name
            , @Field("patient_age") String patient_age
            , @Field("blood_type") String blood_type
            , @Field("bags_num") String bags_num
            , @Field("hospital_name") String hospital_name
            , @Field("hospital_address") String hospital_address
            , @Field("city_id") long city_id
            , @Field("phone") String phone
            , @Field("notes") String notes
            , @Field("latitude") double latitude
            , @Field("longitude") double longitude);


    @GET("donation-requests")
    Call<DonationRequest> getDonationRequests(@Query("api_token") String api_token);

    @GET("donation-request")
    Call<DonationDetails> getDonationRequestDetails(@Query("api_token") String api_token
            , @Query("donation_id") long donation_id);


    // posts methods api


    @GET("posts")
    Call<Postes> getPosts(@Query("api_token") String api_token
            , @Query("page") int page);


    @GET("post")
    Call<PostesDetails> PostDetails(@Query("api_token") String api_token
            , @Query("post_id") long post_id);

    @GET("my-favourites")
    Call<PostesFavouriteList> myFavourites(@Query("api_token") String api_token);

    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<PostesToggleFavorite> PostToggleFavourite(@Field("post_id") long post_id
            , @Field("api_token") String api_token);

    // general methods api


    @GET("governorates")
    Call<ListOfGovernorate> governorates();

    @GET("cities")
    Call<ListOfCities> cities(@Query("governorate_id") long governorate_id);


    @GET("settings")
    Call<Settings> settings(@Query("api_token") String api_token);

    @POST("contact")
    @FormUrlEncoded
    Call<ConnectUs> contactUs(@Field("api_token") String api_token
            , @Field("title") String title, @Field("message") String message);


    @POST("report")
    @FormUrlEncoded
    Call<Report> report(@Field("api_token") String api_token
            , @Field("message") String message);


    //i have no idea

    @GET("blood-types")
    Call<ListBloodType> getBloodList();





}

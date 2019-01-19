package com.nsb.visions.varun.mynsb.Auth;


import android.content.Context;
import android.content.SharedPreferences;

import eu.amirs.JSON;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.nsb.visions.varun.mynsb.HTTP.HTTP;
import com.nsb.visions.varun.mynsb.User.User;

import java.io.IOException;

/**
 */


// Auth class for attaining user details or logging in
public class Auth {

    private Context context;
    private HTTP httpClient;



    /* constructor is just a constructor lmao
            @params;
                Context context

     */
    public Auth(Context context) {
        this.context = context;
        this.httpClient = new HTTP(this.context);
    }



    /* Auth function for quick authentication, simply just uses the API
            @params;
                String studentID
                String password

            @throws;
               Exception: Just an error that could be returned from the request
    */
    public User auth(String studentID, String password) throws Exception {
        // Set up the request
        Request login = new Request.Builder()
                .url(HTTP.API_URL + "/user/auth")
                .method("POST", RequestBody.create(null, new byte[0]))
                .addHeader("Authorization", Credentials.basic(studentID, password))
                .build();
        // Retrieve the response
        Response loginResp = httpClient.performRequest(login);



        // Get the set cookie header from the request
        if (loginResp.code() != 200) {
            // Throw the error meaning that the request was unsuccessful
            throw new Exception("User details are invalid");
        }
        // Return the details for the currently logged in user
        return this.getUserDetails();
    }



    /* getUserDetails is a function to get user details for the currently logged in user
            @params;
                nil

            @throws;
                Exception: Just an error that could be returned from the request
    */
    public User getUserDetails() throws Exception {
        // Get user data and return it
        Request getUserDetails = new Request.Builder()
                .url(HTTP.API_URL + "/user/getdetails")
                .build();
        Response userDataResp = httpClient.performRequest(getUserDetails);
        // Read Body
        String body = userDataResp.body().string();
        User user;
        // Begin parsing json
        JSON userData = new JSON(body);
        try {
            // Determine if the request was successful
            if (userDataResp.code() != 200) {
                throw new RuntimeException("Something went horrible wrong");
            }

            // Get the user data and parse it
            Integer StudentID = userData.key("Message").key("Body").index(0).key("StudentID").intValue();
            String Fname = userData.key("Message").key("Body").index(0).key("Fname").stringValue();
            String Lname = userData.key("Message").key("Body").index(0).key("Lname").stringValue();
            Integer Year = userData.key("Message").key("Body").index(0).key("Year").intValue();
            // Push into user container
            user = new User(StudentID, Fname, Lname, Year);
        } catch (Exception e) {
            throw new RuntimeException("Something went horribly wrong");
        }

        return user;
    }
}

package com.project.integratedservices.retofit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiInterceptor implements Interceptor {


    Context context;
    private boolean skip=false;
    private String TAG = "ApiInterceptor " ;

    public ApiInterceptor(Context context){
        this.context = context;
    }



    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
         .header("Content-Type", "application/json")
         .header("Accept", "application/json")
                .build();

        // try the request
        Response response = chain.proceed(request);
//        ResponseBody responseBody = response.body();
        ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);
//        responseBodyCopy.string();


//        if (response.code() == 401) {
//
//            Intent in =  new Intent(context,WelcomeActivity.class);
//            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(in);
//
//      /*      // get a new token (I use a synchronous Retrofit call)
//            System.out.println("@@@Token expired");
//
//            apiClient = XparyRemoteClient.getXparyRetrofitApi();
//            sharedPref = SharedPref.getInstance(XparyApplication.getInstance());
//            RefreshTokenRequestModel requestModel = new RefreshTokenRequestModel();
//            requestModel.setRefresh_token(sharedPref.getData(REFRESH_TOKEN));
//            requestModel.setIs_Mobile("1");
//
//            if(!skip)
//            {
//            RefreshTokenResponse refreshTokenResponse = apiClient.getRefreshToken(requestModel).execute().body();
//
//            if(refreshTokenResponse.getStatus()) {
//                sharedPref.saveData(ACCESS_TOKEN, refreshTokenResponse.getData().getToken());
//                sharedPref.saveData(REFRESH_TOKEN, refreshTokenResponse.getData().getRefreshToken());
//            }
//
////                Request requestNew = request.newBuilder()
////                        .header("Authorization","Bearer "+ sharedPref.getData(ACCESS_TOKEN))
////                        .header("Content-Type", "application/json")
////                        .header("Accept", "application/json")
////                        .method(request.method(), request.body())
////                        .build();
////                return chain.proceed(requestNew);
//            }*/
//
       /* } else*/ if (response.code() == 200) {
            try {
                String rawJson = responseBodyCopy.string();
                Object object = new JSONTokener(rawJson).nextValue();
                String jsonLog = object instanceof JSONObject
                        ? ((JSONObject) object).toString(4)
                        : object instanceof JSONArray?((JSONArray) object).toString(4):((String) object);
                Log.d("jsonLog -> ", response.request().url().toString());

              longLog(jsonLog);

                System.out.println("@@@Interceptor -> " + jsonLog);
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            if(response.message().equals("Please login."))
//            {
//                Intent in =  new Intent(context,WelcomeActivity.class);
//                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(in);
//            }
//
//            if(request.url().toString().equals("https://dev.uiplonline.com/xpary/backend/api/get-token"))
//            {
//                JSONObject object = null;
//                try {
//                     object = new JSONObject(response.body().source().buffer().clone().readString(Charset.forName("UTF-8")));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if(object.optString("message").equals("Your credentials are incorrect. Please try again"))
//                {
//                    skip = true;
////                    Toast.makeText(XparyApplication.getInstance(), "got it", Toast.LENGTH_SHORT).show();
//                    Intent in =  new Intent(context,WelcomeActivity.class);
//                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(in);
//
//
//                }
//            }
        }
        System.out.println("@@@Interceptor -> " + response.code());
        // otherwise just pass the original response on
        return response;

    }

    public static void longLog(String str) {
        if (str.length() > 4000) {
            Log.d("", str.substring(0, 4000));
            longLog(str.substring(4000));
        } else
            Log.d("", str);
    }
}

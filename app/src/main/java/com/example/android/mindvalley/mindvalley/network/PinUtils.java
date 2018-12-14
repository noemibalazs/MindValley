package com.example.android.mindvalley.mindvalley.network;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.mindvalley.mindvalley.model.Pin;
import com.example.android.mindvalley.mindvalley.model.ProfileImage;
import com.example.android.mindvalley.mindvalley.model.Urls;
import com.example.android.mindvalley.mindvalley.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PinUtils {

    private static final String TAG = PinUtils.class.getSimpleName();

    public PinUtils(){}

    private static final String ID = "id";
    private static final String URLS = "urls";
    private static final String USER = "user";
    private static final String NAME = "name";
    private static final String PROFILE_IMAGE = "profile_image";
    private static final String SMALL = "small";
    private static final String MEDIUM = "medium";
    private static final String LARGE = "large";
    private static final String RAW = "raw";
    private static final String FULL = "full";
    private static final String REGULAR = "regular";
    private static final String THUMB = "thumb";


    public static List<Pin> fetchPin(String urlString){

        URL url = createUrl(urlString);
        String response = null;
        try {
            response = makeHttpRequest(url);
        }catch (IOException e){
            Log.v(TAG, "Error making HTTP request " + e);
            e.printStackTrace();
        }

        List<Pin> pins = extractDataFromJson(response);
        return pins;

    }

    private static URL createUrl(String string){
        URL url = null;
        try {
            url = new URL(string);
        }catch (MalformedURLException e){
            Log.v(TAG, "Error building url " + e);
            e.printStackTrace();
        }
        return  url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        if (url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromTheStream(inputStream);
            }else {
                Log.v(TAG, "Error getting response code " + urlConnection.getResponseCode());
            }
        }catch (IOException e){
            e.printStackTrace();
            Log.v(TAG, "Error fetching pins");
        }finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }if(inputStream!=null){
                inputStream.close();
            }

        }

        return jsonResponse;

    }

    private static String readFromTheStream(InputStream inputStream) throws IOException{
        StringBuilder builder = new StringBuilder();

        if (inputStream != null){
            InputStreamReader readerInput = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(readerInput);
            String line = reader.readLine();
            while (line!=null){
                builder.append(line);
                line = reader.readLine();
            }
        }

        return builder.toString();

    }

    private static List<Pin> extractDataFromJson (String json){
        if (TextUtils.isEmpty(json)){
            return  null;
        }

        List<Pin> pinList = new ArrayList<>();

        try{
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++){

                JSONObject object = jsonArray.getJSONObject(i);

                String pinId = object.getString(ID);

                JSONObject userObject = object.getJSONObject(USER);
                String userName = userObject.getString(NAME);

                JSONObject profileObject = userObject.getJSONObject(PROFILE_IMAGE);

                String smallProfile = profileObject.getString(SMALL);
                String mediumProfile = profileObject.getString(MEDIUM);
                String largeProfile = profileObject.getString(LARGE);

                ProfileImage profileImage = new ProfileImage(smallProfile);

                User user = new User(userName, profileImage);

                JSONObject urlsObject = object.getJSONObject(URLS);

                String raw = urlsObject.getString(RAW);
                String full = urlsObject.getString(FULL);
                String regular = urlsObject.getString(REGULAR);
                String small = urlsObject.getString(SMALL);
                String thumb = urlsObject.getString(THUMB);

                Urls url = new Urls(regular);

                Pin pin = new Pin(pinId, user, url);
                pinList.add(pin);

            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return pinList;

    }

}

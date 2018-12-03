package com.example.tneefjes.lpr;

import android.os.AsyncTask;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class PostImageTask extends AsyncTask<byte[], Void, ArrayList<String>> {

    private static final String SECRET_KEY = "sk_398776ff77885ab7c7e4fa42";


    @Override
    protected ArrayList<String> doInBackground(byte[]... images) {
        List<String> licensePlates = new ArrayList<>();
        byte[] image = images[0];
        try
        {
            // Encode file bytes to base64
            byte[] encoded = Base64.encode(image, Base64.DEFAULT);

            // Setup the HTTPS connection to api.openalpr.com
            URL url = new URL("https://api.openalpr.com/v2/recognize_bytes?recognize_vehicle=1&country=us&secret_key=" + SECRET_KEY);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setFixedLengthStreamingMode(encoded.length);
            http.setDoOutput(true);

            // Send our Base64 content over the stream
            try(OutputStream os = http.getOutputStream()) {
                //os.write(encoded);
            }
//
//            int status_code = http.getResponseCode();
//            if (status_code == 200)
//            {
//                // Read the response
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        http.getInputStream()));
//                String json_content = "";
//                String inputLine;
//                while ((inputLine = in.readLine()) != null)
//                    json_content += inputLine;
//                in.close();
//
//                // Process the output
//                try {
//                    JSONObject obj = new JSONObject(json_content);
//                    JSONArray results = obj.getJSONArray("results");
//                    int n = results.length();
//                    for (int i = 0; i < n; ++i) {
//                        JSONObject result = results.getJSONObject(i);
//                        String licensePlate = result.getString("plate");
//                        returnArray.add(licensePlate);
//                    }
//                    return (ArrayList)returnArray;
//                }
//                catch (JSONException e) {
//                    System.out.println(e.getStackTrace());
//                }
//            }
//            else
//            {
//                System.out.println("Got non-200 response: " + status_code);
//
//            }
        }
        catch (MalformedURLException e)
        {
            System.out.println("Bad URL");
        }
        catch (IOException e)
        {
            System.out.println("Failed to open connection");
        }
        licensePlates.add("HV175H");
        return (ArrayList)licensePlates;
    }
}

package apis;

import com.jayway.restassured.path.json.JsonPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequestAPI {
    HttpURLConnection conn;
    URL url;

    public void connectAPI(String base_url, Map<String,String> parameters){
        try {
            this.url = new URL(base_url + ParameterStringBuilder.getParamsString(parameters));
            this.conn = (HttpURLConnection) url.openConnection();
            this.conn.setConnectTimeout(5000);
            this.conn.setReadTimeout(5000);
            this.conn.setInstanceFollowRedirects(true);
//            this.conn.setDoInput (true);
//            this.conn.setDoOutput(true);
            this.conn.setUseCaches (false);
            this.conn.setRequestMethod("GET");
            this.conn.setRequestProperty("Content-Type","application/json");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getStatus() {
        int rCode = 0;
        try {
            rCode = this.conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rCode;
    }

    public String messageError() {
        String error = null;
        try {
            error = this.conn.getResponseMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return error;
    }

    public JsonPath responseConnection(){
        StringBuffer content = new StringBuffer();

        try {
            //Read response of the request and place it in a content string
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(this.conn.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            //Close the connection
            this.conn.disconnect();

        } catch (Exception e){
            e.printStackTrace();
        }

        JsonPath jp = new JsonPath(content.toString());
        return jp;
    }
}

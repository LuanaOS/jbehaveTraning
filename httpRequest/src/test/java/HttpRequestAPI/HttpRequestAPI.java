package HttpRequestAPI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequestAPI {
    URL url;
    Map<String, String> parameters;

    public HttpRequestAPI(URL url, Map<String,String> parameters){
        this.url = url;
        this.parameters = parameters;
    }

    public String responseConnection(){
        HttpURLConnection conn;
        StringBuffer content = null;

        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type","application/json");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

        } catch (Exception e){
            e.printStackTrace();
        }

        return content.toString();
    }
}

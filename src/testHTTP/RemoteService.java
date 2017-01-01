package testHTTP;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class RemoteService {
  private static String processURL = "http://localhost:8080/SourceOffer/login.action?";

  public static void loginRemoteService(String adminname, String password) {
    String result = null;
    try {
      HttpClient httpclient = new DefaultHttpClient();
      String URL = processURL + "userName=" + adminname + "&password=" + password;
      HttpGet request = new HttpGet(URL);
      request.addHeader("Accept", "text/json");
      HttpResponse response = httpclient.execute(request);
      if (response.getStatusLine().getStatusCode() == 200) {
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, "UTF-8");

        if (json != null) {
          JSONObject jsonObject = new JSONObject(json);
          result = jsonObject.get("result").toString();
        }
        if (result == null) {
          System.out.println("resulte is unll");
        } else {
          System.out.println(result);
        }
      } else {
        System.out.println(response.getStatusLine().getStatusCode());
      }
    } catch (ClientProtocolException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
    loginRemoteService("lyx", "882776");
    loginRemoteService("lx", "882776");
    loginRemoteService("lyx", "882");
  }
}

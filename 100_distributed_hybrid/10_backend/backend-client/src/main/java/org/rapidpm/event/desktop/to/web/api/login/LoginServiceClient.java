package org.rapidpm.event.desktop.to.web.api.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.getProperty;

public class LoginServiceClient
    implements LoginService {

  public static final String PATH_LOGIN = "/login";

  private final String backendserviceIP   = getProperty("backendserviceIP", "127.0.0.1");
  private final String backendservicePORT = getProperty("backendservicePORT", "7000");

  private final Gson         gson   = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                                                       .create();
  private final OkHttpClient client = new OkHttpClient();


  @Override
  public List<SessionUser> authenticate(String username, String password) {

    Request request = new Request.Builder().url(
        "http://" + backendserviceIP + ":" + backendservicePORT + PATH_LOGIN + "/" + username + "/" + password)
                                           .build();

    try (Response response = client.newCall(request)
                                   .execute()) {
      final String resultString = response.body()
                                          .string();
      final List<SessionUser> sessionUsers = gson.fromJson(resultString, new TypeToken<List<SessionUser>>() { }.getType());
      return (sessionUsers.size() == 1)
             ? sessionUsers
             : new ArrayList<>();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //TODO NOT NICE
    return new ArrayList<>();
  }
}

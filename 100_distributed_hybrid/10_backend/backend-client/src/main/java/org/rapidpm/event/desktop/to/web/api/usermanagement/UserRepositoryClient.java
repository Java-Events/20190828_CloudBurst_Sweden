package org.rapidpm.event.desktop.to.web.api.usermanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import okhttp3.OkHttpClient;

import java.util.List;

import static java.lang.System.getProperty;

public class UserRepositoryClient implements UserRepository {

  private final String backendserviceIP   = getProperty("backendserviceIP", "127.0.0.1");
  private final String backendservicePORT = getProperty("backendservicePORT", "7000");

  private final Gson         gson   = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                                                       .create();
  private final OkHttpClient client = new OkHttpClient();



  @Override
  public List<User> loadUser(String username, String password) {
    return null;
  }

  @Override
  public void storeUser(User user) {

  }

  @Override
  public void deleteUser(User user) {

  }
}

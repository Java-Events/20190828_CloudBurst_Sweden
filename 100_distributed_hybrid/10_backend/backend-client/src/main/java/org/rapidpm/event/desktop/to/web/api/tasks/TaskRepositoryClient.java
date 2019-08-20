package org.rapidpm.event.desktop.to.web.api.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.rapidpm.dependencies.core.logger.HasLogger;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static java.lang.System.getProperty;

public class TaskRepositoryClient
    implements TaskRepository, HasLogger {

  public static final String PATH_LOGIN         = "login";
  public static final String PATH_PROJECTS      = "projects";
  public static final String PATH_PROJECT_COUNT = "project-count";
  public static final String PATH_LOAD_PROJECT  = "load-project";
  public static final String PATH_ELEMENT_COUNT = "element-count";
  public static final String PATH_TASK_FOR      = "task-for";
  public static final String PATH_TASK_STORE    = "task-store";
  public static final String PATH_TASK_DELETE    = "task-delete";

  public static final String PROJECT = "project";
  public static final String TASK_ID = "ID";


  private final String backendserviceIP   = getProperty("backendserviceIP", "127.0.0.1");
  private final String backendservicePORT = getProperty("backendservicePORT", "7000");

  private final Gson         gson   = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
                                                       .create();
  private final OkHttpClient client = new OkHttpClient();


  @Override
  public Task taskFor(Integer id, String project) {
    Request request = new Request.Builder().url(
        "http://" + backendserviceIP + ":" + backendservicePORT + "/"+ PATH_TASK_FOR + "/" + id + "/" + project)
                                           .build();

    try (Response response = client.newCall(request)
                                   .execute()) {
      final String resultString = response.body()
                                          .string();
      final Task task = gson.fromJson(resultString, new TypeToken<Task>() { }.getType());
      return task;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void store(Task task) {
    final String encoded = Base64.getEncoder()
                           .encodeToString(gson.toJson(task)
                                               .getBytes());

    Request request = new Request.Builder().url(
        "http://" + backendserviceIP + ":" + backendservicePORT + "/"+ PATH_TASK_STORE + "/" + encoded )
                                           .build();

    try (Response response = client.newCall(request)
                                   .execute()) {
      final String resultString = response.body()
                                          .string();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void remove(Task task) {
    final String encoded = Base64.getEncoder()
                                 .encodeToString(gson.toJson(task)
                                                     .getBytes());

    Request request = new Request.Builder().url(
        "http://" + backendserviceIP + ":" + backendservicePORT + "/"+ PATH_TASK_DELETE + "/" + encoded )
                                           .build();

    try (Response response = client.newCall(request)
                                   .execute()) {
      final String resultString = response.body()
                                          .string();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Task> loadProject(String project) {
    Request request = new Request.Builder().url(
        "http://" + backendserviceIP + ":" + backendservicePORT + "/"+ PATH_LOAD_PROJECT + "/" + project)
                                           .build();

    try (Response response = client.newCall(request)
                                   .execute()) {
      final String resultString = response.body()
                                          .string();
      final List<Task> tasks = gson.fromJson(resultString, new TypeToken<List<Task>>() { }.getType());
      return tasks;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public int elementCount() {
    Request request = new Request.Builder().url(
        "http://" + backendserviceIP + ":" + backendservicePORT + "/"+ PATH_ELEMENT_COUNT )
                                           .build();

    try (Response response = client.newCall(request)
                                   .execute()) {
      final String resultString = response.body()
                                          .string();
      final int count = gson.fromJson(resultString, new TypeToken<Integer>() { }.getType());
      return count;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return -1;
  }

  @Override
  public List<String> projects() {
    Request request = new Request.Builder().url(
        "http://" + backendserviceIP + ":" + backendservicePORT + "/"+ PATH_PROJECTS )
                                           .build();

    try (Response response = client.newCall(request)
                                   .execute()) {
      final String resultString = response.body()
                                          .string();
      final List<String> projects = gson.fromJson(resultString, new TypeToken<List<String>>() { }.getType());
      return projects;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public int projectCount() {
    Request request = new Request.Builder().url(
        "http://" + backendserviceIP + ":" + backendservicePORT + "/"+ PATH_PROJECT_COUNT )
                                           .build();

    try (Response response = client.newCall(request)
                                   .execute()) {
      final String resultString = response.body()
                                          .string();
      final int count = gson.fromJson(resultString, new TypeToken<Integer>() { }.getType());
      return count;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return -1;
  }
}

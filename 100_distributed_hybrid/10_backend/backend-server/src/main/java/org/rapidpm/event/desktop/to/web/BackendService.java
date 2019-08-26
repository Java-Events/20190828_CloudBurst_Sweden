package org.rapidpm.event.desktop.to.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.json.JavalinJson;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.rapidpm.event.desktop.to.web.api.login.LoginService;
import org.rapidpm.event.desktop.to.web.api.login.LoginServiceProvider;
import org.rapidpm.event.desktop.to.web.api.login.SessionUser;
import org.rapidpm.event.desktop.to.web.api.tasks.Task;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskRepository;
import org.rapidpm.event.desktop.to.web.api.tasks.TaskRepositoryProvider;

import java.util.Base64;
import java.util.List;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

public class BackendService {

  public static final String DEFAULT_PORT = "7000";
  private Javalin app;

  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";

  private static final LoginService LOGIN_SERVICE = new LoginServiceProvider().load()
                                                                              .ifAbsent(() -> {
                                                                                throw new RuntimeException(
                                                                                    "LoginService could not be loaded");
                                                                              })
                                                                              .get();

  private static final TaskRepository TASK_REPOSITORY = new TaskRepositoryProvider().load()
                                                                                    .ifAbsent(() -> {
                                                                                      throw new RuntimeException(
                                                                                          "TaskRepository could not be loaded");
                                                                                    })
                                                                                    .get();
  public static final  String         PROJECT         = "project";
  public static final  String         TASK_ID         = "ID";
  public static final  String         TASK            = "task";

  public static final String PATH_LOGIN         = "login";
  public static final String PATH_PROJECTS      = "projects";
  public static final String PATH_PROJECT_COUNT = "project-count";
  public static final String PATH_LOAD_PROJECT  = "load-project";
  public static final String PATH_ELEMENT_COUNT = "element-count";
  public static final String PATH_TASK_FOR      = "task-for";
  public static final String PATH_TASK_STORE    = "task-store";
  public static final String PATH_TASK_DELETE    = "task-delete";


  public static final String CLI_HOST = "host";
  public static final String CLI_PORT = "port";

  public static void main(String[] args) throws ParseException {

    final Options options = new Options();
    options.addOption(CLI_HOST, true, "host to use");
    options.addOption(CLI_PORT, true, "port to use");

    DefaultParser parser = new DefaultParser();
    CommandLine   cmd    = parser.parse(options, args);

    if (cmd.hasOption(CLI_HOST)) {
      setProperty(CLI_HOST, cmd.getOptionValue(CLI_HOST));
    } if (cmd.hasOption(CLI_PORT)) {
      setProperty(CLI_PORT, cmd.getOptionValue(CLI_PORT));
    }
    new BackendService().start(args);
  }

  public void stop() {
    if (app != null) app.stop();
  }

  public void start(String[] args) {

    DemoDataGenerator.generateData();

    Gson gson = new GsonBuilder().create();
    JavalinJson.setFromJsonMapper(gson::fromJson);
    JavalinJson.setToJsonMapper(gson::toJson);

    final Javalin javalin = Javalin.create(config -> {
      config.registerPlugin(new RouteOverviewPlugin("overview"));
//      config.registerPlugin(new MicrometerPlugin());
    });
    app = javalin.start(Integer.parseInt(getProperty(CLI_PORT, DEFAULT_PORT)));
    app.get("/", ctx -> ctx.result("Desktop to Web - Backend Service"));


    app.get(PATH_LOGIN + "/:" + USERNAME + "/:" + PASSWORD, ctx -> {
      final String username = ctx.pathParam(USERNAME);
      final String password = ctx.pathParam(PASSWORD);
//      final Optional<User> authenticate = LOGIN_SERVICE.authenticate(username, password);
      final List<SessionUser> authenticate = LOGIN_SERVICE.authenticate(username, password);
      ctx.json(authenticate);
    });

    app.get(PATH_PROJECTS, ctx -> ctx.json(TASK_REPOSITORY.projects()));

    app.get(PATH_PROJECT_COUNT, ctx -> ctx.json(TASK_REPOSITORY.projectCount()));

    app.get(PATH_LOAD_PROJECT + "/:" + PROJECT, ctx -> {
      final String projectName = ctx.pathParam(PROJECT);
      ctx.json(TASK_REPOSITORY.loadProject(projectName));
    });

    app.get(PATH_ELEMENT_COUNT, ctx -> ctx.json(TASK_REPOSITORY.elementCount()));

    app.get(PATH_TASK_FOR + "/:" + TASK_ID + "/:" + PROJECT, ctx -> {
      final String projectName = ctx.pathParam(PROJECT);
      final String id          = ctx.pathParam(TASK_ID);
      ctx.json(TASK_REPOSITORY.taskFor(Integer.valueOf(id), projectName));
    });

    app.get(PATH_TASK_STORE + "/:" + TASK, ctx -> {
      final String task = ctx.pathParam(TASK);
      final Task   t = gson.fromJson(new String(Base64.getDecoder()
                                                          .decode(task)), Task.class);
      TASK_REPOSITORY.store(t);
    });
    app.get(PATH_TASK_DELETE + "/:" + TASK, ctx -> {
      final String task = ctx.pathParam(TASK);
      final Task t = gson.fromJson(new String(Base64.getDecoder()
                                                    .decode(task)), Task.class);
      TASK_REPOSITORY.remove(t);
    });


  }
}


package com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project;


import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.MainLayout;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.tasks.Task;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.tasks.TaskRepository;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.tasks.TaskRepositoryProvider;
import com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.tasks.TaskState;
import org.rapidpm.dependencies.core.logger.HasLogger;

import java.util.*;
import java.util.stream.Stream;

import static com.vaadin.tutorial.flow.layout.applayout.vaadin.views.project.ProjectView.NAV;
import static java.util.stream.Collectors.toList;


@Route(value = NAV, layout = MainLayout.class)
public class ProjectView
    extends Composite<VerticalLayout>
    implements HasLogger {
  public static final String NAV = "project";

  private final TaskRepository repository = new TaskRepositoryProvider().load()
                                                                        .ifAbsent(() -> logger().warning(
                                                                            "TaskRepository could not be loaded"))
                                                                        .ifAbsent(() -> {
                                                                          throw new RuntimeException(
                                                                              "no TaskRepository Implementation");
                                                                        })
                                                                        .get();

  private final ComboBox<String> projects  = new ComboBox<>("Projects", repository.projects());
  private final Button           btnReload = new Button("reload");
  private final Button           btnClose  = new Button("reload");


  private final Grid<Task>   taskGrid = new Grid<>();
  private final List<String> states   = Stream.of(TaskState.values())
                                              .map(Enum::name)
                                              .collect(toList());

  private final List<Task> tasks = new ArrayList<>();

  public ProjectView() {
    getContent().setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.START);
    getContent().setSizeFull();

    projects.addValueChangeListener(e -> {
      tasks.clear();
      final String project = e.getValue();
      logger().info("selected project is " + project);
      if (project != null && !project.isEmpty()) {
        tasks.addAll(repository.loadProject(project));
        logger().info("number of loaded tasks from the repository " + tasks.size());
        taskGrid.setItems(tasks);
      }
    });

    taskGrid.setHeightFull();
    taskGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
    final Grid.Column<Task> colTaskNr = taskGrid.addColumn(Task::getId)
                                                .setHeader("TaskNr")
                                                .setAutoWidth(true);
//    final Grid.Column<Task> colPrjNr = taskGrid.addColumn(Task::getProject)
//                                               .setHeader("Prj Nr")
//                                               .setAutoWidth(true);
    final Grid.Column<Task> colState = taskGrid.addColumn(Task::getState)
                                               .setAutoWidth(true)
                                               .setHeader("State");
    final Grid.Column<Task> colSummary = taskGrid.addColumn(Task::getSummary)
                                                 .setAutoWidth(true)
                                                 .setHeader("Summary");
    final Grid.Column<Task> colDescription = taskGrid.addColumn(Task::getDescription)
                                                     .setAutoWidth(true)
                                                     .setHeader("Description");

    Binder<Task> binder = new Binder<>(Task.class);
    Editor<Task> editor = taskGrid.getEditor();
    editor.setBinder(binder);
    editor.setBuffered(true);

    TextField tfSummary = new TextField();
    binder.bind(tfSummary, Task::getSummary, Task::setSummary);
    colSummary.setEditorComponent(tfSummary);

    TextField tfDescription = new TextField();
    binder.bind(tfDescription, Task::getDescription, Task::setDescription);
    colDescription.setEditorComponent(tfDescription);

    Select<TaskState> selectState = new Select<>();
    selectState.setItems(Stream.of(TaskState.values()));
    binder.bind(selectState, Task::getState, Task::setState);
    colState.setEditorComponent(selectState);

    Collection<Button> editButtons = Collections.newSetFromMap(new WeakHashMap<>());

    Grid.Column<Task> editorColumn = taskGrid.addComponentColumn(customer -> {
      Button edit = new Button("Edit");
      edit.addClassName("edit");
      edit.addClickListener(e -> {
        editor.editItem(customer);
        selectState.focus();

      });
      edit.setEnabled(!editor.isOpen());
      editButtons.add(edit);
      return edit;
    });

    editor.addOpenListener(e -> editButtons.forEach(button -> button.setEnabled(!editor.isOpen())));
    editor.addCloseListener(e -> editButtons.forEach(button -> button.setEnabled(!editor.isOpen())));

    Button save = new Button("Save", e -> editor.save());
    save.addClassName("save");

    Button cancel = new Button("Cancel", e -> editor.cancel());
    cancel.addClassName("cancel");

    // Add a keypress listener that listens for an escape key up event.
    // Note! some browsers return key as Escape and some as Esc
    taskGrid.getElement()
            .addEventListener("keyup", event -> editor.cancel())
            .setFilter("event.key === 'Escape' || event.key === 'Esc'");

    Div buttons = new Div(save, cancel);
    editorColumn.setEditorComponent(buttons);

//    editor.addSaveListener(
//        event -> message.setText(event.getItem().getFirstName() + ", "
//                                 + event.getItem().getCountry() + ", "
//                                 + event.getItem().getState()));

    getContent().add(projects, taskGrid);
  }
}

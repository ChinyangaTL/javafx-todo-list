package com.github.chinyangatl.todolist;

import com.github.chinyangatl.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea detailsArea;
    @FXML
    private Label labelDeadline;

    public void initialize() {
        TodoItem item1 = new TodoItem("Quit Java", "Give up on this terrible language",
                LocalDate.of(2021, Month.DECEMBER, 23));
        TodoItem item2 = new TodoItem("Buy Album", "Does it look infected?",
                LocalDate.of(2021, Month.OCTOBER, 7));
        TodoItem item3 = new TodoItem("Dentist Appointment", "Floss vigourously before going there",
                LocalDate.of(2021, Month.SEPTEMBER, 13));
        TodoItem item4 = new TodoItem("Pick up Jane", "Arrival is at 11 March",
                LocalDate.of(2021, Month.MARCH, 11));

        todoItems = new ArrayList<>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
                if(t1 != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    detailsArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    labelDeadline.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = (TodoItem) todoListView.getSelectionModel().getSelectedItem();
        detailsArea.setText(item.getDetails());
        labelDeadline.setText(item.getDeadline().toString());


    }
}

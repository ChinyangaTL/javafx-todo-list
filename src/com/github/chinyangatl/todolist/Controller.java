package com.github.chinyangatl.todolist;

import com.github.chinyangatl.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea detailsArea;

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

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = (TodoItem) todoListView.getSelectionModel().getSelectedItem();

        StringBuilder stringBuilder = new StringBuilder(item.getDetails());
        stringBuilder.append("\n\n\n\n");
        stringBuilder.append("Due: ");
        stringBuilder.append(item.getDeadline().toString());
        detailsArea.setText(stringBuilder.toString());

    }
}

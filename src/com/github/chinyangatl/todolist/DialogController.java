package com.github.chinyangatl.todolist;

import com.github.chinyangatl.todolist.datamodel.TodoData;
import com.github.chinyangatl.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextArea details;
    @FXML
    private TextField shortDesc;
    @FXML
    private DatePicker deadline;

    public TodoItem processResults() {
        String shortDescription = shortDesc.getText().trim();
        String detailsArea = details.getText().trim();
        LocalDate deadlineVal = deadline.getValue();

        TodoItem newItem = new TodoItem(shortDescription, detailsArea, deadlineVal);
        TodoData.getInstance().addTodoItem(newItem);

        return newItem;
    }

}

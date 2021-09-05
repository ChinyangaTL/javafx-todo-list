package com.github.chinyangatl.todolist.datamodel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {
    private static TodoData instance = new TodoData();
    private static String filename = "TodoListItems.txt";

    private List<TodoItem> todoItems;
    private DateTimeFormatter formatter;

    public static TodoData getInstance() {
        return instance;
    }

    private TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

//    public void setTodoItems(List<TodoItem> todoItems) {
//        this.todoItems = todoItems;
//    }

    public void loadTodoItems() throws IOException {
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader bufferedReader = Files.newBufferedReader(path);

        String input;

        try {
            while((input = bufferedReader.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                TodoItem todoItem = new TodoItem(shortDescription, details, date);
                todoItems.add(todoItem);
            }
        } finally {
            {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
            }
        }
    }

    public void storeTodoItems() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(path);

        try {
            Iterator<TodoItem> itemIterator = todoItems.iterator();
            while (itemIterator.hasNext()) {
                TodoItem item = itemIterator.next();
                bufferedWriter.write(String.format("%s\t%s\t%s\t",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));

                bufferedWriter.newLine();
            }
        } finally {
            {
                if(bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
        }
    }

    public void addTodoItem(TodoItem item) {
        todoItems.add(item);
    }

}

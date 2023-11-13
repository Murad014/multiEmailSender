package com.emailsender.emailsender.utils;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class TableUtils {

    public static <T> void deleteSelectedRows(TableView<T> tableView) {
        ObservableList<T> selectedItems = tableView.getSelectionModel().getSelectedItems();
        tableView.getItems().removeAll(selectedItems);
    }

}

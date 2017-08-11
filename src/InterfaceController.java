import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import static java.nio.file.Files.readAllLines;
import static jdk.nashorn.api.scripting.ScriptUtils.convert;

/**
 * Created by Tomov on 3.8.2017 г..
 *///localhost:3306
public class InterfaceController {
    private Database database;
    @FXML
    private TableView tableViewTable;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginLabel;
    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField databaseField;
    @FXML
    private TextField addressField;
    @FXML
    private Label loginErrorLabel;
    @FXML
    private TableView contentsViewTable;
    @FXML
    private ComboBox tableComboBox;
    @FXML
    private VBox startMenuBox;
    @FXML
    private Label databaseLabel;
    @FXML
    private CheckBox saveInfoCheckBox;
    @FXML
    public void initialize(){
        loadInfo();
    }

    public void changeTables(ActionEvent event) throws Exception { // divide into methods
        Table selectedTable = database.getTable((String) tableComboBox.getSelectionModel().getSelectedItem());
        loadColumnTableView(selectedTable);
        loadContentsTableView(selectedTable);
    }
    private void loadColumnTableView(Table selectedTable) {
        ObservableList<Column> tableColumns = FXCollections.observableList(selectedTable.getColumns());
        tableViewTable.setItems(tableColumns);
    }
    private void loadContentsTableView(Table selectedTable) {
        contentsViewTable.getColumns().clear();
        contentsViewTable.getItems().clear();

        for(String columnName : selectedTable.getColumnNames()) {
            TableColumn<Row,String> tableColumn = new TableColumn(columnName);
            tableColumn.setStyle("-fx-font: NORMAL 12 Tahoma;");

            tableColumn.setCellValueFactory(param -> {
                int index = param.getTableView().getColumns().indexOf(param.getTableColumn());
                List<Cell> cells = param.getValue().getCells();
                return new SimpleStringProperty(cells.size() > index ? cells.get(index).toString() : null);
            });

            contentsViewTable.getColumns().add(tableColumn);
        }

        List<Row> rowList = selectedTable.getRows();
        contentsViewTable.getItems().addAll(rowList);
    }
    public void loginAuth(ActionEvent event) {
        try {
            database = new Database(databaseField.getText(), DatabaseConnection.getInstance(userField.getText(), passwordField.getText(),addressField.getText()));
            switchScreen();
            saveInfo();
        } catch(Exception exc) {
            loginErrorLabel.setVisible(true);
        }
    }
    private void switchScreen(){
        loginLabel.setVisible(false);
        userField.setVisible(false);
        passwordField.setVisible(false);
        databaseField.setVisible(false);
        addressField.setVisible(false);
        loginButton.setVisible(false);
        startMenuBox.setVisible(true);
        tableComboBox.getItems().addAll(database.getTableNames());
        databaseLabel.setText(databaseField.getText());
        databaseLabel.setVisible(true);
    }
    private void saveInfo(){
        try {
            if (saveInfoCheckBox.isSelected()) {
                List<String> lines = Arrays.asList(userField.getText(), databaseField.getText(), addressField.getText());
                Path file = Paths.get("loginInfo.txt");
                Files.write(file, lines, Charset.forName("UTF-8"));
            }
        } catch(Exception exc) {
            loginErrorLabel.setText("Something happened.Info not saved.");
            loginErrorLabel.setVisible(true);
        }
    }
    private void loadInfo(){
        try {
            Path file = Paths.get("loginInfo.txt");
            List<String> loginInfo = Files.readAllLines(file);
            userField.setText(loginInfo.get(0));
            databaseField.setText(loginInfo.get(1));
            addressField.setText(loginInfo.get(2));
        } catch (Exception ex) { // no info to load
        }
    }

}

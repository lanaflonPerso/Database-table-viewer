import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.Collection;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

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
    private ComboBox viewComboBox;
    @FXML
    private ComboBox tableComboBox;
    @FXML
    private VBox startMenuBox;
    @FXML
    public void initialize(){
    }

    public void changeViews(ActionEvent event){

    }
    public void changeTables(ActionEvent event) throws Exception {
        Table selectedTable = database.getTable((String)tableComboBox.getSelectionModel().getSelectedItem());
        ObservableList<Column> tableColumns = FXCollections.observableList(selectedTable.getColumns());
        tableViewTable.setItems(tableColumns);
    }
    public void loginAuth(ActionEvent event) {
        try {
            database = new Database(databaseField.getText(), DatabaseConnection.getInstance(userField.getText(), passwordField.getText(),addressField.getText()));
            loginLabel.setVisible(false);
            userField.setVisible(false);
            passwordField.setVisible(false);
            databaseField.setVisible(false);
            addressField.setVisible(false);
            loginButton.setVisible(false);
            startMenuBox.setVisible(true);
            tableComboBox.getItems().addAll(database.getTableNames());
            viewComboBox.getSelectionModel().selectFirst();
            System.out.println("Success"); // remove at the end

        } catch(Exception exc) {
            loginErrorLabel.setVisible(true);
        }
    }
}

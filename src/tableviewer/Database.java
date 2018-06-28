package tableviewer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomov on 2.8.2017 г..
 */
public class Database {
    private String name;
    private List<Table> tables;
    private DatabaseConnection connection;
    public Database(String name, DatabaseConnection connection) throws SQLException {
        tables = new ArrayList<>();
        this.name = name;
        this.connection = connection;
        getTables();
        getTableColumns();
        getTableContents();
        connection.closeConnection();
    }
    public Table getTable(String tableName) throws Exception{
        for(Table table : tables) {
            if(table.getName().equals(tableName))
                return table;
        }
        throw new Exception("No such table");
    }
    private void getTableContents() throws SQLException {
        List<String> rowValues;
        for(Table table : tables) {
            List<String> columnNames = table.getColumnNames();
            ResultSet rs = connection.queryTry("SELECT * FROM "+name+".`"+table.getName()+"`");
            while(rs.next()) {
                rowValues = new ArrayList<>();
                for (int i = 1; i <= table.getTableSize(); i++) {
                    rowValues.add(rs.getString(i));
                }
                table.addRow(new Row(columnNames,rowValues));
            }
        }
    }
    private void getTables() throws SQLException {
        ResultSet rs = connection.queryTry("SELECT * FROM information_schema.`COLUMNS` WHERE `TABLE_SCHEMA` = \""+name+"\"");
        while(rs.next()) {
            if(!tables.contains(new Table(rs.getString("TABLE_NAME")))) {
                tables.add(new Table(rs.getString("TABLE_NAME")));
            }
        }

    }
    public List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        for(Table table : tables){
            tableNames.add(table.getName());
        }
        return tableNames;
    }
    private void getTableColumns() throws SQLException {
        for(Table table : tables) {
            ResultSet rs = connection.queryTry("SELECT * FROM information_schema.`COLUMNS` WHERE TABLE_SCHEMA = \""+name+"\" AND TABLE_NAME = \""+table.getName()+"\"");
            while(rs.next()) {
                Column column = new Column(rs.getString("COLUMN_NAME"),rs.getString("ORDINAL_POSITION"),
                                rs.getString("IS_NULLABLE"),rs.getString("DATA_TYPE"),rs.getString("CHARACTER_MAXIMUM_LENGTH"),
                                rs.getString("COLUMN_TYPE"));
                table.addColumn(column);
            }

        }
    }
}

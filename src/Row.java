import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.*;

/**
 * Created by Tomov on 2.8.2017 г..
 */
public class Row {
    Map<String,String> row;
    public Row(List<String> columnNames, List<String> values) {
        row = new LinkedHashMap<>();
        int valueCounter = 0;
        for(String columnName: columnNames) {
            row.put(columnName,values.get(valueCounter++));
        }
    }
    public List<Cell> getCells(){
        List<Cell> cellList = new ArrayList<>();
        for(String cellKey : row.keySet()) {
            cellList.add(new Cell(row.get(cellKey)));
        }
        return cellList;
    }
    public List<String> getRowValues() {
        return (List<String>) row.values();
    }
    public ObservableMap<String,String> getObservableRowMap(){
        return FXCollections.observableMap(row);
    }

    public String getValue(String columnName) {
        return row.get(columnName);
    }
    public String getRowString(){
        StringBuilder rowString = new StringBuilder();
        for(String column : row.keySet()) {
            rowString.append(row.get(column) + "  ");
        }
        return rowString.toString();
    }
}
class Cell
{
    private final String value;

    public Cell(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}

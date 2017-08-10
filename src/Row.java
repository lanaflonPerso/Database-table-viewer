import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

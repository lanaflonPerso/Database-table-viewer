package tableviewer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomov on 2.8.2017 г..
 */
public class Table {
    private String name;
    private List<Column> columns;
    private List<Row> rows;
    public Table(String name) {
        this.name = name;
        columns = new ArrayList<>();
        rows = new ArrayList<>();
    }
    public void addColumn(Column column) {
        columns.add(column);
    }
    public void addRow(Row row){
        rows.add(row);
    }
    public List<Column> getColumns(){
        return columns;
    }
    public List<Row> getRows(){
        return rows;
    }
    public List<String> getColumnNames(){
        List<String> columnNames = new ArrayList<>();
        for(Column column : columns) {
            columnNames.add(column.getColumnName());
        }
        return columnNames;
    }
    public String getName() {
        return name;
    }
    public int getTableSize(){
        return columns.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        return name.equals(table.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

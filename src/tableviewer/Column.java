package tableviewer;

/**
 * Created by Tomov on 2.8.2017 г..
 */
public class Column {
    private String columnName,
            positionID,
            isNullable,
            dataType,
            maxLength,
            columnType;

    public Column(String columnName, String positionID, String isNullable, String dataType, String maxLength, String columnType) {
        this.columnName = columnName;
        this.positionID = positionID;
        this.isNullable = isNullable;
        this.dataType = dataType;
        this.maxLength = maxLength;
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getPositionID() {
        return positionID;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public String getDataType() {
        return dataType;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public String getColumnType() {
        return columnType;
    }
}

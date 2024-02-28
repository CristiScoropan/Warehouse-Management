package dao;

public class deletequery {
    private String table = null;

    private String field = null;

    private String value = null;

    private String query;

    public deletequery() {
        this.query = null;
    }

    public deletequery setTable(String table) {
        this.table = table.concat("s");
        return this;
    }

    public deletequery setField(String field) {
        this.field = field;
        return this;
    }

    public deletequery setValue(String v) {
        this.value = v;
        return this;
    }
    public String build() {
        if (table != null && field != null && value != null) {
            query = "DELETE FROM " + table + " WHERE " + field + " = '" + value + "'";
        }
        return query;
    }
    public void clear() {
        query = null;
        field = null;
        table = null;
        value = null;
    }
}

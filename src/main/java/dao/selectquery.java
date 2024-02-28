package dao;

import java.util.Objects;

public class selectquery {
    private String table = null;

    private String field = null;

    private String query;

    public selectquery() {
        this.query = null;
    }

    public selectquery setTable(String table) {
        this.table = table;
        return this;
    }

    public selectquery setField(String field) {
        this.field = field;
        return this;
    }

    /**
     * Builds the SELECT query based on the specified table and field.
     *
     * @return The select query as a string, or null if any required parameters are missing.
     */
    public String build() {
        if (table != null && field != null) {
            if (Objects.equals(field, "ALL")) {
                query = "SELECT * FROM " + table + " ORDER BY id ASC";
            } else {
                query = "SELECT * FROM " + table + " WHERE " + field + " = ";
            }
        }
        return query;
    }

    /**
     * Clears the current state of the SelectQueryBuilder instance.
     * Resets the table, field, and query values to null.
     */
    public void clear() {
        query = null;
        field = null;
        table = null;
    }
}

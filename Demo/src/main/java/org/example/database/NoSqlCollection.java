package org.example.database;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public interface NoSqlCollection {
    public JSONObject getSchema();
    public String getCollectionName();
}

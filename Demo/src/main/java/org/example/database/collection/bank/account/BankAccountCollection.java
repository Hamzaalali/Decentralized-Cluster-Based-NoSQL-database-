package org.example.database.collection.bank.account;
import org.example.database.NoSqlCollection;
import org.example.json.JsonBuilder;
import org.example.server_client.DataTypes;
import org.json.simple.JSONObject;
public class BankAccountCollection implements NoSqlCollection {
    @Override
    public JSONObject getSchema() {
        JSONObject schema= JsonBuilder.getBuilder()
                .add("accountNumber",DataTypes.LONG)
                .add("clientName", DataTypes.STRING)
                .add("balance",DataTypes.DOUBLE)
                .add("hasInsurance",DataTypes.BOOLEAN)
                .add("accountType",DataTypes.STRING)
                .build();
        return schema;
    }

    @Override
    public String getCollectionName() {
        return "accounts";
    }

}

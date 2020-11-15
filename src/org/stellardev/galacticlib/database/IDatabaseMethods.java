package org.stellardev.galacticlib.database;

public interface IDatabaseMethods {

    String getDeleteTable(String table);

    String getCreateTable(String table);

    String getPullAll(String table);

    String getPullRow(String table, String row);

    String getPushRow(String table);

    String getDeleteRow(String table, String row);

    String parseTableId(String input);

}

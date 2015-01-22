package nz.co.space.cyber.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Peter on 04/10/2014.
 */
public class DataBaseControlHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "season_planning_database.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseControlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        DatabaseControl.onCreate(database);
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        DatabaseControl.onUpgrade(database, oldVersion, newVersion);
    }
}
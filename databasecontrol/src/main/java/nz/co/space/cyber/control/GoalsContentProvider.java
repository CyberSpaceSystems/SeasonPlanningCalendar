package nz.co.space.cyber.control;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;

public class GoalsContentProvider extends ContentProvider {
    private static final int GOALS = 30;
    private static final int GOALS_ID = 40;
    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(DatabaseControl.GoalsColumns.AUTHORITY, DatabaseControl.GoalsColumns.BASE_PATH, GOALS);
        sURIMatcher.addURI(DatabaseControl.GoalsColumns.AUTHORITY, DatabaseControl.GoalsColumns.BASE_PATH + "/#", GOALS_ID);
    }
    private DataBaseControlHelper database;


    public GoalsContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        switch (uriType) {
            case GOALS:
                id = sqlDB.insert(DatabaseControl.GoalsColumns.TABLE_GOALS, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(DatabaseControl.GoalsColumns.BASE_PATH + "/" + id);
    }


    @Override
    public boolean onCreate() {
        database = new DataBaseControlHelper(getContext());

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(DatabaseControl.GoalsColumns.TABLE_GOALS);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case GOALS:
                break;
            case GOALS_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(DatabaseControl.GoalsColumns.COLUMN_GOALS_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // Make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {


        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case GOALS:
                rowsUpdated = sqlDB.update(DatabaseControl.GoalsColumns.TABLE_GOALS,
                        values,
                        selection,
                        selectionArgs);
                break;
            case GOALS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(DatabaseControl.GoalsColumns.TABLE_GOALS,
                            values,
                            DatabaseControl.GoalsColumns.COLUMN_GOALS_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(DatabaseControl.GoalsColumns.TABLE_GOALS,
                            values,
                            DatabaseControl.GoalsColumns.COLUMN_GOALS_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }


    private void checkColumns(String[] projection) {
        String[] available = {DatabaseControl.GoalsColumns.COLUMN_GOALS_ID, DatabaseControl.GoalsColumns.COLUMN_GOALS_WEEKLY_ID, DatabaseControl.GoalsColumns.COLUMN_GOALS_DEC};

        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(
                    Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(
                    Arrays.asList(available));
            // Check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(
                        "Unknown columns in projection");
            }
        }
    }

}


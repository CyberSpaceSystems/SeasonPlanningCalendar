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

public class MonthContentProvider extends ContentProvider {

    private static final int MONTHS = 10;
    private static final int MONTH_ID = 20;
    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

    //  private static final String AUTHORITY = "com.cyberspace.database.control";
    //   private static final String BASE_PATH = "season_planning_month";


    //  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
    //          + "/" + BASE_PATH);
    static {
        sURIMatcher.addURI(DatabaseControl.MonthlyColumns.AUTHORITY, DatabaseControl.MonthlyColumns.BASE_PATH, MONTHS);
        sURIMatcher.addURI(DatabaseControl.MonthlyColumns.AUTHORITY, DatabaseControl.MonthlyColumns.BASE_PATH + "/#", MONTH_ID);


    }
    private DataBaseControlHelper database;


    public MonthContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (sURIMatcher.match(uri)) {
            case MONTHS:
                return DatabaseControl.CONTENT_TYPE;
            case MONTH_ID:
                return DatabaseControl.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        switch (uriType) {
            case MONTHS:
                id = sqlDB.insert(DatabaseControl.MonthlyColumns.TABLE_SEASON_PLANNING_MONTH, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(DatabaseControl.MonthlyColumns.BASE_PATH + "/" + id);
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
        queryBuilder.setTables(DatabaseControl.MonthlyColumns.TABLE_SEASON_PLANNING_MONTH);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case MONTHS:
                break;
            case MONTH_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_ID + "="
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
            case MONTHS:
                rowsUpdated = sqlDB.update(DatabaseControl.MonthlyColumns.TABLE_SEASON_PLANNING_MONTH,
                        values,
                        selection,
                        selectionArgs);
                break;
            case MONTH_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(DatabaseControl.MonthlyColumns.TABLE_SEASON_PLANNING_MONTH,
                            values,
                            DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(DatabaseControl.MonthlyColumns.TABLE_SEASON_PLANNING_MONTH,
                            values,
                            DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_ID + "=" + id
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
        String[] available = {
                DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_ID,
                DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_MONTHLY_IN_THE_YEAR_ID,
                DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_PARSE_OF_THE_MONTH,
                DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_VOLUME,
                DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_INTENSITY,
                DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_KEYWORD_ID,
                DatabaseControl.MonthlyColumns.MONTHLY_COLUMN_EVENTS_ID

        };

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

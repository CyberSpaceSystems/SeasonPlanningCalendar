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

public class KeyCoachingPointsContentProvider extends ContentProvider {


    private static final int POINTS = 10;
    private static final int POINTS_ID = 20;
    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(DatabaseControl.KeyCoachingPoint.AUTHORITY, DatabaseControl.KeyCoachingPoint.BASE_PATH, POINTS);
        sURIMatcher.addURI(DatabaseControl.KeyCoachingPoint.AUTHORITY, DatabaseControl.KeyCoachingPoint.BASE_PATH + "/#", POINTS_ID);
    }
    private DataBaseControlHelper database;


    public KeyCoachingPointsContentProvider() {
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (sURIMatcher.match(uri)) {
            case POINTS:
                return DatabaseControl.CONTENT_TYPE;
            case POINTS_ID:
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
            case POINTS:
                id = sqlDB.insert(DatabaseControl.KeyCoachingPoint.TABLE_KEY_COACHING_POINTS, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(DatabaseControl.KeyCoachingPoint.BASE_PATH + "/" + id);
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
        queryBuilder.setTables(DatabaseControl.KeyCoachingPoint.TABLE_KEY_COACHING_POINTS);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case POINTS:
                break;
            case POINTS_ID:
                // Adding the ID to the original query
                queryBuilder.appendWhere(DatabaseControl.KeyCoachingPoint.KEY_COACHING_ID + "="
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
            case POINTS:
                rowsUpdated = sqlDB.update(DatabaseControl.KeyCoachingPoint.TABLE_KEY_COACHING_POINTS,
                        values,
                        selection,
                        selectionArgs);
                break;
            case POINTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(DatabaseControl.KeyCoachingPoint.TABLE_KEY_COACHING_POINTS,
                            values,
                            DatabaseControl.KeyCoachingPoint.KEY_COACHING_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(DatabaseControl.KeyCoachingPoint.TABLE_KEY_COACHING_POINTS,
                            values,
                            DatabaseControl.KeyCoachingPoint.KEY_COACHING_ID + "=" + id
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
        String[] available = {DatabaseControl.KeyCoachingPoint.KEY_COACHING_DISCIPLINE,
                DatabaseControl.KeyCoachingPoint.KEY_COACHING_ID,
                DatabaseControl.KeyCoachingPoint.KEY_COACHING_PHASE,
                DatabaseControl.KeyCoachingPoint.KEY_COACHING_POINT
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


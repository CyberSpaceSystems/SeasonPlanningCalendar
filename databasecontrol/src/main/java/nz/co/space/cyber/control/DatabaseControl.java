package nz.co.space.cyber.control;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Peter on 04/10/2014.
 */
public class DatabaseControl {


    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.co.space.cyber.databasecontrol";

    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.co.space.cyber.databasecontrol";
    public static final String TABLE_KEY_COACHING_POINTS = "key_coaching_points";
    // Database creation SQL statement
    public static final String KEY_COACHING_ID = "_id";
    public static final String KEY_COACHING_DISCIPLINE = "discipline";
    public static final String KEY_COACHING_PHASE = "phase";
    public static final String KEY_COACHING_POINT = "key_coaching_point";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_KEY_COACHING_POINTS
            + "("
            + KEY_COACHING_ID + " integer primary key autoincrement, "
            + KEY_COACHING_DISCIPLINE + " text not null, "
            + KEY_COACHING_PHASE + " text not null, "
            + KEY_COACHING_POINT + " text not null "
            + ");";

    private static final String MONTHLY_CREATE = "CREATE TABLE IF NOT EXISTS "
            + MonthlyColumns.TABLE_SEASON_PLANNING_MONTH
            + "( "
            + MonthlyColumns.MONTHLY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MonthlyColumns.MONTHLY_COLUMN_MONTHLY_IN_THE_YEAR_ID + " INTEGER NOT NULL, "
            + MonthlyColumns.MONTHLY_COLUMN_VOLUME + " TEXT NOT NULL, "
            + MonthlyColumns.MONTHLY_COLUMN_INTENSITY + " TEXT NOT NULL, "
            + MonthlyColumns.MONTHLY_COLUMN_PARSE_OF_THE_MONTH + " TEXT NOT NULL "
            + MonthlyColumns.MONTHLY_COLUMN_KEYWORD_ID + " INTEGER NOT NULL, "
            + MonthlyColumns.MONTHLY_COLUMN_EVENTS_ID + " INTEGER NOT NULL "
            + ");";
    private static final String WEEKLY_CREATE = "CREATE TABLE IF NOT EXISTS "
            + WeeklyColumns.TABLE_SEASON_PLANNING_WEEK
            + "( "
            + WeeklyColumns.WEEKLY_COLUMN_WEEKLY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WeeklyColumns.WEEKLY_COLUMN_WEEKLY_IN_THE_MONTH_ID + " INTEGER NOT NULL, "
            + WeeklyColumns.WEEKLY_COLUMN_TOTAL_NUMBER_OF_HOURS_TRANIED_IN_THAT_WEEK + " INTEGER NOT NULL, "
            + WeeklyColumns.WEEKLY_COLUMN_TOTAL_NUMBER_OF_KMS_IN_THAT_WEEK + " INTEGER NOT NULL, "
            + WeeklyColumns.WEEKLY_COLUMN_TOTAL_NUMBER_OF_SESSION_TRANIED_IN_THAT_WEEK + " INTEGER NOT NULL, "
            + WeeklyColumns.WEEKLY_COLUMN_M_CYCLE + " TEXT NOT NULL, "
            + WeeklyColumns.WEEKLY_COLUMN_GOALS_ID + " INTEGER NOT NULL, "
            + WeeklyColumns.WEEKLY_COLUMN_COMMENTS_ID + " INTEGER NOT NULL, "
            + ");";
    // Database creation SQL statement
    private static final String GOALS_CREATE = "CREATE TABLE IF NOT EXISTS "
            + GoalsColumns.TABLE_GOALS
            + "( "
            + GoalsColumns.COLUMN_GOALS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GoalsColumns.COLUMN_GOALS_WEEKLY_ID + " INTEGER NOT NULL, "
            + GoalsColumns.COLUMN_GOALS_DEC + " TEXT NOT NULL "
            + ");";
    // Database creation SQL statement
    private static final String COMMENT_CREATE = "CREATE TABLE IF NOT EXISTS "
            + CommentColumns.TABLE_COMMENTS
            + "( "
            + CommentColumns.COLUMN_COMMENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CommentColumns.COLUMN_COMMENTS_WEEKLY_ID + " INTEGER NOT NULL, "
            + CommentColumns.COLUMN_COMMENTS_DEC + " TEXT NOT NULL "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(MONTHLY_CREATE);
        database.execSQL(WEEKLY_CREATE);
        database.execSQL(GOALS_CREATE);
        database.execSQL(COMMENT_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(DatabaseControl.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + GoalsColumns.TABLE_GOALS);
        database.execSQL("DROP TABLE IF EXISTS " + MonthlyColumns.TABLE_SEASON_PLANNING_MONTH);
        database.execSQL("DROP TABLE IF EXISTS " + WeeklyColumns.TABLE_SEASON_PLANNING_WEEK);
        onCreate(database);
    }


    public static final class MonthlyColumns implements BaseColumns {

        public static final String AUTHORITY = "nz.co.space.cyber.control.month";
        public static final String BASE_PATH = "season_planning_month";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/" + BASE_PATH);


        public static final String TABLE_SEASON_PLANNING_MONTH = "season_planning_month";
        public static final String MONTHLY_COLUMN_ID = "_id";
        public static final String MONTHLY_COLUMN_MONTHLY_IN_THE_YEAR_ID = "month_in_the_year_id";
        public static final String MONTHLY_COLUMN_PARSE_OF_THE_MONTH = "parse_of_the_month";
        public static final String MONTHLY_COLUMN_VOLUME = "volume";
        public static final String MONTHLY_COLUMN_INTENSITY = "intensity";
        public static final String MONTHLY_COLUMN_KEYWORD_ID = "monthly_key_word_id";
        public static final String MONTHLY_COLUMN_EVENTS_ID = "monthly_events_id";

    }

    public static final class WeeklyColumns implements BaseColumns {

        public static final String AUTHORITY = "nz.co.space.cyber.control.week";
        public static final String BASE_PATH = "season_planning_week";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/" + BASE_PATH);

        public static final String TABLE_SEASON_PLANNING_WEEK = "season_planning_week";
        public static final String WEEKLY_COLUMN_WEEKLY_ID = "week_in_the_year_id";
        public static final String WEEKLY_COLUMN_WEEKLY_IN_THE_MONTH_ID = "week_in_the_month_id";
        public static final String WEEKLY_COLUMN_TOTAL_NUMBER_OF_SESSION_TRANIED_IN_THAT_WEEK = "total_number_session";
        public static final String WEEKLY_COLUMN_TOTAL_NUMBER_OF_HOURS_TRANIED_IN_THAT_WEEK = "total_number_of_hours_trained";
        public static final String WEEKLY_COLUMN_TOTAL_NUMBER_OF_KMS_IN_THAT_WEEK = "total_number_of_kms";
        public static final String WEEKLY_COLUMN_M_CYCLE = "m_cycle";
        public static final String WEEKLY_COLUMN_GOALS_ID = "goals_id";
        public static final String WEEKLY_COLUMN_COMMENTS_ID = "comments_id";


    }

    public static final class SkillsSessionPlanChecklist implements BaseColumns {

        public static final String SESSION_OBJECTIVE = "objective_of_the_session";
        public static final String SESSION_DATE = "date_of_the_session";
        public static final String SESSION_TIME = "time_of_the_session";
        public static final String SESSION_LENGTH = "length_of_the_session";
        public static final String SESSION_VENUE = "venue_for_the_session";
        public static final String SESSION_ATHLETE_STAGE = "athlete_participants_stage";
        public static final String SESSION_ATHLETE_AGE = "athlete_participants_age";
        public static final String SESSION_ATHLETE_NUMBERS = "athlete_participants_number";
        public static final String SESSION_WARMUP_ID = "session_warmup_id";
        public static final String SESSION_WARMDOWN_ID = "session_warmdowm_id";
        public static final String SESSION_ATHLETE_INJURYORILLNESS = "athlete_participants_injury_or_illness";
        public static final String SESSION_HELPERS = "session_helper";
    }

    public static final class KeyCoachingPoint implements BaseColumns {

        public static final String AUTHORITY = "com.cyberspace.database.control.coaching";
        public static final String BASE_PATH = "season_planning_key_coaching_points";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/" + BASE_PATH);


        public static final String TABLE_KEY_COACHING_POINTS = "season_planning_key_coaching_points";

        public static final String KEY_COACHING_ID = "_id";
        public static final String KEY_COACHING_DISCIPLINE = "discipline";
        public static final String KEY_COACHING_PHASE = "phase";
        public static final String KEY_COACHING_POINT = "key_coaching_point";

    }

    public static final class GoalsColumns implements BaseColumns {


        public static final String AUTHORITY = "com.cyberspace.database.control.goals";
        public static final String BASE_PATH = "season_planning_goals";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/" + BASE_PATH);

        public static final String TABLE_GOALS = "season_planning_goals";
        public static final String COLUMN_GOALS_ID = "_id";
        public static final String COLUMN_GOALS_WEEKLY_ID = "goals_weekly_in_the_year_id";
        public static final String COLUMN_GOALS_DEC = "goals";
    }

    ///COMMENTS
    public static final class CommentColumns implements BaseColumns {


        public static final String AUTHORITY = "com.cyberspace.database.control.comments";
        public static final String BASE_PATH = "season_planning_comments";

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/" + BASE_PATH);

        public static final String TABLE_COMMENTS = "season_planning_comments";
        public static final String COLUMN_COMMENTS_ID = "_id";
        public static final String COLUMN_COMMENTS_WEEKLY_ID = "comments_weekly_in_the_year_id";
        public static final String COLUMN_COMMENTS_DEC = "comments";
    }

    public static final class DayColumns implements BaseColumns {
        public static final String TABLE_SEASON_PLANNING_DAY = "season_planning_day";
        public static final String DAY_COLUMN_ID = "_id";
        public static final String DAY_COLUMN_SESSION = "session_name";
        public static final String DAY_COLUMN_AM_MID_PM = "am_mid_pm";

    }
}

package id.aryad.cookies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "FoodRecipe6.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tb_resep";
    private static final String COLUMN_ID = "id_resep";
    private static final String COLUMN_RECIPE_NAME = "nama_resep";
    private static final String COLUMN_DESCRIPTION = "deskripsi";
    private static final String COLUMN_RECIPE = "resep";
    private static final String COLUMN_TOOLS = "tools";
    private static final String COLUMN_STEPS = "steps";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_RECIPE_NAME + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_RECIPE + " TEXT, " +
                        COLUMN_TOOLS + " TEXT, " +
                        COLUMN_STEPS + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addData(String nama_resep, String deskripsi, String resep, String tools, String steps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RECIPE_NAME, nama_resep);
        cv.put(COLUMN_DESCRIPTION, deskripsi);
        cv.put(COLUMN_RECIPE, resep);
        cv.put(COLUMN_TOOLS, tools);
        cv.put(COLUMN_STEPS, steps);

        long result = db.insert(TABLE_NAME, null, cv);

        System.out.println("Result : " + result);
        Log.d(TAG, "addData: result : " + result);

        if(result == -1){
            Toast.makeText(context, "Data gagal tersimpan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data berhasil tersimpan", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String foodname, String description, String recipe, String tools, String steps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RECIPE_NAME, foodname);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_RECIPE, recipe);
        cv.put(COLUMN_TOOLS, tools);
        cv.put(COLUMN_STEPS, steps);

        long result = db.update(TABLE_NAME, cv, "id_resep=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully updated data.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id_resep=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Delete.", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.experiment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

public class DBhelper extends SQLiteOpenHelper {
    public static final String date = "Date";
    public static final String note = "Note";
    public static final String id = "_id";
    public static final String notes_table = "notes_table";
    private Context context;


    public DBhelper(Context context){
        super(context, "Userdata.db", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String creatable = "create table "+notes_table+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+date+" VARCHAR(30), "+note+" VARCHAR(300))";
        sqLiteDatabase.execSQL(creatable);

    }

    public boolean onInsert(NoteModel theNote){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(date,theNote.getDateTime());
        cv.put(note,theNote.getNote());
        long insert = sqLiteDatabase.insert(notes_table,null, cv);
        if (insert == 1){
            return true;
        } else{
            return false;
        }
    }
//
//    public Cursor viewAll(){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        return sqLiteDatabase.rawQuery("SELECT * FROM "+notes_table,null);
//    }

    public SimpleCursorAdapter populateListViewFromDB(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+notes_table,null);
        String fromFieldNames[] = {DBhelper.id, DBhelper.date, DBhelper.note};
        int[] toViewIds = new int[]{R.id.single_note_id,R.id.single_note_date,R.id.single_note_note};
        return new SimpleCursorAdapter(
                context,
                R.layout.single_note,
                cursor,
                fromFieldNames,
                toViewIds
                );
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}

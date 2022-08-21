package sg.edu.np.practical_6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {
    private String TName = "User";
    private String col0 = "NAME";
    private String col1 = "DESCRIPTION";
    private String col2 = "ID";
    private String col3 = "FOLLOWED";
    DBHandler(Context c){
        super(c,"users.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "Create Table User (NAME TEXT, DESCRIPTION TEXT, ID INTEGER PRIMARY KEY, FOLLOWED BOOLEAN)";
        db.execSQL(query);
        ArrayList<User> userList = initRandomUser();
        for (User user : userList){
            ContentValues values = new ContentValues();
            values.put(col0, user.name);
            values.put(col1, user.description);
            values.put(col2, user.id);
            values.put(col3, user.followed);
            db.insert(TName, null, values);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.close();
    }
    public ArrayList<User> getUsers(){
        String query = "Select * FROM USER";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<User> userList = new ArrayList<>();
        while(cursor.moveToNext()){
            User user = new User();
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = cursor.getInt(2);
            user.followed = fGetBoolbyInt(cursor.getInt(3));
            userList.add(user);

        }
        cursor.close();
        db.close();
        return userList;
    }
    public User getSpecificUSer(Integer id){
        String query = "SELECT * FROM USER WHERE ID = " + "\"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        User user = new User();

        if(cursor.moveToFirst()){
            Boolean bool = fGetBoolbyInt(cursor.getInt(3));
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = cursor.getInt(2);
            user.followed = bool;
        }
        return user;
    }
    public void  updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer iFollowed = fGetIntbyBool(user.followed);
        String query = "UPDATE USER SET FOLLOWED = \"" + iFollowed + "\" WHERE ID = \"" + user.id + "\"";
        db.execSQL(query);
        db.close();
    }
    private ArrayList<User> initRandomUser(){
        ArrayList<User> userList = new ArrayList<>();
        Integer randID = 0;
        while (userList.size() <20){
            Random rand = new Random();
            String randName = "Name " + Integer.toString(rand.nextInt());
            String randDescription = "Description " + Integer.toString(rand.nextInt());
            Boolean randFollowed = rand.nextBoolean();
            User usr = new User(randName, randDescription, randID, randFollowed);
            userList.add(usr);
            randID += 1;
        }
        return userList;
    }
    public int fGetIntbyBool(Boolean b){
        if (b == true){
            return 1;
        }
        else{
            return 0;
        }
    }
    public boolean fGetBoolbyInt(Integer i){
        if (i == 1){
            return true;
        }
        else {
            return false;
        }
    }

}

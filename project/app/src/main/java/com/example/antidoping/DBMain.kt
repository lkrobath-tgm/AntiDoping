package com.example.antidoping
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;


class DBMain (private val context: Context) : SQLiteOpenHelper(context, "nada-small", null, 3) {
    private var dbObj: SQLiteDatabase? = null
    private final var DB_NAME = "nada-small.db"
    private final var DB_PATH = "com/example/antidoping/assets/"


    @Throws(IOException::class)
    fun createDB() {
        context.assets.open("nada-small.db")

        this.readableDatabase
        Log.i("Readable ends..........", "end")

        try {
            copyDB()
            Log.i("copy db ends...........", "end")

        } catch (e: IOException) {

            throw Error("Error copying database")
        }

    }

    private fun checkDB(): Boolean {

        var checkDB: SQLiteDatabase? = null

        try {
            val path = DB_PATH + DB_NAME
            Log.i("myPath ......", path)
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY)

            Log.i("myPath ......", path)
            if (checkDB != null) {
                val c = checkDB.rawQuery("SELECT * FROM bank", null)
                Log.i("Cursor.......", c.getString(0))
                c.moveToFirst()
                val contents = arrayOfNulls<String>(80)
                var flag = 0

                while (!c.isAfterLast) {
                    var temp = ""
                    val s2 = c.getString(0)
                    val s3 = c.getString(1)
                    val s4 = c.getString(2)
                    temp = "$temp\n Id:$s2\tType:$s3\tBal:$s4"
                    contents[flag] = temp
                    flag = flag + 1

                    Log.i("DB values.........", temp)
                    c.moveToNext()

                }
            } else {
                return false
            }

        } catch (e: SQLiteException) {
            e.printStackTrace()
        }

        checkDB?.close()
        return if (checkDB != null) true else false
    }

    @Throws(IOException::class)
    fun copyDB() {
        try {
            Log.i("inside copyDB..........", "start")

            val ip = context.assets.open(DB_NAME + ".db")
            Log.i("Input Stream....", ip.toString() + "")
            val op = DB_PATH + DB_NAME
            val output = FileOutputStream(op)
            val buffer = ByteArray(1024)
            var length: Int
            length = ip.read(buffer)
            while (length > 0) {
                output.write(buffer, 0, length)
                Log.i("Content.... ", length.toString() + "")
                length = ip.read(buffer)
            }
            output.flush()
            output.close()
            ip.close()
        } catch (e: IOException) {
            Log.v("error", e.toString())
        }

    }

    @Throws(SQLException::class)
    fun openDB() {

        val myPath = DB_PATH + DB_NAME
        dbObj = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE)
        Log.i("open DB......", dbObj!!.toString())
    }

    @Synchronized
    override fun close() {

        if (dbObj != null)
            dbObj!!.close()

        super.close()
    }

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}
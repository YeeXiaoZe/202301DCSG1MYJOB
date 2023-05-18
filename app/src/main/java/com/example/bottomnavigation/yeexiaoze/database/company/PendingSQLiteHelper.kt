package com.example.bottomnavigation.yeexiaoze.database.company

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PendingSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, "Pending.db", null, 1)
{
    companion object
    {
        private const val PENDING_TABLE = "pending"
        private const val USERNAME = "username"
        private const val COMPANY_NAME = "companyName"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " + PENDING_TABLE + "(" + USERNAME +
                " TEXT PRIMARY KEY, " + COMPANY_NAME + " TEXT UNIQUE" + ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $PENDING_TABLE")
    }

    fun insertPending(pending: CompanyModel): Long
    {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(USERNAME, pending.username)
        contentValues.put(COMPANY_NAME, pending.companyName)

        val result = db.insert(PENDING_TABLE, null, contentValues)
        db.close()

        return result
    }

    @SuppressLint("Range", "Recycle")
    fun getAllPending(): ArrayList<CompanyModel>
    {
        val pendingList: ArrayList<CompanyModel> = ArrayList()
        val query = "SELECT * FROM $PENDING_TABLE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try
        {
            cursor = db.rawQuery(query, null)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
            db.execSQL(query)

            return ArrayList()
        }

        var username: String
        var companyName: String

        if (cursor.moveToFirst())
        {
            do
            {
                username = cursor.getString(cursor.getColumnIndex("username"))
                companyName = cursor.getString(cursor.getColumnIndex("companyName"))

                pendingList.add(
                    CompanyModel(username = username, companyName = companyName)
                )
            }while (cursor.moveToNext())
        }

        return pendingList
    }
}
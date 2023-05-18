package com.example.bottomnavigation.yeexiaoze.database.selfDevelopment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bottomnavigation.yeexiaoze.database.selfDevelopment.ChapterSQLiteHelper.Companion.CHAPTER_TABLE

class SubchapterSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, "Subchapter.db", null, 1)
{
    companion object {
        private const val SUBCHAPTER_TABLE = "subchapter"
        private const val SUBCHAPTER_ID = "subchapterID"
        private const val TITLE = "title"
        private const val CONTENT = "content"
        private const val LINK = "link"
        private const val CHAPTER_ID = "chapterID"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " + SUBCHAPTER_TABLE + "(" +
                SUBCHAPTER_ID + " TEXT PRIMARY KEY, " + TITLE + " TEXT, " +
                CONTENT + " TEXT, " + LINK + " TEXT, " +
                CHAPTER_ID + " TEXT, FOREIGN KEY(" + CHAPTER_ID + ") REFERENCES " +
                CHAPTER_TABLE + "(" + CHAPTER_ID + "))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $SUBCHAPTER_TABLE")
    }

    fun insertSubchapter(subchapter: SubchapterModel): Long
    {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(SUBCHAPTER_ID, subchapter.subchapterID)
        contentValues.put(TITLE, subchapter.title)
        contentValues.put(CONTENT, subchapter.content)
        contentValues.put(LINK, subchapter.link)
        contentValues.put(CHAPTER_ID, subchapter.chapterID)

        val result = db.insert(SUBCHAPTER_TABLE, null, contentValues)
        db.close()

        return result
    }

    @SuppressLint("Range")
    fun getAllSubchapter(): ArrayList<SubchapterModel>
    {
        val subchapterList: ArrayList<SubchapterModel> = ArrayList()
        val query = "SELECT * FROM $SUBCHAPTER_TABLE"
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

        var subchapterID: String
        var title: String
        var content: String
        var link: String
        var chapterID: String

        if (cursor.moveToFirst())
        {
            do
            {
                subchapterID = cursor.getString(cursor.getColumnIndex("subchapterID"))
                title = cursor.getString(cursor.getColumnIndex("title"))
                content = cursor.getString(cursor.getColumnIndex("content"))
                link = cursor.getString(cursor.getColumnIndex("link"))
                chapterID = cursor.getString(cursor.getColumnIndex("chapterID"))

                subchapterList.add(
                    SubchapterModel(subchapterID = subchapterID, title = title, content = content,
                        link = link, chapterID = chapterID)
                )
            }while (cursor.moveToNext())
        }

        return subchapterList
    }

    @SuppressLint("Range")
    fun getAttribute(COLUMN_NAME: String): ArrayList<String>
    {
        val attributeList: ArrayList<String> = ArrayList()
        val query = "SELECT $COLUMN_NAME FROM $SUBCHAPTER_TABLE"
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

        var attribute: String

        if (cursor.moveToFirst())
        {
            do
            {
                attribute = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))

                attributeList.add(attribute)
            }while (cursor.moveToNext())
        }

        return attributeList
    }

    @SuppressLint("Range")
    fun conditionalGetAttribute(COLUMN_NAME: String, CONDITIONAL_COLUMN: String, CONDITIONAL_STRING: String): String
    {
        var attribute = ""
        val query = "SELECT $COLUMN_NAME FROM $SUBCHAPTER_TABLE WHERE $CONDITIONAL_COLUMN = '$CONDITIONAL_STRING'"
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

            return ""
        }

        if (cursor.moveToFirst())
        {
            do
            {
                attribute = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                break;
            }while (cursor.moveToNext())
        }

        return attribute
    }

    @SuppressLint("Range")
    fun getRecords(CHAPTER_ID: String): ArrayList<SubchapterModel>
    {
        val query = "SELECT * FROM $SUBCHAPTER_TABLE WHERE chapterID = '$CHAPTER_ID'"
        val db = this.readableDatabase
        val subchapterList: ArrayList<SubchapterModel> = ArrayList()

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

        if (cursor.moveToFirst())
        {
            do
            {
                val subchapterID = cursor.getString(cursor.getColumnIndex("subchapterID"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val content = cursor.getString(cursor.getColumnIndex("content"))
                val link = cursor.getString(cursor.getColumnIndex("link"))
                val chapterID = cursor.getString(cursor.getColumnIndex("chapterID"))

                subchapterList.add(
                    SubchapterModel(subchapterID = subchapterID, title = title, content = content,
                        link = link, chapterID = chapterID)
                )
            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return subchapterList
    }
}
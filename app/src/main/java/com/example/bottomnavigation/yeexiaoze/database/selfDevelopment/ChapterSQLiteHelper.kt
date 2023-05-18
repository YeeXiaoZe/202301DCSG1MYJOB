package com.example.bottomnavigation.yeexiaoze.database.selfDevelopment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ChapterSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, "Chapter.db", null, 1)
{
    companion object {
        const val CHAPTER_TABLE = "chapter"
        private const val CHAPTER_ID = "chapterID"
        private const val TITLE = "title"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " + CHAPTER_TABLE + "(" + CHAPTER_ID +
                " TEXT PRIMARY KEY, " + TITLE + " TEXT" + ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $CHAPTER_TABLE")
    }

    fun insertChapter(chapter: ChapterModel): Long
    {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(CHAPTER_ID, chapter.chapterID)
        contentValues.put(TITLE, chapter.title)

        val result = db.insert(CHAPTER_TABLE, null, contentValues)
        db.close()

        return result
    }

    @SuppressLint("Range")
    fun getAllChapter(): ArrayList<ChapterModel>
    {
        val chapterList: ArrayList<ChapterModel> = ArrayList()
        val query = "SELECT * FROM $CHAPTER_TABLE"
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

        var chapterID: String
        var title: String

        if (cursor.moveToFirst())
        {
            do
            {
                chapterID = cursor.getString(cursor.getColumnIndex("chapterID"))
                title = cursor.getString(cursor.getColumnIndex("title"))

                chapterList.add(
                    ChapterModel(chapterID = chapterID, title = title)
                )
            }while (cursor.moveToNext())
        }

        return chapterList
    }

    @SuppressLint("Range")
    fun getAttribute(COLUMN_NAME: String): ArrayList<String>
    {
        val attributeList: ArrayList<String> = ArrayList()
        val query = "SELECT $COLUMN_NAME FROM $CHAPTER_TABLE"
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
        val query = "SELECT $COLUMN_NAME FROM $CHAPTER_TABLE WHERE $CONDITIONAL_COLUMN = '$CONDITIONAL_STRING'"
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

    fun updateAttribute(COLUMN_FOR_UPDATE: String, STRING_UPDATE: String, CHAPTER_ID: String) {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        val update = contentValues.apply{
            put(COLUMN_FOR_UPDATE, STRING_UPDATE)
        }

        val selection = "chapterID = ?"
        val selectionArgs = arrayOf(CHAPTER_ID)

        db.update(CHAPTER_TABLE, update, selection, selectionArgs)
        db.close()
    }
}
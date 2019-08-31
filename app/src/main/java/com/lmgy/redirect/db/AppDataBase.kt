package com.lmgy.redirect.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lmgy.redirect.db.dao.HostDao
import com.lmgy.redirect.db.data.HostData
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.icu.lang.UCharacter.GraphemeClusterBreak.V





/**
 * @author lmgy
 * @date 2019/8/31
 */
@Database(entities = [HostData::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun hostDao(): HostDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDataBase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "redirect_database")
                    .build()
        }

    }
}
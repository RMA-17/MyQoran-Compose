package com.rmaproject.myqoran.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmaproject.myqoran.data.local.entities.*

@Database(
    entities = [Qoran::class],
    views = [Surah::class, Juz::class, Page::class],
    version = 1,
    exportSchema = false
)
abstract class QoranDatabase : RoomDatabase() {
    abstract fun dao(): QoranDao

    companion object {
        const val DATABASE_NAME = "quran.db"
    }
}
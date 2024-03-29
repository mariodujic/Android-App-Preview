package com.groundzero.gloriapatri.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.groundzero.gloriapatri.features.prayers.data.Prayer
import com.groundzero.gloriapatri.features.prayers.data.PrayersDao

@Database(entities = [Prayer::class], exportSchema = false, version = 1)
abstract class PersistenceDatabase : RoomDatabase() {

  abstract fun getPrayersDao(): PrayersDao

  companion object {

    @Volatile
    private var instance: PersistenceDatabase? = null

    fun getInstance(
      context: Context,
      locale: String
    ): PersistenceDatabase =
      instance
        ?: buildDatabase(
          context,
          locale
        ).also { instance = it }

    private fun buildDatabase(
      context: Context,
      locale: String
    ): PersistenceDatabase {

      val assetsPrayersPath = "database/$locale/prayers.db"

      return Room.databaseBuilder(
        context, PersistenceDatabase::class.java,
        PRAYERS_DATABASE_NAME
      )
        .allowMainThreadQueries()
        .createFromAsset(assetsPrayersPath)
        .build()
    }

    private const val PRAYERS_DATABASE_NAME = "prayers_database"
  }
}
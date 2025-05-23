package com.example.chineseholiday.di

import android.content.Context
import com.example.chineseholiday.data.AppDatabase
import com.example.chineseholiday.data.HolidayDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideHolidayDao(database: AppDatabase): HolidayDao {
        return database.holidayDao()
    }
} 
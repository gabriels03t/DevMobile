package com.example.lembretesdeeventos

import android.app.Application
import androidx.room.Room
import com.example.lembretedeeventos.database.LembreteDatabase

class LembreteApplication : Application() {

    lateinit var database: LembreteDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, LembreteDatabase::class.java, "lembrete-db")
            .build()
    }

    companion object {
        lateinit var instance: LembreteApplication
            private set
    }
}
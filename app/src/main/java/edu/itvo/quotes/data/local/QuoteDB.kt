package edu.itvo.quotes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.itvo.quotes.data.local.daos.QuoteDao
import edu.itvo.quotes.data.local.entities.QuoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [QuoteEntity::class, ], version = 1)
abstract class QuoteDB : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): QuoteDB {
            //--- Ejecutar si la instancia no es nulo y devolver la instancia,
            //--- sino crear la base de datos
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDB::class.java,
                    "quotes.dbf"
                )
                    //--- Limpia y reconstruye en lugar de migrar si no hay un objeto de migración
                    //--- Aqui no se revisa el tema de migración
                    .fallbackToDestructiveMigration()
                    .addCallback(QuoteDBCallback(scope))
                    .build()
                INSTANCE = instance
                //-- Devolver la instancia
                instance
            }
        }

        private class QuoteDBCallback(
            private val scope: CoroutineScope
        ) : Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populate(database.quoteDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more elements
         */
        suspend fun populate(quoteDao: QuoteDao) {

            quoteDao.deleteAll()

            val quote = QuoteEntity(
                id = 1,
                quote = "Solo se que no se nada",
                author = "Sócrates"
            )

            quoteDao.insert(quote)

        }
    }
}

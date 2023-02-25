package juanluis.cailiu.personajesrickandmorty.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import juanluis.cailiu.personajesrickandmorty.data.source.local.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDataBase:RoomDatabase() {
    abstract fun providesData(): CharacterDao
}




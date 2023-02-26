package juanluis.cailiu.personajesrickandmorty.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import juanluis.cailiu.personajesrickandmorty.data.source.local.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun characterInsert(data: List<CharacterEntity>)

    @Query("delete from character")
    suspend fun characterDelete()


    @Query("select * from character")
    suspend fun characterSelect(): List<CharacterEntity>



}









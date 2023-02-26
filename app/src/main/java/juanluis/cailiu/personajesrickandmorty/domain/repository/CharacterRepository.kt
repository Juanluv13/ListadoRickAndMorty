package juanluis.cailiu.personajesrickandmorty.domain.repository

import juanluis.cailiu.personajesrickandmorty.data.source.local.CharacterDao
import juanluis.cailiu.personajesrickandmorty.data.source.local.entity.CharacterEntity
import juanluis.cailiu.personajesrickandmorty.data.source.remote.dto.Character
import juanluis.cailiu.personajesrickandmorty.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CharacterRepository {
    suspend fun getCharacter(): Character
    suspend fun characterInsert(data: List<CharacterEntity>)
    suspend fun characterDelete()
    suspend fun characterSelect(): List<CharacterEntity>

    companion object {
        suspend fun deleteCharacter(characterModel: CharacterModel)= withContext(Dispatchers.IO) {
            CharacterDao.deleteCharacter(characterModel)
        }
    }
}
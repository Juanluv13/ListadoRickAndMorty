package juanluis.cailiu.personajesrickandmorty.domain.repository

import juanluis.cailiu.personajesrickandmorty.data.source.local.entity.CharacterEntity
import juanluis.cailiu.personajesrickandmorty.data.source.remote.dto.Character

interface CharacterRepository {
    suspend fun getCharacter(): Character
    suspend fun characterInsert(data: List<CharacterEntity>)
    suspend fun characterDelete()
    suspend fun characterSelect(): List<CharacterEntity>
}
package juanluis.cailiu.personajesrickandmorty.data.repository

import juanluis.cailiu.personajesrickandmorty.data.source.local.CharacterDao
import juanluis.cailiu.personajesrickandmorty.data.source.local.entity.CharacterEntity
import juanluis.cailiu.personajesrickandmorty.data.source.remote.ApiService
import juanluis.cailiu.personajesrickandmorty.data.source.remote.dto.Character
import juanluis.cailiu.personajesrickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val characterDao: CharacterDao
) : CharacterRepository {
    override suspend fun getCharacter(): Character = apiService.getCharacter()

    override suspend fun characterInsert(data: List<CharacterEntity>) =
        characterDao.characterInsert(data)

    override suspend fun characterDelete() = characterDao.characterDelete()
    override suspend fun characterSelect(): List<CharacterEntity> = characterDao.characterSelect()
}
package juanluis.cailiu.personajesrickandmorty.domain.usecase

import juanluis.cailiu.personajesrickandmorty.data.source.local.entity.toRoom
import juanluis.cailiu.personajesrickandmorty.domain.model.CharacterModel
import juanluis.cailiu.personajesrickandmorty.domain.model.toDomain
import juanluis.cailiu.personajesrickandmorty.domain.repository.CharacterRepository
import juanluis.cailiu.personajesrickandmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(): Flow<Resource<List<CharacterModel>>> = flow {
        try {
            emit(Resource.Loading<List<CharacterModel>>())
            val data = repository.characterSelect().map { it.toDomain() }
            if (data.isNotEmpty()) {
                emit(Resource.Success<List<CharacterModel>>(data))
            } else {
                val remote = repository.getCharacter().results!!.map { it!!.toDomain() }
                repository.characterDelete()
                repository.characterInsert(remote.map { it.toRoom() })
                emit(Resource.Success<List<CharacterModel>>(remote))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<CharacterModel>>(e.localizedMessage?: "No hay conexion a internet"))
        } catch (e: IOException) {
            emit(Resource.Error<List<CharacterModel>>("error"))
        }
    }

}
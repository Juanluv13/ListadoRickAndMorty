package juanluis.cailiu.personajesrickandmorty.domain.usecase

import io.mockk.*
import juanluis.cailiu.personajesrickandmorty.data.source.remote.dto.Character
import juanluis.cailiu.personajesrickandmorty.data.source.remote.dto.Result
import juanluis.cailiu.personajesrickandmorty.domain.model.CharacterModel
import juanluis.cailiu.personajesrickandmorty.domain.repository.CharacterRepository
import juanluis.cailiu.personajesrickandmorty.util.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import org.junit.Test



class CharacterUseCaseTest {

    private val characterkRepository = mockk<CharacterRepository>()
    private val characterUseCase = CharacterUseCase(characterkRepository)

    @Test
    suspend fun `when characterSelect returns empty list, getCharacter is called and returns test data`() {
        coEvery { characterkRepository.characterSelect() } returns emptyList()
        val testCharacters = listOf(
            Result(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                created = "21/04/2000",
                url = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            ), Result(
                id = 3,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                created = "21/04/2000",
                url = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            ), Result(
                id = 2,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                created = "21/04/2000",
                url = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            )
        )
        coEvery { characterkRepository.getCharacter() } returns Character(
            results = testCharacters,
            info =null
        )

        // Llama al método invoke() de characterUseCase
        val result = characterUseCase().take(1).toList()

        // Comprueba que el resultado sea el esperado
        assertEquals(result.first(), Resource.Loading<List<CharacterModel>>())
        assertEquals(result.last(), Resource.Success(testCharacters))
    }


}

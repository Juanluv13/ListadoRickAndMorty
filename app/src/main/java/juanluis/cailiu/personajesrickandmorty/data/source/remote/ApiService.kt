package juanluis.cailiu.personajesrickandmorty.data.source.remote

import juanluis.cailiu.personajesrickandmorty.data.source.remote.dto.Character
import juanluis.cailiu.personajesrickandmorty.util.Constants
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getCharacter(): Character
}
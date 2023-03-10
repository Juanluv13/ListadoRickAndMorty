package juanluis.cailiu.personajesrickandmorty.domain.model

import juanluis.cailiu.personajesrickandmorty.data.source.local.entity.CharacterEntity
import juanluis.cailiu.personajesrickandmorty.data.source.remote.dto.Result

data class CharacterModel(
    val created: String?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val name: String?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
)

fun Result.toDomain() = CharacterModel(
    created = created,
    gender = gender,
    id = id,
    image = image,
    name = name,
    species = species,
    status = status,
    type = type,
    url = url
)

fun CharacterEntity.toDomain() = CharacterModel(
    created = created,
    gender = gender,
    id = id,
    image = image,
    name = name,
    species = species,
    status = status,
    type = type,
    url = url
)

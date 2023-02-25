package juanluis.cailiu.personajesrickandmorty.presenter.viewmodel

import juanluis.cailiu.personajesrickandmorty.domain.model.CharacterModel

data class CharacterState(
    val ok: List<CharacterModel> = emptyList(),
    val fail: String = "",
    val loading: Boolean = false
)

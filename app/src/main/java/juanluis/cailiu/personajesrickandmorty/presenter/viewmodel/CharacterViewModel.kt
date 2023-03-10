package juanluis.cailiu.personajesrickandmorty.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import juanluis.cailiu.personajesrickandmorty.domain.usecase.CharacterUseCase
import juanluis.cailiu.personajesrickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import juanluis.cailiu.personajesrickandmorty.domain.model.CharacterModel
import juanluis.cailiu.personajesrickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val useCase: CharacterUseCase) : ViewModel() {
    private val _state = MutableStateFlow(CharacterState())
    val state: StateFlow<CharacterState> = _state

    init {
        getCharacters()
    }
    fun deleteCharacter(characterModel: CharacterModel) = viewModelScope.launch {
        CharacterRepository.deleteCharacter(characterModel)
    }
    private fun getCharacters() = viewModelScope.launch(Dispatchers.IO) {
        useCase().collect{
            when(it){
                is Resource.Error ->  _state.value = CharacterState(fail=it.message?:"error")
                is Resource.Loading -> _state.value = CharacterState(loading = true)
                is Resource.Success -> _state.value = CharacterState(ok=it.data ?: emptyList())
            }
        }
    }
}
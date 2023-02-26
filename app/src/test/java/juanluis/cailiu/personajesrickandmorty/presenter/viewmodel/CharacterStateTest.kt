package juanluis.cailiu.personajesrickandmorty.presenter.viewmodel

import juanluis.cailiu.personajesrickandmorty.domain.model.CharacterModel
import org.junit.Assert.*
import org.junit.Test


class CharacterStateTest {
    @Test
    fun `empty state should have empty ok list, empty fail string, and loading false`() {
        val state = CharacterState()
        assertEquals(emptyList<CharacterModel>(), state.ok)
        assertEquals("", state.fail)
        assertFalse(state.loading)
    }

    @Test
    fun `state with ok list should have non-empty ok list, empty fail string, and loading false`() {
        val character1 = CharacterModel("sad", "Test 1", 1, "2023-02-26", "https://test1.com/image1.jpg", "Female","dead","","https://test2.com/image2.jpg")
        val character2 = CharacterModel("sad", "Test 2", 1, "2023-02-26", "https://test2.com/image2.jpg", "Male","alive","","https://test2.com/image2.jpg")
        val okList = listOf(character1, character2)
        val state = CharacterState(ok = okList)
        assertEquals(okList, state.ok)
        assertEquals("", state.fail)
        assertFalse(state.loading)
    }

    @Test
    fun `state with fail string should have empty ok list, non-empty fail string, and loading false`() {
        val state = CharacterState(fail = "Test error message")
        assertEquals(emptyList<CharacterModel>(), state.ok)
        assertEquals("Test error message", state.fail)
        assertFalse(state.loading)
    }

    @Test
    fun `state with loading should have empty ok list, empty fail string, and loading true`() {
        val state = CharacterState(loading = true)
        assertEquals(emptyList<CharacterModel>(), state.ok)
        assertEquals("", state.fail)
        assertTrue(state.loading)
    }
}


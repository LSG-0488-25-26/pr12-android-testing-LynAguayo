package com.example.android_studio_test_exercice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.state.ToggleableState
import com.example.android_studio_test_exercice.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun `valors inicials correctes`() {
        assertEquals(true, viewModel.estatSwitch.value)
        assertEquals(false, viewModel.esVegetaria.value)
        assertEquals(false, viewModel.esVega.value)
        assertEquals(true, viewModel.esCarnivor.value)
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
        assertEquals("Messi", viewModel.selectedOption.value)
        assertEquals(0f, viewModel.sliderValue.value)
        assertEquals(false, viewModel.expanded.value)
        assertEquals("Opció A", viewModel.selectedItem.value)
        assertEquals("", viewModel.searchText.value)
        assertEquals(false, viewModel.showSnackbar.value)
        assertEquals(false, viewModel.toggleState.value)
    }

    @Test
    fun `toggleEstatSwitch canvia de true a false`() {
        assertEquals(true, viewModel.estatSwitch.value)
        viewModel.toggleEstatSwitch()
        assertEquals(false, viewModel.estatSwitch.value)
    }

    @Test
    fun `toggleEstatSwitch dues vegades torna a l'estat inicial`() {
        viewModel.toggleEstatSwitch()
        viewModel.toggleEstatSwitch()
        assertEquals(true, viewModel.estatSwitch.value)
    }

    @Test
    fun `toggleEsCarnivor canvia de true a false`() {
        assertEquals(true, viewModel.esCarnivor.value)
        viewModel.toggleEsCarnivor()
        assertEquals(false, viewModel.esCarnivor.value)
    }

    @Test
    fun `toggleEsCarnivor dues vegades torna a true`() {
        viewModel.toggleEsCarnivor()
        viewModel.toggleEsCarnivor()
        assertEquals(true, viewModel.esCarnivor.value)
    }

    @Test
    fun `toggleEsVegetaria canvia de false a true`() {
        assertEquals(false, viewModel.esVegetaria.value)
        viewModel.toggleEsVegetaria()
        assertEquals(true, viewModel.esVegetaria.value)
    }

    @Test
    fun `toggleEsVegetaria dues vegades torna a false`() {
        viewModel.toggleEsVegetaria()
        viewModel.toggleEsVegetaria()
        assertEquals(false, viewModel.esVegetaria.value)
    }

    @Test
    fun `toggleEsVega canvia de false a true`() {
        assertEquals(false, viewModel.esVega.value)
        viewModel.toggleEsVega()
        assertEquals(true, viewModel.esVega.value)
    }

    @Test
    fun `toggleEsVega dues vegades torna a false`() {
        viewModel.toggleEsVega()
        viewModel.toggleEsVega()
        assertEquals(false, viewModel.esVega.value)
    }

    @Test
    fun `toggleTriStateStatus seguix la sequencia Off-Indeterminate-On-Off`() {
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)

        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Indeterminate, viewModel.triStateStatus.value)

        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.On, viewModel.triStateStatus.value)

        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
    }

    @Test
    fun `setSelectedOption actualitza el jugador seleccionat`() {
        viewModel.setSelectedOption("Lamine Yamal")
        assertEquals("Lamine Yamal", viewModel.selectedOption.value)

        viewModel.setSelectedOption("Raphina")
        assertEquals("Raphina", viewModel.selectedOption.value)
    }

    @Test
    fun `setSliderValue actualitza el valor del slider`() {
        viewModel.setSliderValue(42f)
        assertEquals(42f, viewModel.sliderValue.value)

        viewModel.setSliderValue(100f)
        assertEquals(100f, viewModel.sliderValue.value)

        viewModel.setSliderValue(0f)
        assertEquals(0f, viewModel.sliderValue.value)
    }

    @Test
    fun `setExpanded canvia l'estat del DropdownMenu`() {
        assertEquals(false, viewModel.expanded.value)

        viewModel.setExpanded(true)
        assertEquals(true, viewModel.expanded.value)

        viewModel.setExpanded(false)
        assertEquals(false, viewModel.expanded.value)
    }

    @Test
    fun `setSelectedItem actualitza l'item del DropdownMenu`() {
        viewModel.setSelectedItem("Opció B")
        assertEquals("Opció B", viewModel.selectedItem.value)

        viewModel.setSelectedItem("Opció C")
        assertEquals("Opció C", viewModel.selectedItem.value)
    }

    @Test
    fun `setSearchText actualitza el text de cerca`() {
        viewModel.setSearchText("hola")
        assertEquals("hola", viewModel.searchText.value)

        viewModel.setSearchText("")
        assertEquals("", viewModel.searchText.value)
    }

    @Test
    fun `performSearch mostra el snackbar`() {
        assertFalse(viewModel.showSnackbar.value!!)
        viewModel.performSearch()
        assertTrue(viewModel.showSnackbar.value!!)
    }

    @Test
    fun `hideSnackbar amaga el snackbar`() {
        viewModel.performSearch()
        assertTrue(viewModel.showSnackbar.value!!)
        viewModel.hideSnackbar()
        assertFalse(viewModel.showSnackbar.value!!)
    }

    @Test
    fun `toggle canvia l'estat del boto Activat-Desactivat`() {
        assertEquals(false, viewModel.toggleState.value)

        viewModel.toggle()
        assertEquals(true, viewModel.toggleState.value)

        viewModel.toggle()
        assertEquals(false, viewModel.toggleState.value)
    }
}

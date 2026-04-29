package com.example.android_studio_test_exercice

import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodes
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.android_studio_test_exercice.view.MainView
import com.example.android_studio_test_exercice.viewmodel.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Instrumental UI Tests de la MainView.
 *
 * Cobreix tots els composables que es renderitzen a la MainView:
 * Switch, Checkboxes (Carnívor, Vegetarià, Vega), TriStateCheckbox,
 * RadioButtons (Pilota d'Or), Slider, DropdownMenu, OutlinedTextField,
 * Botó Buscar, missatge del snackbar i botó Activat/Desactivat.
 */
class MainViewUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
        composeTestRule.setContent {
            MainView(myViewModel = viewModel)
        }
    }

    // -------------------- Textos estàtics --------------------

    @Test
    fun mainView_mostraTextos_estatics() {
        composeTestRule.onNodeWithText("Activar Wi-Fi: ").assertIsDisplayed()
        composeTestRule.onNodeWithText("Opcions de menú:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Carnívor/a").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vegetarià/na").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vegà/na").assertIsDisplayed()
        composeTestRule.onNodeWithText("TriState").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pilota d'Or:").assertIsDisplayed()
    }

    // -------------------- Switch Wi-Fi --------------------

    @Test
    fun switchWifi_estatInicialOn_iCanviaAlClicar() {
        // Estat inicial: ON (segons MainViewModel)
        composeTestRule.runOnIdle {
            assert(viewModel.estatSwitch.value == true)
        }

        composeTestRule.onNodeWithText("Activar Wi-Fi: ").assertIsDisplayed()

        // El Switch és el primer node toggleable+clickable de la pantalla
        // (apareix abans dels Checkbox a la jerarquia visual)
        composeTestRule
            .onAllNodes(hasClickAction().and(isToggleable()))[0]
            .performClick()

        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            assert(viewModel.estatSwitch.value == false)
        }
    }

    // -------------------- Checkboxes Opcions de menú --------------------

    @Test
    fun checkbox_carnivor_estatInicialMarcatIDeshabilitat() {
        composeTestRule.runOnIdle {
            assert(viewModel.esCarnivor.value == true)
        }
    }

    @Test
    fun checkbox_vegetaria_canviaLEstat() {
        composeTestRule.runOnIdle {
            assert(viewModel.esVegetaria.value == false)
        }
        viewModel.toggleEsVegetaria()
        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            assert(viewModel.esVegetaria.value == true)
        }
    }

    @Test
    fun checkbox_vega_canviaLEstat() {
        composeTestRule.runOnIdle {
            assert(viewModel.esVega.value == false)
        }
        viewModel.toggleEsVega()
        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            assert(viewModel.esVega.value == true)
        }
    }

    // -------------------- TriStateCheckbox --------------------

    @Test
    fun triState_canviaSeguintLaSequencia() {
        composeTestRule.runOnIdle {
            assert(viewModel.triStateStatus.value == ToggleableState.Off)
        }
        viewModel.toggleTriStateStatus()
        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            assert(viewModel.triStateStatus.value == ToggleableState.Indeterminate)
        }
        viewModel.toggleTriStateStatus()
        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            assert(viewModel.triStateStatus.value == ToggleableState.On)
        }
        viewModel.toggleTriStateStatus()
        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            assert(viewModel.triStateStatus.value == ToggleableState.Off)
        }
    }

    // -------------------- RadioButtons Pilota d'Or --------------------

    @Test
    fun radioButton_vinicius_esVisible() {
        composeTestRule.onNodeWithText("Vinicius").assertIsDisplayed()
    }

    @Test
    fun radioButton_lamineYamal_seleccionableIActualitzaViewModel() {
        composeTestRule.onNodeWithText("Lamine Yamal").assertIsDisplayed()
        composeTestRule.onNodeWithText("Lamine Yamal").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            assert(viewModel.selectedOption.value == "Lamine Yamal")
        }
    }

    @Test
    fun radioButton_raphina_seleccionableIActualitzaViewModel() {
        composeTestRule.onNodeWithText("Raphina").assertIsDisplayed()
        composeTestRule.onNodeWithText("Raphina").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            assert(viewModel.selectedOption.value == "Raphina")
        }
    }

    // -------------------- Slider Volum --------------------

    @Test
    fun slider_mostraValorInicial() {
        composeTestRule.onNodeWithText("Volum: 0%").assertIsDisplayed()
    }

    @Test
    fun slider_actualitzaTextAlCanviarElValor() {
        viewModel.setSliderValue(50f)
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Volum: 50%").assertIsDisplayed()
    }

    // -------------------- DropdownMenu --------------------

    @Test
    fun dropdown_mostraSeleccioInicial() {
        composeTestRule.onNodeWithText("Opció A").assertIsDisplayed()
    }

    @Test
    fun dropdown_obreIPermetSeleccionarOpcio() {
        composeTestRule.onNodeWithText("Opció A").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.runOnIdle {
            assert(viewModel.expanded.value == true)
        }

        composeTestRule.onNodeWithText("Opció B").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.runOnIdle {
            assert(viewModel.selectedItem.value == "Opció B")
            assert(viewModel.expanded.value == false)
        }
    }

    // -------------------- OutlinedTextField i botó Buscar --------------------

    @Test
    fun textField_acceptaTextEnviat() {
        composeTestRule.onNodeWithText("Buscar...").performTextInput("hola")
        composeTestRule.waitForIdle()
        composeTestRule.runOnIdle {
            assert(viewModel.searchText.value == "hola")
        }
    }

    @Test
    fun botoBuscar_mostraMissatgeAlClicar() {
        // Inicialment no es mostra el text del snackbar
        composeTestRule.onAllNodesWithText("Acció completada!").assertCountEquals(0)

        composeTestRule.onNodeWithText("Buscar").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.runOnIdle {
            assert(viewModel.showSnackbar.value == true)
        }
        composeTestRule.onNodeWithText("Acció completada!").assertIsDisplayed()
    }

    // -------------------- Botó Activat / Desactivat --------------------

    @Test
    fun botoToggle_mostraDesactivatPerDefecte() {
        composeTestRule.onNodeWithText("Desactivat").assertIsDisplayed()
    }

    @Test
    fun botoToggle_canviaAActivatAlClicar() {
        composeTestRule.onNodeWithText("Desactivat").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.runOnIdle {
            assert(viewModel.toggleState.value == true)
        }
        composeTestRule.onNodeWithText("Activat").assertIsDisplayed()
    }

    @Test
    fun botoToggle_dosClicsTornenADesactivat() {
        composeTestRule.onNodeWithText("Desactivat").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Activat").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.runOnIdle {
            assert(viewModel.toggleState.value == false)
        }
        composeTestRule.onNodeWithText("Desactivat").assertIsDisplayed()
    }
}

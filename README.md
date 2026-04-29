# PR12 - Android Testing: Unit Testing UT + Instrumental Testing UI

## Patró MVVM

El `MainViewModel` exposa cada propietat com a `LiveData<T>` (només lectura) i manté el `MutableLiveData<T>` privat. La `MainView` es subscriu reactivament a aquests valors mitjançant `observeAsState` i delega tota acció de l'usuari en mètodes del ViewModel (`toggleEstatSwitch()`, `setSelectedOption(...)`, `performSearch()`, etc.).

## Procés de testing

### 1. Unit Tests — `MainViewModelTest.kt`

Es testegen tots els mètodes del `MainViewModel` sobre la JVM (carpeta `app/src/test`).

S'utilitza `InstantTaskExecutorRule` (de `androidx.arch.core:core-testing`) per a que `LiveData` publiqui els valors de manera síncrona dins els tests.

Per executar:

```bash
./gradlew testDebugUnitTest
```

Tests inclosos:

- Valors inicials de tots els `LiveData`.
- `toggleEstatSwitch`, `toggleEsCarnivor`, `toggleEsVegetaria`, `toggleEsVega` (canvi i retorn a l'estat inicial).
- `toggleTriStateStatus` (cicle Off → Indeterminate → On → Off).
- `setSelectedOption`, `setSliderValue`, `setExpanded`, `setSelectedItem`, `setSearchText`.
- `performSearch` / `hideSnackbar`.
- `toggle` (canvi del botó Activat/Desactivat).

### 2. UI Tests instrumentats — `MainViewUITest.kt`

Es testegen tots els Composables de la `MainView` amb un emulador o dispositiu físic (`app/src/androidTest`).

Es fa servir `createComposeRule()` per inflar la `MainView` amb un `MainViewModel` real i verificar:

- Visibilitat dels textos estàtics ("Activar Wi-Fi:", "Opcions de menú:", etc.).
- Comportament del `Switch` Wi-Fi.
- `Checkbox` carnívor (deshabilitat), vegetariana, vegana.
- `TriStateCheckbox` i la seva seqüència d'estats.
- `RadioButton` de la Pilota d'Or (Vinicius deshabilitat, Lamine Yamal i Raphina seleccionables).
- `Slider` de volum (text "Volum: x%" actualitzat reactivament).
- `DropdownMenu` amb obertura, selecció i tancament.
- `OutlinedTextField` "Buscar..." rebent text.
- Botó "Buscar" que mostra el missatge "Acció completada!".
- Botó toggle Activat/Desactivat (canvi recíproc).

Per executar:

```bash
./gradlew connectedDebugAndroidTest
```

## Dependències afegides al `build.gradle.kts`

```kotlin
// Unit tests
testImplementation(libs.junit)
testImplementation(libs.androidx.core.testing)   // InstantTaskExecutorRule
testImplementation(libs.mockito.core)
testImplementation(libs.mockito.kotlin)

// Instrumented / UI tests
androidTestImplementation(libs.androidx.junit)
androidTestImplementation(libs.androidx.espresso.core)
androidTestImplementation(platform(libs.androidx.compose.bom))
androidTestImplementation(libs.androidx.ui.test.junit4)
debugImplementation(libs.androidx.ui.tooling)
debugImplementation(libs.androidx.ui.test.manifest)
```

## Captures dels tests

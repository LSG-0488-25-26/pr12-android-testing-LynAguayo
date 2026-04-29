# PR12 - Android Testing: Unit Testing UT + Instrumental Testing UI

El `MainViewModel` exposa cada propietat com a `LiveData<T>` (només lectura) i manté el `MutableLiveData<T>` privat. La `MainView` es subscriu reactivament a aquests valors mitjançant `observeAsState` i delega tota acció de l'usuari en mètodes del ViewModel (`toggleEstatSwitch()`, `setSelectedOption(...)`, `performSearch()`, etc.).

## Procés de testing

### 1. Unit Tests - `MainViewModelTest.kt`

Es testegen tots els mètodes del `MainViewModel` sobre la JVM (carpeta `app/src/test`).

S'utilitza `InstantTaskExecutorRule` (de `androidx.arch.core:core-testing`) per a que `LiveData` publiqui els valors de manera síncrona dins els tests.

Tests inclosos:

- Valors inicials de tots els `LiveData`.
- `toggleEstatSwitch`, `toggleEsCarnivor`, `toggleEsVegetaria`, `toggleEsVega` (canvi i retorn a l'estat inicial).
- `toggleTriStateStatus` (cicle Off -> Indeterminate -> On -> Off).
- `setSelectedOption`, `setSliderValue`, `setExpanded`, `setSelectedItem`, `setSearchText`.
- `performSearch` / `hideSnackbar`.
- `toggle` (canvi del botó Activat/Desactivat).

### 2. UI Tests instrumentats - `MainViewUITest.kt`

Es testegen tots els Composables de la `MainView` amb un emulador (`app/src/androidTest`).

Es fa servir `createComposeRule()` per inflar la `MainView` amb un `MainViewModel` real i verificar:

- Visibilitat dels textos ("Activar Wi-Fi:", "Opcions de menú:", etc.).
- Comportament del `Switch` Wi-Fi.
- `Checkbox` carnívor (deshabilitat), vegetariana, vegana.
- `TriStateCheckbox` i la seva seqüència d'estats.
- `RadioButton` de la Pilota d'Or (Vinicius deshabilitat, Lamine Yamal i Raphina seleccionables).
- `Slider` de volum (text "Volum: x%" actualitzat reactivament).
- `DropdownMenu` amb obertura, selecció i tancament.
- `OutlinedTextField` "Buscar..." rebent text.
- Botó "Buscar" que mostra el missatge "Acció completada!".
- Botó toggle Activat/Desactivat (canvi recíproc).

## Captures dels tests

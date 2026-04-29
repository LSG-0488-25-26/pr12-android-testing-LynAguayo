package com.example.android_studio_test_exercice.viewmodel

import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel {

    private val _estatSwitch: MutableLiveData<Boolean>
    val estatSwitch: LiveData<Boolean>

    private val _esVegetaria: MutableLiveData<Boolean>
    val esVegetaria: LiveData<Boolean>

    private val _esVega: MutableLiveData<Boolean>
    val esVega: LiveData<Boolean>

    private val _esCarnivor: MutableLiveData<Boolean>
    val esCarnivor: LiveData<Boolean>

    private val _triStateStatus: MutableLiveData<ToggleableState>
    val triStateStatus: LiveData<ToggleableState>

    private val _selectedOption: MutableLiveData<String>
    val selectedOption: LiveData<String>

    private val _sliderValue: MutableLiveData<Float>
    val sliderValue: LiveData<Float>

    private val _expanded: MutableLiveData<Boolean>
    val expanded: LiveData<Boolean>

    private val _selectedItem: MutableLiveData<String>
    val selectedItem: LiveData<String>

    private val _searchText: MutableLiveData<String>
    val searchText: LiveData<String>

    private val _showSnackbar: MutableLiveData<Boolean>
    val showSnackbar: LiveData<Boolean>

    private val _toggleState: MutableLiveData<Boolean>
    val toggleState: LiveData<Boolean>

    constructor() : super() {
        this._estatSwitch = MutableLiveData<Boolean>(true)
        this.estatSwitch = this._estatSwitch

        this._esVegetaria = MutableLiveData<Boolean>(false)
        this.esVegetaria = this._esVegetaria

        this._esVega = MutableLiveData<Boolean>(false)
        this.esVega = this._esVega

        this._esCarnivor = MutableLiveData<Boolean>(true)
        this.esCarnivor = this._esCarnivor

        this._triStateStatus = MutableLiveData<ToggleableState>(ToggleableState.Off)
        this.triStateStatus = this._triStateStatus

        this._selectedOption = MutableLiveData<String>("Messi")
        this.selectedOption = this._selectedOption

        this._sliderValue = MutableLiveData<Float>(0f)
        this.sliderValue = this._sliderValue

        this._expanded = MutableLiveData<Boolean>(false)
        this.expanded = this._expanded

        this._selectedItem = MutableLiveData<String>("Opció A")
        this.selectedItem = this._selectedItem

        this._searchText = MutableLiveData<String>("")
        this.searchText = this._searchText

        this._showSnackbar = MutableLiveData<Boolean>(false)
        this.showSnackbar = this._showSnackbar

        this._toggleState = MutableLiveData<Boolean>(false)
        this.toggleState = this._toggleState
    }

    fun toggleEstatSwitch() {
        this._estatSwitch.value = !(this._estatSwitch.value)!!
    }

    fun toggleEsCarnivor() {
        this._esCarnivor.value = !(this._esCarnivor.value)!!
    }

    fun toggleEsVegetaria() {
        this._esVegetaria.value = !(this._esVegetaria.value)!!
    }

    fun toggleEsVega() {
        this._esVega.value = !(this._esVega.value)!!
    }

    fun toggleTriStateStatus() {
        when (this._triStateStatus.value) {
            ToggleableState.On -> setTriStateStatus(ToggleableState.Off)
            ToggleableState.Off -> setTriStateStatus(ToggleableState.Indeterminate)
            ToggleableState.Indeterminate -> setTriStateStatus(ToggleableState.On)
            null -> setTriStateStatus(ToggleableState.On)
        }
    }

    private fun setTriStateStatus(triState: ToggleableState) {
        this._triStateStatus.value = triState
    }

    fun setSelectedOption(option: String) {
        this._selectedOption.value = option
    }

    fun setSliderValue(value: Float) {
        this._sliderValue.value = value
    }

    fun setExpanded(value: Boolean) {
        this._expanded.value = value
    }

    fun setSelectedItem(item: String) {
        this._selectedItem.value = item
    }

    fun setSearchText(text: String) {
        this._searchText.value = text
    }

    fun performSearch() {
        this._showSnackbar.value = true
    }

    fun hideSnackbar() {
        this._showSnackbar.value = false
    }

    fun toggle() {
        this._toggleState.value = !(this._toggleState.value)!!
    }
}

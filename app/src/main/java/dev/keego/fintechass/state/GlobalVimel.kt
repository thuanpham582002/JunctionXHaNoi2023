package dev.keego.fintechass.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GlobalVimel @Inject constructor() : VimelStateHolder<GlobalVimel.GlobalState>(GlobalState()) {
//    val ads: AdCenter = AdCenter.getInstance()

    data class GlobalState(
        var count: Int = 0,
    ) : State

    companion object {

        @Composable
        fun getInstance(): GlobalVimel {
            val composeView = LocalView.current
            val storeOwner = composeView.findViewTreeViewModelStoreOwner()

            if (storeOwner != null) {
                return hiltViewModel(storeOwner)
            }
            return hiltViewModel()
        }
    }
}

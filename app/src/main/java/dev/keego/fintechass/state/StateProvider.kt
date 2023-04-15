package dev.keego.fintechass.state

import android.annotation.SuppressLint
import androidx.compose.runtime.compositionLocalOf

class PreviewableState : State

val LocalState = compositionLocalOf<VimelStateHolder<out State>> {
    VimelStateHolder(PreviewableState())
}

@SuppressLint("CompositionLocalNaming")
val GlobalState = compositionLocalOf {
    VimelStateHolder(GlobalVimel.GlobalState())
}

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}

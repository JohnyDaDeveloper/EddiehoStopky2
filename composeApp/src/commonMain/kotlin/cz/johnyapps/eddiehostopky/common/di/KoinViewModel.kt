package cz.johnyapps.eddiehostopky.common.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.mp.KoinPlatform

@Composable
inline fun <reified VM : ViewModel> koinViewModel(
    crossinline constructor: () -> VM
): VM {
    return viewModel { constructor() }
}

@Composable
inline fun <reified VM : ViewModel, reified P1 : Any> koinViewModel(
    crossinline constructor: (P1) -> VM
): VM {
    val koin = KoinPlatform.getKoin()
    val parameter1 = koin.get<P1>()
    return viewModel { constructor(parameter1) }
}

@Composable
inline fun <reified VM : ViewModel, reified P1 : Any, reified P2 : Any> koinViewModel(
    crossinline constructor: (P1, P2) -> VM
): VM {
    val koin = KoinPlatform.getKoin()
    val parameter1 = koin.get<P1>()
    val parameter2 = koin.get<P2>()
    return viewModel { constructor(parameter1, parameter2) }
}

@Composable
inline fun <reified VM : ViewModel, reified P1 : Any, reified P2 : Any, reified P3 : Any> koinViewModel(
    crossinline constructor: (P1, P2, P3) -> VM
): VM {
    val koin = KoinPlatform.getKoin()
    val parameter1 = koin.get<P1>()
    val parameter2 = koin.get<P2>()
    val parameter3 = koin.get<P3>()
    return viewModel { constructor(parameter1, parameter2, parameter3) }
}

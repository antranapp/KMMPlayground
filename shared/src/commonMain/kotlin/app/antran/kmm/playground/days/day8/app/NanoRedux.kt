package app.antran.kmm.playground.days.day8.app

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ReduxState
interface ReduxAction
interface ReduxEffect

interface Store<S : ReduxState, A : ReduxAction, E : ReduxEffect> {
    fun observeState(): StateFlow<S>
    fun observeSideEffect(): Flow<E>
    fun dispatch(action: A)
}
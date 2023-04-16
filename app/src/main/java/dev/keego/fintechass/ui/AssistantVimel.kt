package dev.keego.fintechass.ui

import dev.keego.fintechass.FakeData
import dev.keego.fintechass.Payment
import dev.keego.fintechass.setup.room.User
import dev.keego.fintechass.setup.room.listUserExample
import dev.keego.fintechass.state.State
import dev.keego.fintechass.state.VimelStateHolder
import javax.inject.Inject

class AssistantVimel @Inject constructor() : VimelStateHolder<AssistantVimel.AssistantVMState>(
    AssistantVMState()
) {
    data class AssistantVMState(
        val assistantState: AssistantState = AssistantState.SUGGEST,
        val receivers: List<User> = emptyList(),
        val amount: String = "",
        val payment: Payment? = null,
        val message: String = "",
    ) : State

    fun submit() {

    }

    fun moveNext() {
        with(state.value) {
            if (receivers.isEmpty()) {
                update {
                    it.copy(
                        assistantState = AssistantState.MEMBERS,
                        receivers = listUserExample
                    )
                }
            } else if (amount.isEmpty()) {
                update { it.copy(assistantState = AssistantState.AMOUNT, amount = "100.000đ") }
            } else if (payment == null) {
                update {
                    it.copy(
                        assistantState = AssistantState.ACCOUNT,
                        payment = FakeData.payments[0]
                    )
                }
            } else if (message.isEmpty()) {
                update {
                    it.copy(
                        assistantState = AssistantState.MESSAGE,
                        message = "Quang CK 5 cu tien hoc"
                    )
                }
            } else {
                update { it.copy(assistantState = AssistantState.CONFIRM) }
            }
        }
    }

    fun stopFlow() {
        with(state.value) {
            update { it.copy(assistantState = AssistantState.NULL) }
        }
    }

    fun setReceiver(users: List<User>) {
        with(state.value) {
            update { it.copy(receivers = users) }
        }
    }

    fun setAmount(amount: String) {
        with(state.value) {
            update { it.copy(amount = amount) }
        }
    }

    fun setPayment(payment: Payment) {
        with(state.value) {
            update { it.copy(payment = payment) }
        }
    }

    fun setMessage(message: String) {
        with(state.value) {
            update { it.copy(message = message) }
        }
    }
}
package dev.keego.fintechass.ui

import dev.keego.fintechass.Payment
import dev.keego.fintechass.screen.chat.jsonobject.Entity
import dev.keego.fintechass.setup.room.User
import dev.keego.fintechass.setup.room.listUserExample
import dev.keego.fintechass.state.State
import dev.keego.fintechass.state.VimelStateHolder
import javax.inject.Inject

class   AssistantVimel @Inject constructor() : VimelStateHolder<AssistantVimel.AssistantVMState>(
    AssistantVMState()
) {
    data class AssistantVMState(
        val assistantState: AssistantState = AssistantState.NULL,
        val accountNumber: String = "",
        val amount: String = "",
        val payment: Payment? = null,
        val message: String = "",
    ) : State

    fun submit() {

    }

    fun clear() {
        update { AssistantVMState() }
    }

    fun setEntity(entity: Entity) {
        when (entity.entity) {
            "amount" -> {
                update { it.copy(amount = entity.value, assistantState = AssistantState.SUGGEST) }
            }

            "bank" -> {
                update {
                    it.copy(
                        payment = Payment.find(entity.value)
                    )
                }
            }

            "account_number" -> {
                update {
                    it.copy(
                        accountNumber = entity.value
                    )
                }
            }

            else -> {}
        }
    }

    fun moveNext() {
        with(state.value) {
           if (amount.isEmpty()) {
                update { it.copy(assistantState = AssistantState.AMOUNT, amount = "100.000đ") }
            } else if (payment == null) {
                update {
                    it.copy(
                        assistantState = AssistantState.ACCOUNT,
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
}
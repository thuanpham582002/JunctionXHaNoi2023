package dev.keego.fintechass.screen.chat.jsonobject

import kotlinx.serialization.Serializable

@Serializable
data class InputData(
    val text: String,
    val intent: Intent,
    val entities: List<Entity>,
    val text_tokens: List<List<Int>>,
    val intent_ranking: List<Intent>,
    val response_selector: ResponseSelector
)

@Serializable
data class Intent(
    val name: String,
    val confidence: Double
)


@Serializable
data class Entity(
    val entity: String,
    val start: Int,
    val end: Int,
    val confidence_entity: Double,
    val value: String,
    val extractor: String,
    val processors: List<String>? = null
)

@Serializable
data class ResponseSelector(
    val all_retrieval_intents: List<String>,
    val default: DefaultResponse
)

@Serializable
data class DefaultResponse(
    val response: Response,
    val ranking: List<String>
)

@Serializable
data class Response(
    val responses: String?,
    val confidence: Double,
    val intent_response_key: String?,
    val utter_action: String
)
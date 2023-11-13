package albek.petprojects.touristapp.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.formatDateTime(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    val localDateTime = LocalDateTime.parse(this, formatter)
    val date = localDateTime.toLocalDate()
    return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
}
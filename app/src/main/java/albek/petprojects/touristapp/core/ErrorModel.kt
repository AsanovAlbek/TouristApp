package albek.petprojects.touristapp.core

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ErrorModel(
    @StringRes val messageId: Int = 0,
    @DrawableRes val image: Int = 0
)
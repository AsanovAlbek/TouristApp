package albek.petprojects.touristapp.core.composable

import albek.petprojects.touristapp.R
import android.content.Context
import androidx.compose.runtime.Composable
import com.squareup.picasso3.Picasso
import com.squareup.picasso3.compose.rememberPainter

@Composable
fun picassoPainter(context: Context, imageUrl: String) =
    Picasso.Builder(context).build().run {
        rememberPainter(key = imageUrl) { picasso ->
            picasso.load(imageUrl)
        }
    }
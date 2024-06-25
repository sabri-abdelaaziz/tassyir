package com.wagdev.tassyir.note_feature.presentation.notes.components
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.wagdev.tassyir.note_feature.domain.model.Note
import kotlin.io.path.Path

@Composable
fun NoteItem(
    note: Note,
    cornerRadius: Dp =10.dp,
    modifier: Modifier = Modifier,
    cutCornerRadius: Dp=30.dp,
    onDeleteClick:()->Unit)
{
    Box(modifier = modifier){
        androidx.compose.foundation.Canvas(modifier = modifier.matchParentSize()) {

            val clipPath= androidx.compose.ui.graphics.Path().apply {
                lineTo(size.width-cutCornerRadius.toPx(),0f)
                lineTo(size.width,cutCornerRadius.toPx())
                lineTo(size.width,size.height)
                lineTo(0f,size.height)
                close()
            }
            clipPath(clipPath){
                drawRoundRect(
                    color= Color(note.color),
                    size=size,
                    cornerRadius=CornerRadius(cornerRadius.toPx()),

                    )
                drawRoundRect(
                    color= Color((ColorUtils.blendARGB(note.color,0x000000,0.2f))),
                    size= Size(cutCornerRadius.toPx()+100f,cutCornerRadius.toPx()+100f),
                    cornerRadius=CornerRadius(cornerRadius.toPx()),

                    )

            }


        }

        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(note.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text(note.content,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis)
        }
        IconButton(onClick = onDeleteClick,modifier=Modifier.align(Alignment.BottomEnd)) {
            Icon(Icons.Default.Delete, contentDescription = null)
        }
    }
    
}
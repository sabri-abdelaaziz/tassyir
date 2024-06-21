package com.wagdev.tassyir.note_feature.presentation.Detail


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(
    modifier:Modifier=Modifier,
    noteId:Long,
    assistedFactory: DetailedAssistedFactory ,
    navigatUp:()->Unit
) {
    val viewModel= viewModel(modelClass = DetailViewModel::class.java,
        factory = DetailedViewModelFactory(
            noteId=noteId,
            assistedFactory=assistedFactory
        ))
    val state=viewModel.state
    DetailScreen(
        modifier=Modifier,
        isUpdatingNote = state.isUpdatingNote,
        title=state.title,
        content=state.content,
        isBookMark = false,
        onBookMarkChange = viewModel::onBookMarkChange,
        isFormNoteBlank =state.isUpdatingNote,
        onContentChange = viewModel::onContentChange,
        onTitleChange = viewModel::onTileChange,
        onBtnClick ={
            viewModel.addOrUpdateNote()
            navigatUp()
        },
        onNavigate = navigatUp
    )





}



@Composable
private fun DetailScreen(
    modifier: Modifier=Modifier,
    isUpdatingNote:Boolean,
    title:String,
    content:String,
    isBookMark:Boolean,
    onBookMarkChange:(Boolean)->Unit,
    isFormNoteBlank:Boolean,
    onTitleChange:(String)->Unit,
    onContentChange:(String)->Unit,
    onBtnClick:()->Unit,
    onNavigate:()->Unit
) {
    Column(modifier=Modifier.fillMaxSize()){
        TopSection(
            title = title,
            isBookMark =isBookMark ,
            onTitleChange =onTitleChange ,
            onNavigate=onNavigate,
            onBookMarkChange = onBookMarkChange)
        Spacer(modifier = Modifier.size(12.dp))
        AnimatedVisibility(isFormNoteBlank) {
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(onClick = onBtnClick) {
                    val icon=if (isUpdatingNote) Icons.Default.Update else Icons.Default.Check
                    Icon(imageVector =icon ,contentDescription = null)
                }

            }
        }
        Spacer(modifier = Modifier.size(12.dp))
        NoteTextField(modifier = modifier.weight(1f),
            value = title,
            onValueChange =onTitleChange ,
            label = "Title")
        NoteTextField(modifier = modifier.weight(1f),
            value = content ,
            onValueChange =onContentChange ,
            label = "Content")

    }

}

@Composable
fun TopSection(
    title:String,
    isBookMark: Boolean,
    modifier:Modifier=Modifier,
    onTitleChange:(String)->Unit,
    onBookMarkChange:(Boolean)->Unit,
    onNavigate:() -> Unit
) {

    Row(modifier=modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription =null )
        }

        NoteTextField(modifier = modifier.weight(1f),
            value = title,
            onValueChange =onTitleChange ,
            label = "Title")
        IconButton(onClick = { onBookMarkChange(!isBookMark) }) {
            val icon =if(isBookMark) Icons.Default.BookmarkRemove else Icons.Outlined.BookmarkAdd
            Icon(icon, contentDescription = null)
        }
    }


}

@Composable
fun NoteTextField(
    modifier: Modifier,
    value:String,
    onValueChange:(String)->Unit,
    label:String,
    labelAlign:TextAlign?=null
) {
    OutlinedTextField(value = value,
        onValueChange =onValueChange,
        modifier=modifier,
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(text = "Insert $label",
                textAlign = labelAlign,
                modifier=Modifier.fillMaxWidth())
        }
    )

}
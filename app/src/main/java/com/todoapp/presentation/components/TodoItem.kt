package com.todoapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.todoapp.data.TodoEntity
import com.todoapp.data.addDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TodoItem(
    todo: TodoEntity,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (todo.done) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onError,
        animationSpec = tween(200), label = ""
    )

    Box(
        modifier = Modifier.fillMaxWidth().animateItemPlacement(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(color)
                .clickable {
                    onClick()
                }
                .padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
               Row {
                   AnimatedVisibility(visible = todo.done,
                       enter = scaleIn() + fadeIn(),
                       exit = scaleOut() + fadeOut()) {
                       Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = color)
                   }
               }
                Row {
                    AnimatedVisibility(visible = !todo.done,
                        enter = scaleIn() + fadeIn(),
                        exit = scaleOut() + fadeOut()) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = color)
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 8.dp,
                        end = 8.dp
                    )
            ) {
                Text(text = todo.title, style = MaterialTheme.typography.headlineSmall, color= Color.White)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = todo.content, style = MaterialTheme.typography.bodyMedium, color = Color(0xffebebeb))
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = todo.addDate, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onDelete,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp)) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = color)
            }
        }
    }
}
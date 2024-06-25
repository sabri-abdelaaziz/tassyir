package com.wagdev.tassyir.core.presentation.common_ui

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomItem(
    val title:String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasBadge:Boolean,
    val badgeNum:Int=0,
    var isSelected:Boolean=false
    )

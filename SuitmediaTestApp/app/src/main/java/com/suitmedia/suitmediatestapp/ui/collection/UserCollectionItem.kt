package com.suitmedia.suitmediatestapp.ui.collection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.suitmedia.suitmediatestapp.response.RegresInItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserCollectionItem(
    modifier: Modifier = Modifier,
    userCollectionData: RegresInItem,
    onItemClick: () -> Unit
) {
    Box(
        modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    ) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            elevation = 4.dp,
            onClick = onItemClick
        ) {
            Box(Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // User avatar
                    Image(
                        painter = rememberAsyncImagePainter("${userCollectionData.avatar}"),
                        contentDescription = "User avatar",
                        modifier = Modifier.size(64.dp).clip(shape = CircleShape),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Username and email
                    Column {
                        Text(
                            text = "${userCollectionData.firstName} ${userCollectionData.lastName}",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "${userCollectionData.email}",
                            style = TextStyle(
                                fontWeight = FontWeight.Light,
                                fontSize = 10.sp,
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
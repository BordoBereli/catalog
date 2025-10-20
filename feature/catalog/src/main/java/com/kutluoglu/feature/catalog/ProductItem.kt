package com.kutluoglu.feature.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kutluoglu.core.ui.toCurrencyString
import com.kutluoglu.domain.catalog.Product

/**
 * A composable function that displays a single product item in a card.
 *
 * @param product The [Product] object to display.
 * @param onProductClick A lambda to be invoked when the card is clicked.
 * @param modifier The modifier to be applied to the Card.
 */
@SuppressLint("DefaultLocale")
@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onProductClick: () -> Unit
) {
    Card(
        onClick = onProductClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .semantics {
                // Accessibility for the click action
                val clickLabel = "View details for ${product.title}"
                onClick(label = clickLabel, action = null)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Product thumbnail image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .crossfade(true)
                    // IMPROVEMENT 1: Add placeholder and error images
                    // Note: You'll need to add your own drawable resources for these.
                    // .placeholder(R.drawable.ic_placeholder)
                    // .error(R.drawable.ic_image_broken)
                    .build(),
                contentDescription = "Image of ${product.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // Column for text content
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Product Title
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Row for Brand and Price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.brand,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = product.price.toCurrencyString(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Product Description
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    val sampleProduct = Product(
        id = 1,
        title = "iPhone 9",
        price = 549.99,
        brand = "Apple",
        category = "smartphones",
        description = "An apple mobile which is nothing like apple. A very long description to test the text overflow ellipsis.",
        thumbnail = "https://i.dummyjson.com/data/products/1/thumbnail.jpg"
    )

    MaterialTheme {
        // Pass an empty lambda for the preview
        ProductItem(product = sampleProduct, onProductClick = {})
    }
}
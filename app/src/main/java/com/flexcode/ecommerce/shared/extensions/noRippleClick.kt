@file:Suppress("ktlint")

package com.flexcode.ecommerce.shared.extensions

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import com.flexcode.ecommerce.shared.utils.NoRippleInteractionSource

/**
 *
 * No ripple clickable modifier to remove the ripple effect on
 * clicking elements
 *
 * @author Felix-Kariuki
 * @since 1.0.0
 * @param onClick called once the element is clicked
 * @return clickable Modifier
 */
fun Modifier.noRippleClick(onClick: () -> Unit): Modifier {
    return this.clickable(
        interactionSource = NoRippleInteractionSource(),
        indication = null,
    ) { onClick() }
}

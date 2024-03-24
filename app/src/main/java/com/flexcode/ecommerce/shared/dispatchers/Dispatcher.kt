
package com.flexcode.ecommerce.shared.dispatchers

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class Dispatcher(val dispatcherProvider: DispatcherProvider)

enum class DispatcherProvider {
    IO, MAIN
}

package di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiSecret

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl
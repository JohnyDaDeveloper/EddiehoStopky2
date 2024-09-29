package cz.johnyapps.eddiehostopky.common.di

import org.koin.dsl.module

val commonModule = module {
    includes(commonPlatformModule)
}
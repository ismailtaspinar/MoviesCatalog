package com.canalperenozcan.movieassistant.di

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@Module
@InstallIn(FragmentComponent::class)
object PicassoModule {
    @Provides
    fun providePicasso(): Picasso = Picasso.get()
}
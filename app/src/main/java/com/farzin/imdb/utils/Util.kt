package com.farzin.imdb.utils

import android.content.Context
import com.farzin.imdb.R
import com.farzin.imdb.models.home.HomeGenre
import dagger.hilt.android.qualifiers.ApplicationContext

class Util(@ApplicationContext context: Context) {

    val movieGenres = listOf(
        HomeGenre(28, context.getString(R.string.action)),
        HomeGenre(12, context.getString(R.string.adventure)),
        HomeGenre(16, context.getString(R.string.animation)),
        HomeGenre(35, context.getString(R.string.comedy)),
        HomeGenre(80, context.getString(R.string.crime)),
        HomeGenre(99, context.getString(R.string.documentary)),
        HomeGenre(18, context.getString(R.string.drama)),
        HomeGenre(10751, context.getString(R.string.family)),
        HomeGenre(14, context.getString(R.string.fantasy)),
        HomeGenre(36, context.getString(R.string.history)),
        HomeGenre(27, context.getString(R.string.horror)),
        HomeGenre(10402, context.getString(R.string.music)),
        HomeGenre(9648, context.getString(R.string.mystery)),
        HomeGenre(10749, context.getString(R.string.romance)),
        HomeGenre(878, context.getString(R.string.science_fiction)),
        HomeGenre(10770, context.getString(R.string.tv_movie)),
        HomeGenre(53, context.getString(R.string.thriller)),
        HomeGenre(10752, context.getString(R.string.war)),
        HomeGenre(37, context.getString(R.string.western))
    )


    val tvGenres = listOf(
        HomeGenre(10759,  context.getString(R.string.action_and_adventure)),
        HomeGenre(16, context.getString(R.string.animation)),
        HomeGenre(35, context.getString(R.string.comedy)),
        HomeGenre(80, context.getString(R.string.crime)),
        HomeGenre(99, context.getString(R.string.documentary)),
        HomeGenre(18, context.getString(R.string.drama)),
        HomeGenre(10751, context.getString(R.string.family)),
        HomeGenre(10762, context.getString(R.string.kids)),
        HomeGenre(9648, context.getString(R.string.mystery)),
        HomeGenre(10763, context.getString(R.string.news)),
        HomeGenre(10764, context.getString(R.string.reality)),
        HomeGenre(10765, context.getString(R.string.science_fiction)),
        HomeGenre(10766, context.getString(R.string.soape)),
        HomeGenre(10767, context.getString(R.string.talk)),
        HomeGenre(10768, context.getString(R.string.war_and_politic)),
        HomeGenre(37, context.getString(R.string.western))
    )




}
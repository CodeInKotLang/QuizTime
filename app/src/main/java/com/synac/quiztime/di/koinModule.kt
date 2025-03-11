package com.synac.quiztime.di

import com.synac.quiztime.data.local.DatabaseFactory
import com.synac.quiztime.data.local.QuizDatabase
import com.synac.quiztime.data.remote.HttpClientFactory
import com.synac.quiztime.data.remote.KtorRemoteQuizDataSource
import com.synac.quiztime.data.remote.RemoteQuizDataSource
import com.synac.quiztime.data.repository.QuizQuestionRepositoryImpl
import com.synac.quiztime.data.repository.QuizTopicRepositoryImpl
import com.synac.quiztime.domain.repository.QuizQuestionRepository
import com.synac.quiztime.domain.repository.QuizTopicRepository
import com.synac.quiztime.presentation.dashboard.DashboardViewModel
import com.synac.quiztime.presentation.quiz.QuizViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule = module {

    single { HttpClientFactory.create() }

    singleOf(::KtorRemoteQuizDataSource).bind<RemoteQuizDataSource>()

    single { DatabaseFactory.create(get()) }
    single { get<QuizDatabase>().quizTopicDao() }

    singleOf(::QuizQuestionRepositoryImpl).bind<QuizQuestionRepository>()
    singleOf(::QuizTopicRepositoryImpl).bind<QuizTopicRepository>()

    viewModelOf(::QuizViewModel)
    viewModelOf(::DashboardViewModel)

}
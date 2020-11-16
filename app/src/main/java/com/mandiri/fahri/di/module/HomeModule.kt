package com.mandiri.fahri.di.module

import com.mandiri.fahri.data.HomeDataSource
import com.mandiri.fahri.data.HomeFactorty
import com.mandiri.fahri.di.scope.Presentation
import com.mandiri.fahri.domain.*
import com.mandiri.fahri.domain.executor.JobExecutor
import com.mandiri.fahri.domain.executor.UIThread
import com.mandiri.fahri.presentation.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class HomeModule {
    @Module
    companion object{
    @JvmStatic
    @Presentation
    @Provides
    fun providesHomeDataSource(retrofit: Retrofit) : HomeDataSource = retrofit.create(HomeDataSource::class.java)

    @JvmStatic
    @Presentation
    @Provides
    fun providesHomePresenter(dataSource: HomeDataSource) : HomeFactorty = HomeFactorty(dataSource)

    @JvmStatic
    @Presentation
    @Provides
    fun providesRepository(factorty: HomeFactorty) : HomeRepositoryImpl = HomeRepositoryImpl(factorty)

    @JvmStatic
    @Presentation
    @Provides
    fun providesUsecase(repository: HomeRepository,
                        executor: JobExecutor,
                        thread:UIThread) : HomeUsecase = HomeUsecase(repository,executor,thread)
    @JvmStatic
    @Presentation
    @Provides
    fun providesGenreUsecase(repository: HomeRepository,
                             executor: JobExecutor,
                             thread:UIThread) : GenreUsecase = GenreUsecase(repository,executor,thread)

    @JvmStatic
    @Presentation
    @Provides
    fun providesTrailerUseCase( repository: HomeRepository,
                                executor: JobExecutor,
                                thread: UIThread) : TrailerUseCase = TrailerUseCase(repository,executor,thread)

     @JvmStatic
     @Presentation
     @Provides
     fun providesReviewUseCase( repository: HomeRepository,
                                    executor: JobExecutor,
                                    thread: UIThread) : ReviewUseCase = ReviewUseCase(repository,executor,thread)
    @JvmStatic
    @Presentation
    @Provides
    fun providesPresenter(view : HomeMovieView, usecase: HomeUsecase,usecasegenre : GenreUsecase) = HomeMoviePresenter(view,usecase,usecasegenre)

     @JvmStatic
     @Presentation
     @Provides
     fun providesPresenterDetail(view: DetailMovieView,useCase: TrailerUseCase,useCaseReview: ReviewUseCase) = DetailMoviePresenter(view,useCase,useCaseReview)


    }



    @Binds
    abstract fun bindHomeMovieView(activity: HomeMovieActivity) : HomeMovieView
    @Binds
    abstract fun bindRepository(repositoryImpl: HomeRepositoryImpl) :HomeRepository
    @Binds
    abstract fun bindDetailMovieView(activity : DetailMovie) : DetailMovieView



}
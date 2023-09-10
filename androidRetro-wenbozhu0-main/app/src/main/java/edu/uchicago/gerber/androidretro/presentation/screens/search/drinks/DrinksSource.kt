package edu.uchicago.gerber.androidretro.presentation.screens.search.drinks

import androidx.paging.PagingSource
import androidx.paging.PagingState
import edu.uchicago.gerber.androidretro.data.models.drinks.DrinksInfo
import edu.uchicago.gerber.androidretro.data.repository.DrinksRepository
import edu.uchicago.gerber.androidretro.presentation.screens.Paginate

class DrinksSource(private val drinksRepository: DrinksRepository,
                   private val paginateData: Paginate
): PagingSource<Int, DrinksInfo>() {
    override fun getRefreshKey(state: PagingState<Int, DrinksInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DrinksInfo> {
        return try {
            val prev = params.key ?: 0

            val response = drinksRepository.getDrinks(
                query = paginateData.query
            )

            if (response.isSuccessful) {
                val body = response.body()?.drinks
                LoadResult.Page(
                    data = body!!,
                    prevKey = if (prev == 0) null else prev - 1,
                    nextKey = if (body.size < params.loadSize) null else prev + 10
                )
            } else {
                LoadResult.Error(Exception(response.message()))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true
}
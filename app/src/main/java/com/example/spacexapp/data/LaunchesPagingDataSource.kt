package com.example.spacexapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.spacexapp.data.api.ApiService
import com.example.spacexapp.data.mapper.LaunchesMapper
import com.example.spacexapp.data.models.Options
import com.example.spacexapp.data.models.launches.DateUtc
import com.example.spacexapp.data.models.launches.FromToDate
import com.example.spacexapp.data.models.launches.LaunchesBody
import com.example.spacexapp.domain.entities.LaunchesEntity
import javax.inject.Inject

class LaunchesPagingDataSource @Inject constructor(
    private val apiService: ApiService,
    private val mapper: LaunchesMapper,
) : PagingSource<Int, LaunchesEntity>() {

    override val jumpingSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LaunchesEntity> {

        return try {

            val page = params.key ?: INITIAL_PAGE_NUMBER
            val loadSize = params.loadSize

            val body = LaunchesBody(
                options = Options("-date_utc", page),
                query = DateUtc(FromToDate())
            )

            val jsonResponse = apiService.getPagingLaunchesInfo(body)
            val response = mapper.mapDtoToEntityItem(jsonResponse)

            val nextKey = if (response.isEmpty()) null else page + 1
            val prevKey = if (page == INITIAL_PAGE_NUMBER) null else page - 1

            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LaunchesEntity>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}

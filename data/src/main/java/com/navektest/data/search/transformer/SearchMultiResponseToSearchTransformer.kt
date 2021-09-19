package com.navektest.data.search.transformer

import androidx.annotation.WorkerThread
import com.navektest.business.search.SearchMulti
import com.navektest.data.search.remote.response.SearchMultiResponse
import javax.inject.Inject

internal class SearchMultiResponseToSearchTransformer @Inject constructor() {

    @WorkerThread
    fun transform(response: SearchMultiResponse): SearchMulti {

        val items = response.results.map(::transformItem)

        return SearchMulti(response.page, items, response.total_pages, response.total_results)
    }

    private fun transformItem(result: SearchMultiResponse.Item): SearchMulti.Item {
        val knowForList: List<SearchMulti.KnownFor> = result.known_for?.map { transformKnowFor(it) } ?: emptyList()
        return SearchMulti.Item(
            adult = result.adult ?: false,
            backdrop_path = result.backdrop_path ?: "",
            gender = result.gender ?: 0,
            genre_ids = result.genre_ids ?: emptyList(),
            id = result.id ?: 0,
            known_for = knowForList,
            known_for_department = result.known_for_department ?: "",
            media_type = result.media_type ?: "",
            name = result.name ?: "",
            original_language = result.original_language ?: "",
            original_title = result.original_title ?: "",
            overview = result.overview ?: "",
            popularity = result.popularity ?: 0.0,
            poster_path = result.poster_path ?: "",
            profile_path = result.profile_path ?: "",
            release_date = result.release_date ?: "",
            title = result.title ?: "",
            video = result.video ?: false,
            vote_average = result.vote_average ?: 0.0,
            vote_count = result.vote_count ?: 0,
        )
    }

    private fun transformKnowFor(response: SearchMultiResponse.KnownFor): SearchMulti.KnownFor {
        return SearchMulti.KnownFor(
            adult = response.adult ?: false,
            backdrop_path = response.backdrop_path ?: "",
            genre_ids = response.genre_ids ?: emptyList(),
            id = response.id ?: 0,
            media_type = response.media_type ?: "",
            original_language = response.original_language ?: "",
            original_title = response.original_title ?: "",
            overview = response.overview ?: "",
            poster_path = response.poster_path ?: "",
            release_date = response.release_date ?: "",
            title = response.title ?: "",
            video = response.video ?: false,
            vote_average = response.vote_average ?: 0.0,
            vote_count = response.vote_count ?: 0,
        )
    }
}
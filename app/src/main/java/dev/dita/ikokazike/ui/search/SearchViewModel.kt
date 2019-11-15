package dev.dita.ikokazike.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.algolia.instantsearch.helper.android.list.SearcherSingleIndexDataSource
import com.algolia.instantsearch.helper.android.searchbox.SearchBoxConnectorPagedList
import com.algolia.instantsearch.helper.searchbox.SearchMode
import com.algolia.instantsearch.helper.searcher.SearcherSingleIndex
import com.algolia.instantsearch.helper.stats.StatsConnector
import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import dev.dita.ikokazike.BuildConfig
import dev.dita.ikokazike.data.Tweet

class SearchViewModel : ViewModel() {

    val client =
        ClientSearch(ApplicationID(BuildConfig.ALGOLIA_APP_ID), APIKey(BuildConfig.ALGOLIA_API_KEY))
    val index = client.initIndex(IndexName("ikokazike"))
    val searcher = SearcherSingleIndex(index)

    val dataSourceFactory = SearcherSingleIndexDataSource.Factory(searcher) {
        it.deserialize(Tweet.serializer())
    }
    val pagedListConfig =
        PagedList.Config.Builder().setPageSize(50).setEnablePlaceholders(false).build()
    val tweets: LiveData<PagedList<Tweet>> =
        LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    val searchBox =
        SearchBoxConnectorPagedList(searcher, listOf(tweets), searchMode = SearchMode.AsYouType)
    val stats = StatsConnector(searcher)

    override fun onCleared() {
        super.onCleared()
        searcher.cancel()
    }
}
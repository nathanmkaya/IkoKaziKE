package dev.dita.ikokazike.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.algolia.instantsearch.core.connection.ConnectionHandler
import com.algolia.instantsearch.helper.android.list.autoScrollToStart
import com.algolia.instantsearch.helper.android.searchbox.SearchBoxViewAppCompat
import com.algolia.instantsearch.helper.android.searchbox.connectView
import com.algolia.instantsearch.helper.android.stats.StatsTextView
import com.algolia.instantsearch.helper.stats.StatsPresenterImpl
import com.algolia.instantsearch.helper.stats.connectView
import dev.dita.ikokazike.R
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.include_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()
    private val connection = ConnectionHandler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchBoxView = SearchBoxViewAppCompat(searchView)
        val statsView = StatsTextView(stats)
        val tweetAdapter = TweetAdapter()

        searchViewModel.tweets.observe(this, Observer { tweetAdapter.submitList(it) })

        connection += searchViewModel.searchBox.connectView(searchBoxView)
        connection += searchViewModel.stats.connectView(statsView, StatsPresenterImpl())

        list.let {
            it.itemAnimator = null
            it.adapter = tweetAdapter
            it.layoutManager = LinearLayoutManager(requireContext())
            it.autoScrollToStart(tweetAdapter)
        }

        searchViewModel.searcher.searchAsync()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        connection.disconnect()
    }
}

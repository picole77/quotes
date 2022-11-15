package edu.itvo.quotes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import edu.itvo.quotes.databinding.FragmentSlideshowBinding
import edu.itvo.quotes.presentation.viewmodel.QuoteAllViewModel
import edu.itvo.quotes.ui.slideshow.SlideshowViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private lateinit var viewModelAll: QuoteAllViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)

        viewModelAll = ViewModelProvider(this)[QuoteAllViewModel::class.java]

        observerList(binding.tvQuotes)

        return binding.root
    }
    private fun observerList(textView: TextView) {
        lifecycleScope.launch {
            viewModelAll?.getAllQuote()
            viewModelAll?.quoteModelList?.collect {
                var data = ""
                it.forEach { quoteModel ->
                    data += "${quoteModel.id}-${quoteModel.quote}-${quoteModel.author} \n"
                }
                textView.append(data)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
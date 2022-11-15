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
import edu.itvo.quotes.databinding.FragmentHomeBinding
import edu.itvo.quotes.presentation.viewmodel.QuoteViewModel

import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandonFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var quoteViewModel: QuoteViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        observer()
        binding.viewContainer.setOnClickListener {
            quoteViewModel.randomQuote()
        }


        val root: View = binding.root



        return root
    }
    private fun observer(){
        lifecycleScope.launch {
            quoteViewModel.quoteModel.collect {
                binding.tvQuote.text = it.quote
                binding.tvAuthor.text= it.author
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package edu.itvo.quotes.presentation.view

import dagger.hilt.android.AndroidEntryPoint


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import edu.itvo.quotes.databinding.FragmentGalleryBinding
import edu.itvo.quotes.domain.model.QuoteModel
import edu.itvo.quotes.presentation.viewmodel.AddViewModel
import kotlinx.coroutines.launch
@AndroidEntryPoint
class AddFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModelAdd: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModelAdd = ViewModelProvider(this)[AddViewModel::class.java]


        binding.btnAddQuote.setOnClickListener { observerAdd() }
        return binding.root
    }

    private fun getQuoteModel(): QuoteModel {
        return QuoteModel(
            id = binding.tvAddId.text.toString().toInt(),
            quote = binding.tvAddQuote.text.toString(),
            author = binding.tvAddAuthor.text.toString()
        )
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observerAdd() {
        lifecycleScope.launch {
            when {
                isValidData() -> {
                    viewModelAdd.let {
                        it.addQuote(getQuoteModel())
                        showMessage("Quote added")
                        clearData()
                    }
                }
                else -> showMessage("Please fill all fields")
            }
        }
    }

    private fun isValidData() = binding.tvAddId.text.toString().isNotEmpty() &&
            binding.tvAddQuote.text.toString().isNotEmpty() &&
            binding.tvAddAuthor.text.toString().isNotEmpty()



    private fun clearData() {
        binding.tvAddId.text.clear()
        binding.tvAddQuote.text.clear()
        binding.tvAddAuthor.text.clear()
    }

    private fun showMessage(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
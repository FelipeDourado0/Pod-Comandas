package com.example.comanda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.comanda.databinding.FragmentCadastroLoginBinding
import kotlin.time.Duration.Companion.seconds


class CadastroLoginFragment : Fragment() {
    private var _binding: FragmentCadastroLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCadastroLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener{
            findNavController().navigateUp()
        }

        val pessoasTypes = listOf("Pessoa Física", "Pessoa Jurídica")
        val autoComplete = binding.autoCompleteTipoPessoa
        val adapter = ArrayAdapter(this.requireContext(),R.layout.tipo_pessoa_list_item,pessoasTypes)

        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView,view,i,l ->

            val itemSelected = adapterView.getItemAtPosition(i)
            Toast.makeText(this.context,"Item: $itemSelected", Toast.LENGTH_SHORT).show()
        }

    }



}
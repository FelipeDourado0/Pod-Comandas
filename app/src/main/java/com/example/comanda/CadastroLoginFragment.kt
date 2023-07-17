package com.example.comanda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.comanda.databinding.FragmentCadastroLoginBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Calendar.DATE
import java.util.Calendar.DAY_OF_YEAR
import java.util.Calendar.YEAR
import java.util.Date
import java.util.Locale
import java.util.TimeZone


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
            findNavController().navigate(R.id.action_cadastroLoginFragment_to_login3)
        }
        createSelectTipoPessoa()

        binding.editTextDataNascimentoCadastroUsuario.setOnClickListener{
            showDatePickerDialog()
        }


    }

    fun createSelectTipoPessoa(){
        val pessoasTypes = arrayOf("Pessoa Física", "Pessoa Jurídica")
        val autoComplete = binding.autoCompleteTipoPessoa
        (autoComplete as? MaterialAutoCompleteTextView)?.setSimpleItems(pessoasTypes)

        /*OUTRA FORMA DE FAZER - USANDO ARRAY ADAPTER

        val adapter = ArrayAdapter(this.requireContext(),R.layout.tipo_pessoa_list_item,pessoasTypes)

        autoComplete.setAdapter(adapter)*/

        //como fazer uma ação apartir da seleção de um item
        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView,view,position,l ->

            val itemSelected = adapterView.getItemAtPosition(position)
            Toast.makeText(this.context,"$itemSelected", Toast.LENGTH_SHORT).show()
        }
    }

    fun showDatePickerDialog(){

        val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC-3")
        }
        val calendar = Calendar.getInstance()

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setTextInputFormat(outputDateFormat)
                .build()



        datePicker.addOnPositiveButtonClickListener{
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC-3"))
            calendar.time = Date(it)
            Toast.makeText(this.context, calendar.time.toString(),Toast.LENGTH_SHORT).show()
            val month: String
            if ((calendar.get(Calendar.MONTH) + 1).toString().length < 2) month = "0${calendar.get(Calendar.MONTH) + 1}" else month = "${calendar.get(Calendar.MONTH) + 1}"

            binding.editTextDataNascimentoCadastroUsuario.setText("${calendar.get(Calendar.DAY_OF_MONTH)}/" +
                    "${month}/${calendar.get(Calendar.YEAR)}")
        }
        return datePicker.show(childFragmentManager,"tag")
    }
}
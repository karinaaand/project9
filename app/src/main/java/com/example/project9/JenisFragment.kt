package com.example.project9

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.project9.databinding.FragmentJenisBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
// Kunci untuk menyimpan dan mengambil argumen dari Bundle
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JenisFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JenisFragment : Fragment() {
    // Variabel untuk menyimpan nilai argumen
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentJenisBinding? = null
    private val binding get() = _binding!!// Mendapatkan binding yang aman

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengambil argumen yang diteruskan ke Fragment
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menginflasi layout untuk fragment dan menginisialisasi binding
        _binding = FragmentJenisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            // Mengambil array string dari resources untuk spinner
            val provinces =
                resources.getStringArray(R.array.provinces)
            // Membuat adapter untuk spinner
            val adapterProvinces = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, provinces)
            adapterProvinces.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            spinnerProvinces.adapter = adapterProvinces
            // Mengatur listener untuk tombol
            btnBt.setOnClickListener {
                findNavController().apply {
                    previousBackStackEntry?.savedStateHandle?.set("jenis", spinnerProvinces.selectedItem.toString())
                }.navigateUp() // Kembali ke Fragment sebelumnya
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JenisFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JenisFragment().apply {
                // Membungkus parameter dalam Bundle
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
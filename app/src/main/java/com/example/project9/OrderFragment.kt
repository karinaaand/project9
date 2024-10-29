package com.example.project9

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project9.databinding.FragmentOrderBinding
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // Menggunakan binding untuk mengakses elemen layout
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mendapatkan argumen yang diteruskan ke fragment
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menginflate layout fragment menggunakan binding
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root // Mengembalikan root view dari binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            // Mengatur listener untuk spinner item
            spinnerProvinces.setOnClickListener {
                // Navigasi ke JenisFragment dengan parameter yang dibawa
                val action = OrderFragmentDirections.actionOrderFragmentToJenisFragment(txt6.text.toString())
                findNavController().navigate(action)
            }

            // Mendapatkan data dari previous fragment menggunakan savedStateHandle
            findNavController().currentBackStackEntry?.savedStateHandle?.let { handle ->
                handle.getLiveData<String>("jenis").observe(viewLifecycleOwner) { res ->
                    // Mengatur nilai spinner berdasarkan data yang diterima
                    spinnerProvinces.setText(res)
                }
            }

            // Menambahkan logika ketika tombol pesan diklik
            btnBt.setOnClickListener {
                val selectedType = spinnerProvinces.text.toString()

                // Cek jika spinner kosong atau tidak ada tipe tiket yang dipilih
                if (selectedType.isEmpty() || selectedType == "Jenis Ticket") {
                    Toast.makeText(requireContext(), "Pilih tipe tiket", Toast.LENGTH_SHORT).show()
                } else {
                    // Format waktu saat ini
                    val currentDate = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())
                    // Tampilkan pesan Toast dengan tipe tiket dan waktu pemesanan
                    Toast.makeText(requireContext(), "Tiket dengan tipe $selectedType berhasil dipesan pada $currentDate", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Menghapus referensi binding saat view dihancurkan
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

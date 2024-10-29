package com.example.project9

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.project9.databinding.FragmentTicketBinding

// TODO: Rename parameter arguments, choose names that match

// Mendefinisikan kunci argumen parameter untuk inisialisasi fragmen
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TicketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TicketFragment : Fragment() {
    // Variabel untuk menyimpan nilai parameter yang diteruskan ke fragmen
    private var param1: String? = null
    private var param2: String? = null


    // Variabel binding untuk mengakses tampilan dalam layout
    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Mengambil parameter dari argumen jika tersedia
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Meng-inflate layout fragmen menggunakan View Binding
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Mengatur listener klik untuk tombol beli
        with(binding) {
            btnBuy.setOnClickListener {
                // Menavigasi ke OrderFragment dan meneruskan teks dari txt6 sebagai argumen
//                view.findNavController()?.navigate(R.id.action_TicketFragment_to_OrderFragment)
                val action = TicketFragmentDirections.actionTicketFragmentToOrderFragment(txt6.text.toString())
                findNavController().navigate(action)
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
         * @return A new instance of fragment TicketFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TicketFragment().apply {
                // Membuat Bundle untuk menyimpan parameter
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
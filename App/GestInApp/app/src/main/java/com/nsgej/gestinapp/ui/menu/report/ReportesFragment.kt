 package com.nsgej.gestinapp.ui.menu.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nsgej.gestinapp.R
import com.nsgej.gestinapp.databinding.FragmentReportesBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ReportesFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentReportesBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReportesBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnReporteinventario.setOnClickListener {
            findNavController().navigate(R.id.action_reportesFragment_to_rprtInventarioFragment)
        }

        binding.btnRegresar.setOnClickListener {
            findNavController().navigate(R.id.action_reportesFragment_to_menuFragment)
        }
    }








    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReportesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
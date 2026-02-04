package com.example.cs4405_02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cs4405_02.databinding.FragmentSecondBinding


enum class Operations {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE
}


class Calculator(private val operand1: Double, private val operand2: Double) {
    fun calculate(operation: Operations): Double {
        return when (operation) {
            Operations.ADD -> operand1 + operand2
            Operations.SUBTRACT -> operand1 - operand2
            Operations.MULTIPLY -> operand1 * operand2
            Operations.DIVIDE -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
        }
    }
}

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.calculate.setOnClickListener {
            val op1 = binding.operand1.text.toString().toDouble()
            val op2 = binding.operand2.text.toString().toDouble()
            val _op = binding.operation.selectedItem.toString()
            val operation = when (_op) {
                "ADD" -> Operations.ADD
                "SUBTRACT" -> Operations.SUBTRACT
                "MULTIPLY" -> Operations.MULTIPLY
                "DIVIDE" -> Operations.DIVIDE
                else -> throw IllegalArgumentException("Unknown operation.")
            }

            val calculator = Calculator(op1, op2)
            val result = calculator.calculate(operation)
            binding.result.text = result.toString()


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
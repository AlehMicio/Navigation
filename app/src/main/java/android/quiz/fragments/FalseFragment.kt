package android.quiz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.quiz.R
import android.quiz.databinding.FragmentFalseBinding
import android.quiz.databinding.FragmentTrueBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController


class FalseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil
            .inflate<FragmentFalseBinding>(inflater, R.layout.fragment_false, container, false)

        binding.pressButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_falseFragment_to_quizFragment)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "LOOSE" //Устанавливает название активити на тул-баре

        return  binding.root
    }


}
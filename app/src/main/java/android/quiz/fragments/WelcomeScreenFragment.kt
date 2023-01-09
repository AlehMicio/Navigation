package android.quiz.fragments

import android.os.Bundle
import android.quiz.R
import android.quiz.databinding.FragmentWelcomeScreenBinding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

class WelcomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil
            .inflate<FragmentWelcomeScreenBinding>(inflater, R.layout.fragment_welcome_screen, container, false)

        binding.button.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_welcomeScreenFragment_to_quizFragment)
        }

       // (activity as AppCompatActivity).supportActionBar?.title = "Quiz" //Устанавливает название активити на тул-баре

        return binding.root
    }
}
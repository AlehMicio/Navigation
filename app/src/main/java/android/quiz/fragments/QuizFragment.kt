package android.quiz.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.quiz.QuizItem
import androidx.fragment.app.Fragment
import android.quiz.R
import android.quiz.databinding.FragmentQuizBinding
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import java.util.Timer

class QuizFragment : Fragment() {

    private val quizItem: MutableList<QuizItem> = mutableListOf(
        QuizItem("1+1", listOf("2", "3", "4")),
        QuizItem("2+2", listOf("4", "5", "6")),
        QuizItem("3+3", listOf("6", "7", "8")),
        QuizItem("4+4", listOf("8", "9", "10")),
        QuizItem("5+5", listOf("10", "11", "12")))

    lateinit var nowQuizItem: QuizItem
    lateinit var answer: MutableList<String>
    private var quizItemIndex = 0 //Счётчик вопросов
    private val numberOfQuestions = 2 //кол-во вопросов всего

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val binding = DataBindingUtil
            .inflate<FragmentQuizBinding>(inflater, R.layout.fragment_quiz, container, false)

        getRandomQuestion()

        //Связыаем класс с разметкой через Data Binding, чтобы можно было прямо в разметке давать ссылки на переменные
        binding.quizFragment = this

        binding.pressButton.setOnClickListener { view: View ->
            var selectedCheckBoxId = binding.quizRadioGroup.checkedRadioButtonId //Получаем тот чек-бокс, который отмечен

            if (selectedCheckBoxId > -1){
                var answerIndex = 0
                when (selectedCheckBoxId){
                    R.id.firstRadioButton -> answerIndex = 0
                    R.id.secondRadioButton -> answerIndex = 1
                    R.id.thirdRadioButton -> answerIndex = 2
                }

                if (answer[answerIndex].equals(nowQuizItem.answerList[0])){
                    quizItemIndex++
                    if (quizItemIndex < numberOfQuestions){
                        setQuizItem()
                        binding.invalidateAll() //Чтобы показать все изменения в разметке
                    }
                    else {
                        //Все ответы верны
                        view.findNavController().navigate(R.id.action_quizFragment_to_trueFragment)
                    }
                }
                else {
                    //Ответ неверный
                    binding.ballImageView.animate()
                        .rotation(3600f)
                        .translationX(1000f)
                        .setDuration(2000)

                    Handler(Looper.getMainLooper()).postDelayed({
                        view.findNavController().navigate(R.id.action_quizFragment_to_falseFragment)
                    }, 2000)
                }
            }
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Quiz" //Устанавливает название активити на тул-баре

        //Данная строка отвечает за то, чтобы в данном активити было меню (+ нужны 2 стандартных метода для меню)
        setHasOptionsMenu(true)

        return binding.root
    }

    //Нужен для создания меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    //Нужен для меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
         item, requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun getRandomQuestion(){
        quizItem.shuffle() //Тасует параметры, чтобы они шли на рандом
        quizItemIndex = 0
        setQuizItem()
    }

    private fun setQuizItem(){
        nowQuizItem = quizItem[quizItemIndex]
        answer = nowQuizItem.answerList.toMutableList()
        answer.shuffle()
    }

}
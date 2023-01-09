package android.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.quiz.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout //Для бокового меню

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawelLayout

        val navController = this.findNavController(R.id.appNavHostFragment)
        //Добавляет стрелку назад + onSupportNavigateUp()
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    //Добавляет стрелку назад, связанную с Navigate
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.appNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
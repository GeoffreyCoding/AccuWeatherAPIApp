package za.co.varsitycollege.st10081932.accuweatherapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import za.co.varsitycollege.st10081932.accuweatherapiapp.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkUtil = NetworkUtil()

        thread {
            val weather = try {
                networkUtil.buildURLForWeather()?.readText()
            } catch (e: Exception) {
                return@thread
            }
            runOnUiThread {
                binding.tvWeather.text = weather
            }
        }
    }
}
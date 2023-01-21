package developers.world.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import developers.world.countdown.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel.seconds().observe(this, Observer {
            binding.timerText.text = it.toString()
        })
        viewModel.finished.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show()
            }
        })

        binding.buttonStart.setOnClickListener {
            if (binding.inputTime.text.isEmpty() || binding.inputTime.length() < 4){
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.timerValue.value = binding.inputTime.text.toString().toLong()

                Toast.makeText(this, viewModel.timerValue.value.toString(), Toast.LENGTH_SHORT).show()

                viewModel.startTimer()
            }

        }
        binding.buttonStop.setOnClickListener {
            binding.inputTime.text.clear()
            binding.timerText.text = "0"
            viewModel.stopTimer()
        }

    }
}
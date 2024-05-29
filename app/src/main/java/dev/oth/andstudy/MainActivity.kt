package dev.oth.andstudy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.oth.andstudy.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge() // new method for edge fill
        setContentView(R.layout.activity_main) // without view-binding
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        } // new method for window inset listener

//        getLayoutInflater()
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this
        binding.activity = this
        binding.vm = viewModel

        binding.tvBtn.setOnClickListener {
            it
        }

        viewModel.isTextDisplaying.observe(this) {
            println("is updated text displayed : $it")
        }

        setContentView(binding.root)
    }

    private var clickCnt = 0

    public fun onClickBtn() {
        clickCnt += 1
        viewModel.tvText.value = "is Clicked Text $clickCnt"
    }

    public fun onClickSubActivity() {
        startActivity(Intent(this, SubActivity::class.java))
    }
}


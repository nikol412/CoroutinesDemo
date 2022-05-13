package com.nikol412.coroutinesdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.nikol412.coroutinesdemo.base.viewBinding
import com.nikol412.coroutinesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewmodel: MainViewModel by viewModels()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.buttonLoadAll.setOnClickListener { viewmodel.onLoadAllClick() }
    }

    private fun observeViewModel() = with(viewmodel) {
        firstImageUrl.observe(this@MainActivity) {
            binding.imageViewFirst.load(it)
        }
        secondImageUrl.observe(this@MainActivity) {
            binding.imageViewSecond.load(it)
        }
        thirdImageUrl.observe(this@MainActivity) {
            binding.imageViewThird.load(it)
        }
        errorText.observe(this@MainActivity) {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }
    }
}

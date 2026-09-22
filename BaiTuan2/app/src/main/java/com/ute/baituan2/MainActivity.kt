package com.ute.baituan2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ute.baituan2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. Khai báo biến binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Giữ nguyên tính năng tràn viền

        // 2. Khởi tạo ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cập nhật lại insets listener sử dụng binding thay cho findViewById
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Xử lý sự kiện click mở DetailActivity kèm thông tin
        binding.btnOpenDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            // Truyền tên thật của bạn vào Intent
            intent.putExtra("USER_NAME", "Nguyễn Thành Nguyên")
            startActivity(intent)
        }
    }
}
package com.ute.baituan2
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ute.baituan2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    // 1. Khai báo biến ViewBinding cho DetailActivity
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Giữ nguyên tính năng tràn viền

        // 2. Khởi tạo ViewBinding thay cho R.layout.activity_detail
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Sử dụng binding.main cho Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Lấy dữ liệu được truyền qua Intent từ MainActivity
        val receivedName = intent.getStringExtra("USER_NAME") ?: "Không nhận được tên"

        // 5. Hiển thị dữ liệu lên TextView (id là tvMessage)
        binding.tvMessage.text = "Xin chào, $receivedName!"
    }
}
package com.ute.studentprofile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ute.studentprofile.databinding.ActivityMainBinding
import com.ute.studentprofile.model.Student
import com.ute.studentprofile.utils.showConfirmDialog
import com.ute.studentprofile.utils.toAcademicRanking
import com.ute.studentprofile.utils.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentStudent: Student? = Student(
        id = "2415053122331",
        name = "Nguyễn Thành Nguyên",
        className = "24T3",
        email = "2414053122331@sv.ute.udn.vn",
        phone = "0376172926",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiển thị thông tin sinh viên ban đầu
        currentStudent?.let { bindStudentData(it) }

        // Xử lý sự kiện bấm nút Cập nhật GPA
        binding.btnUpdateGpa.setOnClickListener {
            val student = currentStudent ?: run {
                toast("Không có dữ liệu sinh viên!")
                return@setOnClickListener
            }

            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            currentStudent = student.copy(gpa = newGpa)
            currentStudent?.let { bindStudentData(it) }
            toast("Cập nhật điểm thành công!")
        }

        // Xử lý sự kiện bấm nút Gọi điện
        binding.btnCallPhone.setOnClickListener {
            val phone = currentStudent?.phone
            if (!phone.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phone")
                }
                startActivity(intent)
            } else {
                toast("Không tìm thấy số điện thoại!")
            }
        }

        // Xử lý sự kiện bấm nút Xóa hồ sơ
        binding.btnDeleteProfile.setOnClickListener {
            showConfirmDialog(
                title = "Xác nhận xóa",
                message = "Bạn có chắc chắn muốn xóa hồ sơ sinh viên này không?"
            ) {
                currentStudent = null
                binding.cardProfile.visibility = View.GONE
                toast("Đã xóa hồ sơ sinh viên!")
            }
        }
    }

    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvPhone.text = "SĐT: ${student.phone}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}
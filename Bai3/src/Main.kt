data class Student(
    val id: String,
    var fullName: String,
    var age: Int,
    var major: String,
    var gpa: Double
) {
    override fun toString(): String {
        return "ID: %-6s | Tên: %-20s | Tuổi: %-2d | Ngành: %-20s | GPA: %.2f".format(id, fullName, age, major, gpa)
    }
}

class StudentManager {
    // 5 sinh viên mẫu sẵn có theo yêu cầu
    private val students = mutableListOf(
        Student("SV01", "Nguyễn Thành Nguyên", 20, "Công nghệ thông tin", 8.5),
        Student("SV02", "Trần Thị Bình", 22, "Kinh tế", 4.5),
        Student("SV03", "Lê Hoàng Cường", 21, "Công nghệ thông tin", 7.8),
        Student("SV04", "Phạm Minh Đức", 19, "Thiết kế đồ họa", 9.2),
        Student("SV05", "Vũ Thị Anh Thư", 23, "Kinh tế", 6.8)
    )

    // 1. Add student
    fun addStudent() {
        println("\n--- THÊM SINH VIÊN MỚI ---")
        print("Nhập Student ID: ")
        val id = readlnOrNull()?.trim().orEmpty()
        if (id.isEmpty() || students.any { it.id.equals(id, ignoreCase = true) }) {
            println("ID không được để trống hoặc đã tồn tại!")
            return
        }

        print("Nhập Full Name: ")
        val fullName = readlnOrNull()?.trim().orEmpty()

        print("Nhập Age: ")
        val age = readlnOrNull()?.toIntOrNull() ?: 0

        print("Nhập Major: ")
        val major = readlnOrNull()?.trim().orEmpty()

        print("Nhập GPA (0.0 - 10.0): ")
        val gpa = readlnOrNull()?.toDoubleOrNull() ?: -1.0

        if (fullName.isEmpty() || age <= 0 || major.isEmpty() || gpa !in 0.0..10.0) {
            println("Thông tin không hợp lệ!")
            return
        }

        students.add(Student(id, fullName, age, major, gpa))
        println("Thêm sinh viên thành công!")
    }

    // 2. Display all students (Kèm lựa chọn sắp xếp)
    fun displayMenu() {
        if (students.isEmpty()) {
            println("Danh sách sinh viên trống.")
            return
        }
        println("\n--- TÙY CHỌN HIỂN THỊ / SẮP XẾP ---")
        println("1. Hiển thị danh sách gốc")
        println("2. Sắp xếp theo GPA giảm dần")
        println("3. Sắp xếp theo Tuổi (tăng dần)")
        println("4. Sắp xếp theo Tên (A-Z)")
        print("Chọn: ")

        val listToDisplay = when (readlnOrNull()?.trim()) {
            "2" -> students.sortedByDescending { it.gpa }
            "3" -> students.sortedBy { it.age }
            "4" -> students.sortedBy { it.fullName.trim().split("\\s+".toRegex()).last() }
            else -> students
        }

        printList(listToDisplay)
    }

    // 3. Search student
    fun searchMenu() {
        if (students.isEmpty()) {
            println("⚠️ Danh sách sinh viên trống.")
            return
        }
        println("\n--- TÌM KIẾM SINH VIÊN ---")
        println("1. Tìm theo một phần tên")
        println("2. Tìm tất cả sinh viên thuộc một ngành")
        println("3. Tìm sinh viên có GPA trong khoảng 7.0 -> 8.5")
        println("4. Tìm sinh viên lớn tuổi nhất")
        print("Chọn: ")

        when (readlnOrNull()?.trim()) {
            "1" -> {
                print("Nhập tên/từ khóa cần tìm: ")
                val kw = readlnOrNull()?.trim().orEmpty()
                val result = students.filter { it.fullName.contains(kw, ignoreCase = true) }
                printList(result)
            }
            "2" -> {
                print("Nhập tên ngành: ")
                val major = readlnOrNull()?.trim().orEmpty()
                val result = students.filter { it.major.equals(major, ignoreCase = true) }
                printList(result)
            }
            "3" -> {
                val result = students.filter { it.gpa in 7.0..8.5 }
                printList(result)
            }
            "4" -> {
                val maxAge = students.maxOfOrNull { it.age }
                val oldest = students.filter { it.age == maxAge }
                println("\n--- SINH VIÊN LỚN TUỔI NHẤT ($maxAge tuổi) ---")
                printList(oldest)
            }
            else -> println("Lựa chọn không hợp lệ.")
        }
    }

    // 4. Calculate average GPA & Thống kê
    fun calculateAverageGpa() {
        if (students.isEmpty()) {
            println("Danh sách sinh viên trống.")
            return
        }

        println("\n--- THỐNG KÊ & TÍNH GPA TRUNG BÌNH ---")
        val countGpaHigh = students.count { it.gpa >= 8.0 }
        val countGpaLow = students.count { it.gpa < 5.0 }
        val avgGpaAll = students.map { it.gpa }.average()

        println("• Số sinh viên có GPA >= 8.0 : $countGpaHigh")
        println("• Số sinh viên có GPA < 5.0 : $countGpaLow")
        println("• GPA trung bình toàn bộ SV : %.2f".format(avgGpaAll))

        print("\nNhập tên ngành cần tính GPA trung bình: ")
        val majorInput = readlnOrNull()?.trim().orEmpty()
        val majorStudents = students.filter { it.major.equals(majorInput, ignoreCase = true) }

        if (majorStudents.isNotEmpty()) {
            val avgMajor = majorStudents.map { it.gpa }.average()
            println("• GPA trung bình ngành '$majorInput': %.2f".format(avgMajor))
        } else {
            println("⚠️ Không tìm thấy sinh viên thuộc ngành '$majorInput'.")
        }
    }

    // 5. Find student with highest GPA
    fun findHighestGpa() {
        if (students.isEmpty()) {
            println("⚠️ Danh sách sinh viên trống.")
            return
        }
        val maxGpa = students.maxOfOrNull { it.gpa }
        val topStudents = students.filter { it.gpa == maxGpa }

        println("\n--- SINH VIÊN CÓ GPA CAO NHẤT (GPA: $maxGpa) ---")
        printList(topStudents)

        println("\n--- TOP 3 SINH VIÊN CÓ GPA CAO NHẤT ---")
        val top3 = students.sortedByDescending { it.gpa }.take(3)
        printList(top3)
    }

    // 6. Remove student
    fun removeStudent() {
        print("\nNhập Student ID cần xóa: ")
        val id = readlnOrNull()?.trim().orEmpty()
        val removed = students.removeIf { it.id.equals(id, ignoreCase = true) }
        if (removed) {
            println("Đã xóa thành công sinh viên ID: $id")
        } else {
            println("Không tìm thấy sinh viên có ID: $id")
        }
    }

    private fun printList(list: List<Student>) {
        if (list.isEmpty()) {
            println("Không tìm thấy kết quả phù hợp.")
            return
        }
        println("-".repeat(78))
        list.forEach { println(it) }
        println("-".repeat(78))
    }
}

fun main() {
    val manager = StudentManager()

    while (true) {
        println(
            """
            
            ========== STUDENT MANAGEMENT ========== 
            1. Add student 
            2. Display all students 
            3. Search student 
            4. Calculate average GPA 
            5. Find student with highest GPA 
            6. Remove student 
            0. Exit 
            ========================================
            """.trimIndent()
        )
        print("Choose: ")
        when (readlnOrNull()?.trim()) {
            "1" -> manager.addStudent()
            "2" -> manager.displayMenu()
            "3" -> manager.searchMenu()
            "4" -> manager.calculateAverageGpa()
            "5" -> manager.findHighestGpa()
            "6" -> manager.removeStudent()
            "0" -> {
                println("Chương trình kết thúc. Tạm biệt!")
                break
            }
            else -> println("Lựa chọn không hợp lệ, vui lòng chọn lại!")
        }
    }
}
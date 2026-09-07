fun main() {
    // 1. Nhập thông tin sinh viên
    print("Nhập họ và tên sinh viên: ")
    val name = readln()

    print("Nhập mã sinh viên (MSSV): ")
    val studentId = readln()

    // 2. Nhập điểm 3 môn
    print("Nhập điểm Math: ")
    val math = readln().toDouble()

    print("Nhập điểm Programming: ")
    val programming = readln().toDouble()

    print("Nhập điểm Database: ")
    val database = readln().toDouble()

    // 3. Tính toán các yêu cầu
    val total = math + programming + database
    val average = total / 3
    val highest = maxOf(math, programming, database)
    val isPassed = average >= 5.0

    // 4. In kết quả ra màn hình kèm thông tin sinh viên theo đúng yêu cầu đề bài
    println("\n=========================================")
    println("THÔNG TIN VÀ KẾT QUẢ HỌC TẬP")
    println("Sinh viên: $name (MSSV: $studentId)")
    println("-----------------------------------------")
    println("• Điểm thành phần: Math = $math, Programming = $programming, Database = $database")
    println("• Tổng điểm: $total")
    println("• Điểm trung bình (GPA): ${"%.2f".format(average)}")
    println("• Điểm cao nhất: $highest")

    if (isPassed) {
        println("• Đánh giá: ĐẠT (GPA >= 5.0)")
    } else {
        println("• Đánh giá: KHÔNG ĐẠT (GPA < 5.0)")
    }
    println("=========================================")
}
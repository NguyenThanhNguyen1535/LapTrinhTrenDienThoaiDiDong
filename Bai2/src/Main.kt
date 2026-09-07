fun main() {
    // --- BÀI 1: In các số từ 1 đến 10 ---
    println("--- BÀI 1 ---")
    print("Các số từ 1 đến 10: ")
    for (i in 1..10) {
        print("$i ")
    }
    println("\n")

    // --- BÀI 2: Tính tổng từ 1 đến 100 ---
    println("--- BÀI 2 ---")
    var sum = 0
    for (i in 1..100) {
        sum += i
    }
    println("Tổng từ 1 đến 100 là: $sum\n")

    // --- BÀI 3: In các số chẵn từ 1 đến 20 ---
    println("--- BÀI 3 ---")
    print("Các số chẵn từ 1 đến 20: ")
    for (i in 2..20 step 2) {
        print("$i ")
    }
    println()
}
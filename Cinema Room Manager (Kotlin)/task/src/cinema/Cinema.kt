package cinema

fun main() {
    //Вводим количество рядов в кинотеатре
    println("Enter the number of rows:")
    val row = readln()!!.toInt()
    val frontRows = row / 2
    val backRows = row - frontRows
    var seatTicket = 0
    var rowTicket = 0
    var totalIncome = 0
    var currentIncome = 0
    //Вводим количество мест в ряду
    println("Enter the number of seats in each row:")
    val seats = readln()!!.toInt()
    val totalSeats = row * seats
    //Если количество мест в кинотеатре меньше 60, то стоимость билета 10 долларов,
    //если больше, то 10 долларов впереди и 8 долларов сзади. считается от середины зала
    if (totalSeats <= 60) {
        totalIncome = totalSeats * 10
    } else {
        totalIncome = frontRows * seats * 10 + backRows * seats * 8
    }

    var cinemaList = MutableList(row+1) { MutableList(seats+1) { "0" } }
    cinemaList[0][0] = "  "
    for (i in 1 .. seats) {
        cinemaList[0][i] = "$i"
    }
    for(j  in 1 .. row) {
        cinemaList[j][0] = "$j"

        for (i in 1 .. seats) {
            cinemaList[j][i] = "S"
        }
    }
do {
    //Выводим меню
    println()
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    val status = readln()!!.toInt()
    when (status) {
        //Показываем зал
        1 -> {
            println("Cinema:")
            for (i in 0 .. row) {
                println(cinemaList[i].joinToString(" "))
            }
        }
        //Покупаем билет
        2 -> {
            println("Enter a row number:")
            rowTicket = readln()!!.toInt()
            println("Enter a seat number in that row:")
            seatTicket = readln()!!.toInt()
            do {
                if ((rowTicket > row) || (seatTicket > seats)) {
                    println("Wrong input!")
                    println("Enter a row number:")
                    rowTicket = readln()!!.toInt()
                    println("Enter a seat number in that row:")
                    seatTicket = readln()!!.toInt()
                } else if (cinemaList[rowTicket][seatTicket] == "B") {
                    println("That ticket has already been purchased!")
                    println("Enter a row number:")
                    rowTicket = readln()!!.toInt()
                    println("Enter a seat number in that row:")
                    seatTicket = readln()!!.toInt()
                }
            } while ((rowTicket > row) || (seatTicket > seats) || (cinemaList[rowTicket][seatTicket] == "B"))
            if ((rowTicket <= frontRows) || (totalSeats <= 60)) {
                println("Ticket price: \$10")
                currentIncome += 10
            } else {
                println("Ticket price: \$8")
                currentIncome += 8
            }
            cinemaList[rowTicket][seatTicket] = "B"
            println("Cinema:")
            for (i in 0 .. row) {
                println(cinemaList[i].joinToString(" "))
            }
        }
        //Выводим статистику
        3 -> {
            println("Number of purchased tickets: ${cinemaList.sumBy { it.count { it == "B" } }}")
            println("Percentage: ${"%.2f".format((cinemaList.sumBy { it.count { it == "B" } }.toDouble() / totalSeats.toDouble()) * 100)}%")
            println("Current income: $$currentIncome")
            println("Total income: $$totalIncome")
        }
    }
} while (status != 0) //Выход из программы
}
import java.util.*

class Calculator {

    private val history = mutableListOf<String>() // Operation history

    private val inputStack = Stack<Double>() // Stack for number inputs



    // Add a number to the stack

    fun addInput(number: Double): String {

        inputStack.push(number)

        return "Number $number added."

    }



    // Undo the last entered number

    fun undoLastInput(): String {

        return if (inputStack.isEmpty()) {

            "No numbers to undo."

        } else {

            val removed = inputStack.pop()

            "Removed $removed from input."

        }

    }



    // Perform addition

    fun add(): Double {

        if (inputStack.size < 2) throw IllegalArgumentException("At least two numbers are needed.")

        val result = inputStack.sum()

        history.add("${inputStack.joinToString(" + ")} = $result")

        inputStack.clear()

        return result

    }



    // Perform subtraction

    fun subtract(): Double {

        if (inputStack.size < 2) throw IllegalArgumentException("At least two numbers are needed.")

        val result = inputStack.reduce { acc, number -> acc - number }

        history.add("${inputStack.joinToString(" - ")} = $result")

        inputStack.clear()

        return result

    }



    // Perform multiplication

    fun multiply(): Double {

        if (inputStack.size < 2) throw IllegalArgumentException("At least two numbers are needed.")

        val result = inputStack.reduce { acc, number -> acc * number }

        history.add("${inputStack.joinToString(" * ")} = $result")

        inputStack.clear()

        return result

    }



    // Perform division

    fun divide(): String {

        if (inputStack.size < 2) throw IllegalArgumentException("At least two numbers are needed.")

        if (inputStack.contains(0.0)) {

            history.add("${inputStack.joinToString(" / ")} = Error (division by zero)")

            return "Error: Division by zero"

        }

        val result = inputStack.reduce { acc, number -> acc / number }

        history.add("${inputStack.joinToString(" / ")} = $result")

        inputStack.clear()

        return result.toString()

    }



    // Show the history of calculations

    fun showHistory(): String {

        return if (history.isEmpty()) {

            "The history is empty."

        } else {

            history.joinToString("\n")

        }

    }



    // Clear the history

    fun clearHistory(): String {

        history.clear()

        return "History cleared."

    }

}



fun main() {

    val calculator = Calculator()

    var running = true



    while (running) {

        println("\nCalculator Menu:")

        println("1. Add number")

        println("2. Undo last input")

        println("3. Add")

        println("4. Subtract")

        println("5. Multiply")

        println("6. Divide")

        println("7. Show History")

        println("8. Clear History")

        println("9. Exit")

        print("Choose an option: ")



        val choice = readLine()?.toIntOrNull()



        when (choice) {

            1 -> {

                print("Enter a number: ")

                val number = readLine()?.toDoubleOrNull()

                if (number != null) {

                    println(calculator.addInput(number))

                } else {

                    println("Invalid input. Please enter a valid number.")

                }

            }

            2 -> println(calculator.undoLastInput())

            3 -> {

                try {

                    println("Result: ${calculator.add()}")

                } catch (e: IllegalArgumentException) {

                    println(e.message)

                }

            }

            4 -> {

                try {

                    println("Result: ${calculator.subtract()}")

                } catch (e: IllegalArgumentException) {

                    println(e.message)

                }

            }

            5 -> {

                try {

                    println("Result: ${calculator.multiply()}")

                } catch (e: IllegalArgumentException) {

                    println(e.message)

                }

            }

            6 -> {

                try {

                    println("Result: ${calculator.divide()}")

                } catch (e: IllegalArgumentException) {

                    println(e.message)

                }

            }

            7 -> println("\nHistory:\n${calculator.showHistory()}")

            8 -> println(calculator.clearHistory())

            9 -> {

                println("Exiting the calculator. Goodbye!")

                running = false

            }

            else -> println("Invalid option. Please choose a valid menu option.")

        }

    }

}
package app.antran.kmm.playground.day4

// https://kotlinlang.org/docs/native-objc-interop.html#function-types
class FunctionCallback {
    fun intFunction(startValue: Int, block: (Int) -> Int): Int {
        var value = block(startValue)
        value *= 2
        return value
    }

    fun doubleFunction(startValue: Double, block: (Double) -> Double): Double {
        var value = block(startValue)
        value /= 2
        return value
    }
}
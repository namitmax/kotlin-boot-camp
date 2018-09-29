package io.rybalkinsd.kotlinbootcamp.assignments

/**
 * Returns the greatest of `int` values.
 *
 * @param values an argument. !! Assume values.length > 0. !!
 * @return the largest of values.
 */

fun max(values: List<Int>): Int {
    if (values.isNotEmpty()) {
        var mx: Int = values.first()
        for (e in values) {
            if (e > mx) mx = e
        }
        return mx
    } else return 0
}

/**
 * Returns the sum of all `int` values.
 *
 * @param values an argument. Assume values.length > 0.
 * @return the sum of all values.
 */
fun sum(values: List<Int>): Long {
    if (values.isNotEmpty()) {
        var sm: Long = 0
        for (e in values) {
            sm += e
        }
        return sm
    } else return 0
}
package com.db.dprice.kisapp

/*
 * Your task is to implement the sum() function so that it computes the sum of
 * all elements in the given array a.
 */
fun sum(a: IntArray): Int {
    return a.sum()
}

/*
 * Your task is to implement the indexOfMax() function so that it returns
 * the index of the largest element in the array, or null if the array is empty.
 */
fun indexOfMax(a: IntArray): Int? {
    return a.indices.maxBy { a[it] }
}

/*
 * Any array may be viewed as a number of "runs" of equal numbers.
 * For example, the following array has two runs:
 *   1, 1, 1, 2, 2
 * Three 1's in a row form the first run, and two 2's form the second.
 * This array has two runs of length one:
 *   3, 4
 * And this one has five runs:
 *   1, 0, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0
 * Your task is to implement the runs() function so that it returns the number
 * of runs in the given array.
 */
fun runs(a: IntArray): Int {
    if (a.isEmpty()) {
        return 0
    }

    var prev = a[0]
    var runs = 1
    for (current in a) {
        if (current != prev)
            runs++
        prev = current
    }

    return runs
}

/*
 * Your task is to implement a palindrome test.
 *
 * A string is called a palindrome when it reads the same way left-to-right
 * and right-to-left.
 *
 * See http://en.wikipedia.org/wiki/Palindrome
 */
fun isPalindrome(s: String): Boolean {
    return s == s.reversed()
}

/*
 * Think of a perfect world where everybody has a soulmate.
 * Now, the real world is imperfect: there is exactly one number in the array
 * that does not have a pair. A pair is an element with the same value.
 * For example in this array:
 *   1, 2, 1, 2
 * every number has a pair, but in this one:
 *   1, 1, 1
 * one of the ones is lonely.
 *
 * Your task is to implement the findPairless() function so that it finds the
 * lonely number and returns it.
 *
 * A hint: there's a solution that looks at each element only once and uses no
 * data structures like collections or trees.
 */
fun findPairless(a: IntArray): Int {
    return a.reduce { v1: Int, v2: Int -> v1 xor v2 }
}
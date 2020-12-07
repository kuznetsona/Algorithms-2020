@file:Suppress("UNUSED_PARAMETER")

package lesson7

import kotlin.math.max

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * время работы - О(длина первой строки * длина второй строки)
 */
fun longestCommonSubSequence(first: String, second: String): String {
    val firstLen = first.length
    val secondLen = second.length
    val subLen = Array(firstLen + 1) { Array(secondLen + 1) { 0 } }
    for (i in 0 until firstLen) {
        for (j in 0 until secondLen) {
            if (first[i] == second[j])
                subLen[i + 1][j + 1] = subLen[i][j] + 1
            else
                subLen[i + 1][j + 1] = max(subLen[i + 1][j], subLen[i][j + 1])
        }
    }
    val answer = StringBuilder(max(firstLen, secondLen))
    var itFirst = firstLen
    var itSecond = secondLen
    while (itFirst > 0 && itSecond > 0) {
        when {
            first[itFirst - 1] == second[itSecond - 1] -> {
                answer.append(first[itFirst - 1])
                itFirst--
                itSecond--
            }
            subLen[itFirst - 1][itSecond] > subLen[itFirst][itSecond - 1] -> itFirst --
            else -> itSecond--
        }
    }
    return answer.reverse().toString()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    val size = list.size
    if (list.isEmpty() || size == 1) return list
    val maxSeq = Array(size) { 0 }
    val indexes = Array(size) { 0 }
    for (i in 0 until size) {
        maxSeq[i] = 1
        indexes[i] = -1
        for (j in 0 until i) {
            if (list[j] < list[i] && maxSeq[j] + 1 > maxSeq[i]) {
                maxSeq[i] = maxSeq[j] + 1
                indexes[i] = j
            }
        }
    }
    var maxLen = maxSeq[0]
    var pos = 0
    for (i in 0 until size) {
        if (maxSeq[i] > maxLen) {
            maxLen = maxSeq[i]
            pos = i
        }
    }
    val order = Array(maxLen) { 0 }
    var len = 0
    while (pos != -1) {
        order[len] = pos
        pos = indexes[pos]
        len++
    }
    val result = mutableListOf<Int>()
    for (i in order.size - 1 downTo 0) {
        result += list[order[i]]
    }
    return result
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5
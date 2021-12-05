package eu.leeuwis.adventofcode2021.general

import java.io.File

object InputReader {
    @JvmStatic
    fun readFile(obj : Any, filename: String): List<String> {
        val url = obj.javaClass.getResource(filename)
        return File(url.toURI()).readLines()
    }
}

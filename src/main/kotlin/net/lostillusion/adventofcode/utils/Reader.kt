@file:Suppress("unused")

package net.lostillusion.adventofcode.utils

import java.io.File

interface Reader<O> {
    fun <C: MutableCollection<O>> computeToCollection(
        collection: C,
        delimiter: String,
        composer: (i: Int, content: String) -> O
    ): Collection<O>
}

open class AnyReader<I, O>(i: I, iReader: (i: I) -> String): Reader<O> {
    val content = iReader.invoke(i)

    override fun <C : MutableCollection<O>> computeToCollection(
        collection: C,
        delimiter: String,
        composer: (i: Int, content: String) -> O
    ): C {
        val objects = content
            .split(delimiter)
            .map { it.trim() }
            .toMutableList()
            .also { list -> list.removeIf { it.isEmpty() } }
            .mapIndexed(composer)
        collection.addAll(objects)
        return collection
    }
}

open class StringReader<O>(content: String): AnyReader<String, O>(content, { it })
class FileReader<O>(file: File): AnyReader<File, O>(file, { file.readText(Charsets.UTF_8 )})
class ResourceReader<O>(name: String): StringReader<O>(ResourceReader::class.java.getResource(name).readText())
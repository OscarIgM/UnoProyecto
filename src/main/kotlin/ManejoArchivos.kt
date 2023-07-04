import java.io.File
class ManejoArchivos(private val nombreArchivo: String) {
    fun crearArchivo() {
        val archivo = File(nombreArchivo)
        archivo.createNewFile()
    }

    fun escribirEnArchivo(contenido: String) {
        val archivo = File(nombreArchivo)
        archivo.appendText("$contenido\n")
    }

    fun leerArchivo(): List<String> {
        val archivo = File(nombreArchivo)
        return archivo.readLines()
    }

    fun VaciarArchivo() {
        val archivo = File(nombreArchivo)
        archivo.writeText("")
    }

    fun eliminarArchivo() {
        val archivo = File(nombreArchivo)
        archivo.delete()
    }

}
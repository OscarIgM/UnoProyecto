import java.util.LinkedList

class Jugador(val nombre: String) {
    val mano: LinkedList<Carta> = LinkedList()
    var puntaje: Int = 0
    fun turno() {
        var i = 0
        val resto: Int
        resto = i % 2
        // Lógica de juego
        if (resto == 0) {
            // Turno jugador 1
        } else {
            // Turno jugador 2
        }
    }
    fun elegirCarta(): Carta? {
        println("Mano del jugador $nombre:")
        for ((index, carta) in mano.withIndex()) {
            println("${index + 1}. $carta")
        }
        println("Seleccione el número de la carta que desea jugar:")
        val seleccion = readLine()?.toIntOrNull()

        return seleccion?.let { mano.getOrNull(it - 1) }
    }



}


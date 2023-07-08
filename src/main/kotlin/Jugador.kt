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



}


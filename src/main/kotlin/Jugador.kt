import java.util.LinkedList

class Jugador(val nombre: String) {
    val mano: LinkedList<Carta> = LinkedList()
    var puntaje: Int = 0
}
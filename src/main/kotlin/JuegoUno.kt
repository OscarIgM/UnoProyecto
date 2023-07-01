import java.util.Collections
import java.util.LinkedList

class JuegoUno {
    val mazo: LinkedList<Carta> = LinkedList()

    init {
        // Agregar las cartas al mazo
        UnoCartas.values().forEach { carta ->
            mazo.add(Carta(carta.valor, carta.color))
        }
    }
    fun main() {
revolverMazo(mazo)
        println("¡Bienvenido al juego Uno!")


    }
    fun repartirCartas(jugadores: List<Jugador>) {
        repeat(7) {
            for (jugador in jugadores) {
                val carta = mazo.removeFirst()
                jugador.mano.add(carta)
            }
        }
    }
}


fun main() {
    val main = JuegoUno()
    main.main()
}
fun revolverMazo( mazo:LinkedList<Carta>){
    mazo.shuffle();
}
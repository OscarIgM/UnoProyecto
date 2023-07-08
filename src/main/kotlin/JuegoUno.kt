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
        val jugadores: MutableList<Jugador> = mutableListOf() // Crear una lista vacía

        println("¡Bienvenido al juego Uno!")
        println("Ingrese nombre jugador 1")
        val jugador1= readLine();
        val jugadorUno = Jugador(jugador1 ?: "")
        println("Ingrese nombre jugador 2")
        val jugador2= readLine();
        val jugadorDos= jugador2?.let { Jugador(it) };
        println("Los jugadores son"+jugador1+" /" + jugador2);
jugadores.add(jugadorUno);
        repartirCartas(jugadores)
        verMano(jugadorUno);

    }
    fun verMano(jugador: Jugador) {
        println("Mano del jugador ${jugador.nombre}:")
        for (carta in jugador.mano) {
            println(carta.toString())
        }
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
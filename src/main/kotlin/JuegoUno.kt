import java.util.Collections
import java.util.LinkedList

class JuegoUno {
    val mazo: LinkedList<Carta> = LinkedList()
    val deposito: LinkedList<Carta> = LinkedList()
    init {
        // Agregar las cartas al mazo
        UnoCartas.values().forEach { carta ->
            mazo.add(Carta(carta.valor, carta.color))
            revolverMazo(mazo)

        }
    }
    fun main() {
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
        verMano(jugadorUno)
        moverUltimaCarta()
        var i = 0
        val resto: Int
        resto = i % 2
        // Lógica de juego
        if (resto == 0) {
            // Turno jugador 1




        } else {
            // Turno jugador 2
           // jugadorDos.jugar();
        }
    }
    fun moverUltimaCarta() {
        if (mazo.isNotEmpty()) {
            val ultimaCarta = mazo.removeLast()
            deposito.addLast(ultimaCarta)
            println("Se movió la carta al depósito: $ultimaCarta")
        } else {
            println("El mazo está vacío, no se puede mover ninguna carta al depósito.")
        }
    }
    fun validarJugada(cartaSeleccionada: Carta, ultimaCarta: Carta): Boolean {
        if (cartaSeleccionada.color == ultimaCarta.color || cartaSeleccionada.valor == ultimaCarta.valor) {
            return true
        }

        println("La carta seleccionada no se puede jugar sobre la última carta.")
        return false
    }
    fun verMano(jugador: Jugador) {
        println("Mano del jugador ${jugador.nombre}:")
        for (carta in jugador.mano) {
            println(carta.color+" "+carta.valor)
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
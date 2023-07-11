import java.util.Collections
import java.util.LinkedList

class JuegoUno {
    val mazo: LinkedList<Carta> = LinkedList()
    val deposito: LinkedList<Carta> = LinkedList()
    val registroJugadas = ManejoArchivos("registro de partidas.txt");

    init {
        // Agregar las cartas al mazo
        UnoCartas.values().forEach { carta ->
            mazo.add(Carta(carta.name, carta.valor, carta.color))
            revolverMazo(mazo)
        }
    }

    fun main() {
        //Registro Jugadores
        val jugadores: MutableList<Jugador> = mutableListOf()
        println("¡Bienvenido al juego Uno!")
        val jugadorUno = Jugador(escribirNombre("Jugador 1, ingrese su nombre: "))
        val jugadorDos = Jugador(escribirNombre("Jugador 2, ingrese su nombre: "))
        println("Los jugadores son ${jugadorUno.nombre} / ${jugadorDos.nombre}")
        jugadores.add(jugadorUno); jugadores.add(jugadorDos)
        repartirCartas(jugadores);

        moverUltimaCarta()

        // Lógica de juego
        var i = 0
        var continuarJuego = true

        while (continuarJuego) {

            val resto: Int
            resto = i % 2

            // Turno jugador 1
            if (resto == 0) {
                turno(jugadorUno, jugadorDos)
            }
            // Turno jugador 2
            if (resto == 1) {
                turno(jugadorDos, jugadorUno)
            }
            // Terminar juego
            if (jugadorUno.mano.isEmpty() || jugadorDos.mano.isEmpty()) {
                continuarJuego = false
                for (jugador in jugadores) {
                    jugador.calcularPuntaje()
                }
                if (jugadorUno.mano.isEmpty() && jugadorDos.mano.isEmpty()) {
                    registroJugadas.escribirEnArchivo("Empate : ${jugadorUno.nombre} : ${jugadorUno.puntaje}\n Empate: ${jugadorDos.nombre} : ${jugadorDos.puntaje}")
                } else if (jugadorDos.mano.isEmpty()) {
                    registroJugadas.escribirEnArchivo("Ganador: ${jugadorDos.nombre} : ${jugadorDos.puntaje}\n Perdedor: ${jugadorUno.nombre} : ${jugadorUno.puntaje}")
                } else if (jugadorUno.mano.isEmpty()) {
                    registroJugadas.escribirEnArchivo("Ganador: ${jugadorUno.nombre} : ${jugadorUno.puntaje}\n Perdedor: ${jugadorDos.nombre} : ${jugadorDos.puntaje}")
                }
                break
            }
            i++
        }
    }
    fun turno(jugadorActual: Jugador, oponente: Jugador){
        //Agregar accion cartas especiales
        val carta = jugadorActual.elegirCarta()
        if (carta != null) {
            if (validarJugada(carta, deposito.last)) {
                println("Jugada realizada crack makina fiera")
            }
        } else {
            println("Tas re loco")
        }
    }

    fun moverUltimaCarta() {
        //Método que agrega una carta al depósito. Usar cuando depósito este vacio y asi poder jugar
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
    fun repartirCartas(jugadores: List<Jugador>) {
        repeat(7) {
            for (jugador in jugadores) {
                val carta = mazo.removeFirst()
                jugador.mano.add(carta)
            }
        }
    }
    fun transferirCartasDeDepositoAMazo(){
        //Pasa las cartas que hay en depósito a mazo y las elimina del depósito. Usar cuando se acaben cartas en mazo
        for (carta in deposito){
            mazo.addAll(deposito)
            deposito.clear()
        }
    }
    fun revolverMazo(mazo: LinkedList<Carta>) {
        mazo.shuffle();
    }
    fun escribirNombre(texto: String): String {
        var nombre: String?
        do {
            print(texto)
            nombre = readlnOrNull()?.trim()
        } while (nombre.isNullOrEmpty())
        return nombre
    }

}


fun main() {
    val main = JuegoUno()
    main.main()
}




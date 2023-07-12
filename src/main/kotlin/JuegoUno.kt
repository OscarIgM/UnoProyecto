import java.util.*

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
                continuarJuego = menuTurno(jugadorUno, jugadorDos, jugadores)

            }
            // Turno jugador 2
            if (resto == 1) {
                continuarJuego = menuTurno(jugadorDos, jugadorUno, jugadores)
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

    fun menuTurno(jugadorActual: Jugador, oponente: Jugador, jugadores: List<Jugador>): Boolean {
        var opcion: String
        var continuar = true

        println("---- Menú ----")
        verUltimaCartaEnDeposito()
        jugadorActual.verMano()

        do {
            println("---- Seleccione una opcion ----")
            println("q. Tirar carta")
            println("z. Sacar carta")
            println("x. Salir sin terminar la partida")
            print("Ingrese una opción: ")

            opcion = readLine()?.uppercase(Locale.getDefault()) ?: ""

            when (opcion) {

                "Q" -> {
                    verUltimaCartaEnDeposito()
                    turno(jugadorActual, oponente)
                    println("Turno terminado")
                    opcion = "X"
                }

                "X" -> {
                    println("Salir")
                    for (jugador in jugadores) {
                        jugador.calcularPuntaje()
                    }
                    if (jugadorActual.puntaje == oponente.puntaje) {
                        registroJugadas.escribirEnArchivo("Empate : ${jugadorActual.nombre} : ${jugadorActual.puntaje}\n Empate: ${oponente.nombre} : ${oponente.puntaje}\n")
                    } else if (jugadorActual.puntaje > oponente.puntaje) {
                        registroJugadas.escribirEnArchivo("Ganador: ${oponente.nombre} : ${oponente.puntaje}\n Perdedor: ${jugadorActual.nombre} : ${jugadorActual.puntaje}\n")
                    } else {
                        registroJugadas.escribirEnArchivo("Ganador: ${jugadorActual.nombre} : ${jugadorActual.puntaje}\n Perdedor: ${oponente.nombre} : ${oponente.puntaje}\n")
                    }
                    continuar = false
                }

                "Z" -> {
                    println("+ Carta agregada a tu mano")
                    sacarCarta(mazo, jugadorActual)
                    println("Turno terminado")
                    opcion = "X"
                }

                else -> {
                    println("Opción inválida. Por favor, seleccione una opción válida.")
                }
            }
            println()
        } while (opcion != "X")
        return continuar
    }

    fun turno(jugadorActual: Jugador, oponente: Jugador) {
        //Agregar accion cartas especiales
        val carta = jugadorActual.elegirCarta()
        if (carta != null) {
            botarCarta(carta, jugadorActual)
        }
        if (carta != null) {
            if (validarJugada(carta, deposito.last)) {//quedo horrible pero sirve
                if ((carta.nombre == UnoCartas.AMARILLODOSMAS.name) ||
                    (carta.nombre == UnoCartas.AZULDOSMAS.name) ||
                    (carta.nombre == UnoCartas.VERDEDOSMAS.name) ||
                    (carta.nombre == UnoCartas.ROJODOSMAS.name)
                ) {
                    carta.accionDosMas(oponente, mazo)
                }
                if (carta.nombre == UnoCartas.MAS4.name) {
                    carta.accionMasCuatro(oponente, mazo)
                }
                if (carta.nombre == UnoCartas.COLOR_CAMBIO.name) {
                    carta.accionCambioColor()
                }
                if (carta.nombre == UnoCartas.AMARILLOSALTO.name || carta.nombre == UnoCartas.ROJOSALTO.name ||
                    carta.nombre == UnoCartas.AZULSALTO.name || carta.nombre == UnoCartas.VERDESALTO.name
                ) {
                    //carta.accionSalto(turno, jugadores )ESTO NO ESTA D:
                }
                println("Jugada realizada crack makina fiera")

            }
        } else {
            println("Tas re loco, next")
        }
    }

    fun sacarCarta(mazo: LinkedList<Carta>, jugador: Jugador) {
        jugador.mano.add(mazo.last)
        mazo.removeLast()
    }

    fun botarCarta(cartaSeleccionada: Carta, jugador: Jugador) {
        jugador.mano.remove(cartaSeleccionada)
        deposito.add(cartaSeleccionada)
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

    fun verUltimaCartaEnDeposito() {
        if (deposito.isNullOrEmpty()) {
            println("No hay cartas en el deposito")
        } else {
            println("Última carta en depósito: ${deposito.last}")
        }
    }

    fun validarJugada(cartaSeleccionada: Carta, ultimaCarta: Carta): Boolean {
        if (cartaSeleccionada.color == ultimaCarta.color || cartaSeleccionada.valor == ultimaCarta.valor) {
            return true
        }

        println("La carta seleccionada no se puede jugar sobre la última carta.")
        return false
    }

    fun aplicarCartaMasDos(jugadorActual: Jugador, jugadorRival: Jugador) {
        for (i in 1..2) {
            val carta = mazo.removeLast()
            jugadorRival.mano.add(carta)
        }
        println("Se han sumado 2 cartas al jugador rival (${jugadorRival.nombre}).")
    }

    fun repartirCartas(jugadores: List<Jugador>) {
        repeat(7) {
            for (jugador in jugadores) {
                val carta = mazo.removeFirst()
                jugador.mano.add(carta)
            }
        }
    }

    fun transferirCartasDeDepositoAMazo() {
        //Pasa las cartas que hay en depósito a mazo y las elimina del depósito. Usar cuando se acaben cartas en mazo
        for (carta in deposito) {
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
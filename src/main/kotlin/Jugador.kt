import java.util.LinkedList

class Jugador(val nombre: String) {
    val mano: LinkedList<Carta> = LinkedList()
    var puntaje: Int = 0

    fun elegirCarta(): Carta? {
        verMano()
        println("Seleccione el número de la carta:")
        val seleccion = readLine()?.toIntOrNull()
        return seleccion?.let { mano.getOrNull(it - 1) }
    }

    fun verMano() {
        println("Mano del jugador ${nombre}:")
        for ((index, carta) in mano.withIndex()) {
            println("${index + 1}. $carta")
        }
    }

    fun calcularPuntaje() {
        for (carta in mano) {
            this.puntaje += carta.valor;
        }
    }
}


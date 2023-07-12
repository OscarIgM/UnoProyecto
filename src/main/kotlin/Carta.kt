import java.util.*

class Carta(var nombre: String, val valor: Int, val color: String) {

    //fun accionReversa() {}

    fun accionSalto(turno: Int, jugadores: LinkedList<Jugador>): Int {
        //En juegos de pareja, se agrega un turno que no se usa por el oponente
        if(jugadores.size == 2){
            return turno+1
        }
        return turno
    }

    fun accionDosMas(receptor: Jugador, mazo: LinkedList<Carta>) {
        for (i in 1..2) {
            val nuevaCarta1 = mazo.first()
            receptor.mano.add(nuevaCarta1)
            mazo.remove(nuevaCarta1)
            val nuevaCarta2 = mazo.first()
            receptor.mano.add(nuevaCarta2)
            mazo.remove(nuevaCarta2)
            return
        }
    }

    fun accionCambioColor(): String {
        //metodo retorna String con color que el jugador desea
        val colores: LinkedList<String> = LinkedList()
        colores.add("Azul");colores.add("Amarillo");colores.add("Verde");colores.add("Rojo")
        println("Seleccione color: ")
        for ((index, color) in colores.withIndex()) {
            println("${index + 1}. $color")
        }
        val opcion = ingresarEntero(1,4)
        return colores.get(opcion-1)
    }

    fun accionMasCuatro(receptor: Jugador, mazo: LinkedList<Carta>) {
        for (i in 1..4) {
            val nuevaCarta = mazo.get(i)
            receptor.mano.add(nuevaCarta)
            mazo.remove(nuevaCarta)
            return
        }
    }

    fun ingresarEntero(min: Int, max: Int): Int {
        val scanner = Scanner(System.`in`)
        var valor: Int
        while (true) {
            try {
                valor = scanner.nextInt()
                if (valor in min..max) {
                    break
                } else {
                    println("El valor debe estar dentro del rango $min - $max")
                }
            } catch (e: InputMismatchException) {
                println("Por favor, ingresa un valor entero válido")
                scanner.nextLine()
            }
        }
        return valor
    }

    override fun toString(): String {
        if (valor < 20) {
            return "Carta(color='$color',valor=$valor)"
        } else {
            return "$nombre(color='$color', valor=$valor)"
        }
    }

}



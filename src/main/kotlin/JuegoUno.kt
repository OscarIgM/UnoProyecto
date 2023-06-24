class JuegoUno {
    fun main() {
        val juegoUno = JuegoUno()
        val mazo = juegoUno.crearMazoCartas()
        println("¡Bienvenido al juego Uno!")
    }
    fun crearMazoCartas():MutableList<Cartas>{
        val mazoCartas= mutableListOf<Cartas>()
        mazoCartas.addAll(Cartas.values());
        return mazoCartas
    }

}
fun main() {
    val main = JuegoUno()
    main.main()
}
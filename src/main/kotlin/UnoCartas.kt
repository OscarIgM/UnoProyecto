enum class UnoCartas (val valor: Int, val color: String){
    // Cartas ROJAS
    ROJOCERO(0, "Rojo"), ROJOUNO(1, "Rojo"), ROJODOS(2, "Rojo"), ROJOTRES(3, "Rojo"), ROJOCUATRO(4, "Rojo"), ROJOCINCO(5, "Rojo"),
    ROJOSEIS(6, "Rojo"), ROJOSIETE(7, "Rojo"), ROJOOCHO(8, "Rojo"), ROJONUEVE(9, "Rojo"),
    // Cartas ROJAS especiales
    REVERSA(20, "ROJO"), SALTO(20, "ROJO"), DOSMAS(20, "ROJO"),

    // Cartas Azules
    AZULCERO(0, "Azul"), AZULUNO(1, "Azul"), AZULDOS(2, "Azul"), AZULTRES(3, "Azul"), AZULCUATRO(4, "AZUL"), AZULCINCO(5, "Azul"),
    AZULSEIS(6, "Azul"), AZULSIETE(7, "AZUL"), AZULOCHO(8, "Azul"), AZULNUEVE(9, "Azul"),
    // Cartas Azules especiales
    AZULREVERSA(20, "Azul"), AZULSALTO(20, "Azul"), AZULDOSMAS(20, "Azul"),

    // Cartas Amarillas
    AMARILLOCERO(0, "Amarillo"), AMARILLOUNO(1, "Amarillo"), AMARILLODOS(2, "Amarillo"), AMARILLOTRES(3, "Amarillo"), AMARILLOCUATRO(4, "AMARILLO"), AMARILLOCINCO(5, "Amarillo"),
    AMARILLOSEIS(6, "Amarillo"), AMARILLOSIETE(7, "AMARILLO"), AMARILLOOCHO(8, "Amarillo"), AMARILLONUEVE(9, "Amarillo"),
    // Cartas Amarillas especiales
    AMARILLOREVERSA(20, "Amarillo"), AMARILLOSALTO(20, "Amarillo"), AMARILLODOSMAS(20, "Amarillo"),
    // Cartas Verdes
    VERDECERO(0, "Verde"), VERDEUNO(1, "Verde"), VERDEDOS(2, "Verde"), VERDETRES(3, "Verde"), VERDECUATRO(4, "VERDE"), VERDECINCO(5, "Verde"),
    VERDESEIS(6, "Verde"), VERDESIETE(7, "Verde"), VERDEOCHO(8, "Verde"), VERDENUEVE(9, "Verde"),
    // Cartas Verdes especiales
    VERDEREVERSA(20, "Verde"), VERDESALTO(20, "Verde"), VERDEDOSMAS(20, "Verde"),

    // Cartas de colores
    COLOR_CAMBIO(50, "Cambio"), MAS4(50, "Cambio");
}
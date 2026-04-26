package co.coppervault.app.data

data class BookExtra(
    val author: String,
    val publishYear: Int,
    val pages: Int,
    val synopsisEn: String,
    val synopsisEs: String,
)

object BookExtras {
    val byId: Map<String, BookExtra> = mapOf(
        "wok" to BookExtra(
            author = "Brandon Sanderson",
            publishYear = 2010,
            pages = 1007,
            synopsisEn = "On the war-torn world of Roshar, a former soldier turned slave discovers a weapon that could change the course of history. As ancient oaths are spoken anew, the Knights Radiant begin to return.\n\nKaladin, Shallan, and Dalinar each pursue their own paths toward a truth that has been buried for millennia\u2014one that could save or shatter everything.",
            synopsisEs = "En el mundo devastado por la guerra de Roshar, un antiguo soldado convertido en esclavo descubre un arma que podr\u00EDa cambiar el curso de la historia. Mientras se pronuncian antiguos juramentos, los Caballeros Radiantes comienzan a regresar.\n\nKaladin, Shallan y Dalinar persiguen cada uno su propio camino hacia una verdad enterrada durante milenios\u2014una que podr\u00EDa salvar o destruir todo.",
        ),
        "wor" to BookExtra(
            author = "Brandon Sanderson",
            publishYear = 2014,
            pages = 1087,
            synopsisEn = "The war on the Shattered Plains intensifies as Shallan Davar arrives seeking the legendary city of Urithiru. Kaladin struggles with the oaths he has sworn, while Dalinar forges an unlikely alliance.\n\nAncient secrets surface as the Everstorm approaches\u2014a cataclysm that will reshape the world forever.",
            synopsisEs = "La guerra en las Llanuras Quebradas se intensifica cuando Shallan Davar llega en busca de la legendaria ciudad de Urithiru. Kaladin lucha con los juramentos que ha pronunciado, mientras Dalinar forja una alianza improbable.\n\nAntiguos secretos salen a la superficie mientras la Tormenta Eterna se acerca\u2014un cataclismo que remodelar\u00E1 el mundo para siempre.",
        ),
        "mistborn" to BookExtra(
            author = "Brandon Sanderson",
            publishYear = 2006,
            pages = 541,
            synopsisEn = "In a world where ash falls from the sky and mist dominates the night, a young street thief discovers she possesses the powers of a Mistborn. Together with a crew of rebels, she plans to overthrow the immortal Lord Ruler.\n\nWhat follows is a heist story on a cosmic scale, where the metals are the key to everything.",
            synopsisEs = "En un mundo donde la ceniza cae del cielo y la bruma domina la noche, una joven ladrona callejera descubre que posee los poderes de una Nacida de la Bruma. Junto a un grupo de rebeldes, planea derrocar al inmortal Lord Legislador.\n\nLo que sigue es una historia de atraco a escala c\u00F3smica, donde los metales son la clave de todo.",
        ),
        "woa" to BookExtra(
            author = "Brandon Sanderson",
            publishYear = 2007,
            pages = 763,
            synopsisEn = "With the Lord Ruler dead, the crew must navigate a world plunging into chaos. Vin and Elend struggle to hold together a fragile new government while an ancient power stirs beneath the surface.\n\nThe Well of Ascension calls\u2014but its promise of salvation may be the greatest deception of all.",
            synopsisEs = "Con el Lord Legislador muerto, el grupo debe navegar un mundo que se sume en el caos. Vin y Elend luchan por mantener unido un fr\u00E1gil nuevo gobierno mientras un poder antiguo se agita bajo la superficie.\n\nEl Pozo de la Ascensi\u00F3n llama\u2014pero su promesa de salvaci\u00F3n podr\u00EDa ser el mayor enga\u00F1o de todos.",
        ),
        "warbreaker" to BookExtra(
            author = "Brandon Sanderson",
            publishYear = 2009,
            pages = 592,
            synopsisEn = "Two princesses are sent to a court of gods to prevent a war. In the kingdom of Hallandren, where Breath is currency and color is power, nothing is as it seems.\n\nA sentient sword, a god who doesn\u2019t believe in his own divinity, and a revolution brewing in the shadows.",
            synopsisEs = "Dos princesas son enviadas a una corte de dioses para evitar una guerra. En el reino de Hallandren, donde el Aliento es moneda y el color es poder, nada es lo que parece.\n\nUna espada consciente, un dios que no cree en su propia divinidad, y una revoluci\u00F3n que se gesta en las sombras.",
        ),
        "elantris" to BookExtra(
            author = "Brandon Sanderson",
            publishYear = 2005,
            pages = 638,
            synopsisEn = "The city of Elantris was once a place of wonder, its inhabitants transformed into radiant beings of power. Then the magic failed. Now its people are cursed\u2014trapped in bodies that decay but never die.\n\nPrince Raoden is cast into Elantris and must uncover why the transformation broke, before his kingdom falls to political intrigue.",
            synopsisEs = "La ciudad de Elantris fue un lugar de maravillas, sus habitantes transformados en seres radiantes de poder. Luego la magia fall\u00F3. Ahora su pueblo est\u00E1 maldito\u2014atrapados en cuerpos que se deterioran pero nunca mueren.\n\nEl pr\u00EDncipe Raoden es arrojado a Elantris y debe descubrir por qu\u00E9 la transformaci\u00F3n se rompi\u00F3, antes de que su reino caiga ante intrigas pol\u00EDticas.",
        ),
    )
}

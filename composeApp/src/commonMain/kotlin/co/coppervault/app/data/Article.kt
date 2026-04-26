package co.coppervault.app.data

/**
 * A Coppermind-style article / lore entry.
 */
data class Article(
    val id: String,
    val title: String,
    val subtitle: String,
    val worldKey: String,
    val author: String,
    val readMin: Int,
    val spoilerLevel: String,
    val tags: List<String>,
    val sections: List<ArticleSection>,
)

sealed class ArticleSection {
    data class Body(val text: String) : ArticleSection()
    data class PullQuote(val text: String, val attribution: String) : ArticleSection()
    data class Heading(val text: String) : ArticleSection()
}

object Articles {
    val sample = Article(
        id = "art-001",
        title = "The Rhythm of Lost Light",
        subtitle = "A reflection on Investiture transfer between Shards",
        worldKey = "roshar",
        author = "coppermind",
        readMin = 7,
        spoilerLevel = "Stormlight 4",
        tags = listOf("Investiture", "Shards", "Roshar", "Theory"),
        sections = listOf(
            ArticleSection.Body(
                "When Dalinar Kholin opened the perpendicularity at the Battle of Thaylen Field, " +
                    "something unprecedented happened: Honor\u2019s Investiture, long fractured and " +
                    "leaking, was channeled through a living vessel. The implications of this event " +
                    "ripple far beyond Roshar."
            ),
            ArticleSection.Heading("The Nature of Investiture Transfer"),
            ArticleSection.Body(
                "Investiture, in its raw form, is neither solid nor gaseous. It exists in all " +
                    "three Realmic states simultaneously, which is why different worlds manifest " +
                    "it in such different ways \u2014 Stormlight as luminous gas, Breath as a quantum " +
                    "of life force, metals on Scadrial as catalytic keys."
            ),
            ArticleSection.PullQuote(
                "\u201CInvestiture is the lifeblood of the Cosmere, flowing between " +
                    "Shards like tides between continents.\u201D",
                "Khriss, Ars Arcanum"
            ),
            ArticleSection.Body(
                "What makes the Thaylen Field event so remarkable is the directness of the " +
                    "channel. Previous perpendicularity events required a Shard\u2019s direct " +
                    "intervention or the use of existing natural conduits. Dalinar bypassed " +
                    "both mechanisms entirely."
            ),
            ArticleSection.Heading("Implications for Worldhopping"),
            ArticleSection.Body(
                "If a mortal can open a perpendicularity through sheer Connection and Intent, " +
                    "the barriers between worlds become far more permeable than Silverlight " +
                    "scholars previously believed. The Seventeenth Shard has taken notice, " +
                    "and their policy of non-intervention may soon be tested."
            ),
            ArticleSection.PullQuote(
                "\u201CThe most dangerous thing in the Cosmere is not a Shard " +
                    "\u2014 it is a mortal who refuses to be bound by one.\u201D",
                "Hoid"
            ),
            ArticleSection.Body(
                "We expect further developments as the fifth Stormlight book reveals more " +
                    "about the nature of Connection between Shards and their vessels. The " +
                    "Coppermind will continue to document and theorize."
            ),
        ),
    )
}

package co.coppervault.app.data

data class ThreadReply(
    val id: String,
    val author: String,
    val timeAgo: String,
    val body: String,
    val upvotes: Int,
    val depth: Int = 0,
)

data class ThreadExtra(
    val bodyEn: String,
    val bodyEs: String,
    val replies: List<ThreadReply>,
)

object ThreadExtras {
    val byId: Map<String, ThreadExtra> = mapOf(
        "1" to ThreadExtra(
            bodyEn = "We know Hoid appears in nearly every Cosmere novel, always under a different name. But what is his endgame? He collects magic systems like others collect coins.\n\nI believe he is trying to reassemble something that was lost when Adonalsium shattered. Not the Shards themselves\u2014something else. Something that existed before the Shattering.\n\nThoughts?",
            bodyEs = "Sabemos que Hoid aparece en casi todas las novelas del Cosmere, siempre bajo un nombre distinto. Pero \u00BFcu\u00E1l es su objetivo final? Colecciona sistemas de magia como otros coleccionan monedas.\n\nCreo que est\u00E1 intentando reensamblar algo que se perdi\u00F3 cuando Adonalsium se fragment\u00F3. No las Esquirlas en s\u00ED\u2014algo m\u00E1s. Algo que exist\u00EDa antes de la Fragmentaci\u00F3n.\n\n\u00BFOpiniones?",
            replies = listOf(
                ThreadReply("r1", "bridgefour_", "1h", "This is a great theory. I think the key is in the letters between Hoid and the Shards. He explicitly says he wants what was taken from him.", 89),
                ThreadReply("r2", "allomancer_22", "1h", "But Harmony says Hoid is dangerous. Why would he warn about someone trying to do something good?", 45, depth = 1),
                ThreadReply("r3", "breath_hoarder", "45m", "Because Harmony is compromised. We know this from Lost Metal. His judgment can't be trusted.", 62, depth = 1),
                ThreadReply("r4", "stormlight_ink", "30m", "I always assumed Hoid was collecting Investiture as a weapon, not for reconstruction. The way he takes Breaths in Warbreaker is almost predatory.", 34),
                ThreadReply("r5", "elantris_scholar", "20m", "What if Adonalsium was actually a person? And Hoid is trying to bring them back?", 78),
                ThreadReply("r6", "cosmere_nerd", "10m", "Sanderson has confirmed that Adonalsium was a being, not just a force. So resurrection isn\u2019t off the table.", 56, depth = 1),
            ),
        ),
        "2" to ThreadExtra(
            bodyEn = "Just finished Stormlight 5 and I am SHOOK. The revelations about the Heralds completely reframe everything we thought we knew about the Oathpact.\n\nI need to talk about that ending. Without getting into specifics\u2014did anyone else feel like the final oath was both inevitable and completely surprising?",
            bodyEs = "Acabo de terminar Stormlight 5 y estoy EN SHOCK. Las revelaciones sobre los Heraldos replantean por completo todo lo que cre\u00EDamos saber sobre el Pacto.\n\nNecesito hablar sobre ese final. Sin entrar en detalles espec\u00EDficos\u2014\u00BFalguien m\u00E1s sinti\u00F3 que el juramento final era a la vez inevitable y completamente sorprendente?",
            replies = listOf(
                ThreadReply("r1", "stormwarden", "3h", "The foreshadowing was there all along. Go back and read the epigraphs from Part 3\u2014it\u2019s all there.", 142),
                ThreadReply("r2", "cosmere_nerd", "2h", "I cried. Not ashamed to admit it. That scene with the Stormfather was perfection.", 234, depth = 1),
                ThreadReply("r3", "allomancer_22", "2h", "I need a re-read immediately. There are so many details I know I missed.", 89),
                ThreadReply("r4", "bridgefour_", "1h", "Journey before destination, radiant.", 312),
            ),
        ),
        "3" to ThreadExtra(
            bodyEn = "After the events of The Lost Metal, we know Harmony is struggling with the conflicting intents of Preservation and Ruin. Sazed himself admits he cannot act.\n\nBut what if it\u2019s worse than that? What if Autonomy has already found a way in? The red mists in the Southern Scadrial chapters were suspicious.",
            bodyEs = "Despu\u00E9s de los eventos de El Metal Perdido, sabemos que Armon\u00EDa lucha con los intentos conflictivos de Conservaci\u00F3n y Ruina. El propio Sazed admite que no puede actuar.\n\nPero \u00BFy si es peor que eso? \u00BFY si Autonom\u00EDa ya encontr\u00F3 una forma de entrar? Las brumas rojas en los cap\u00EDtulos de Scadrial Sur eran sospechosas.",
            replies = listOf(
                ThreadReply("r1", "breath_hoarder", "6h", "The red color is always a sign of corrupted Investiture. We\u2019ve seen it on Roshar too with the Unmade.", 67),
                ThreadReply("r2", "stormwarden", "5h", "Autonomy doesn\u2019t need to infiltrate directly. She works through avatars. Trell is almost certainly one of them.", 45, depth = 1),
                ThreadReply("r3", "elantris_scholar", "4h", "I think Discord is the real endgame. If Sazed can\u2019t balance the intents, the Shard will eventually rename itself.", 89),
                ThreadReply("r4", "stormlight_ink", "2h", "Sanderson said we\u2019d see more of Scadrial in Era 3. I bet this thread is resolved there.", 23),
            ),
        ),
        "4" to ThreadExtra(
            bodyEn = "Re-reading chapter 12 of Warbreaker and I\u2019m struck by how much Lightsong\u2019s character is set up here. His refusal to believe in his own divinity is both comic and deeply sad.\n\nThe way Sanderson uses the Court of Gods as a lens for exploring faith and identity is underrated.",
            bodyEs = "Releyendo el cap\u00EDtulo 12 de El Aliento de los Dioses y me sorprende cu\u00E1nto se establece el personaje de Luzsong aqu\u00ED. Su negativa a creer en su propia divinidad es a la vez c\u00F3mica y profundamente triste.\n\nLa forma en que Sanderson usa la Corte de los Dioses como lente para explorar la fe y la identidad est\u00E1 infravalorada.",
            replies = listOf(
                ThreadReply("r1", "cosmere_nerd", "18h", "Lightsong is one of the best characters in the Cosmere. His arc is a masterclass in subverted expectations.", 45),
                ThreadReply("r2", "bridgefour_", "12h", "The parallels with Dalinar\u2019s journey of faith are really interesting when you compare the two.", 23, depth = 1),
                ThreadReply("r3", "stormwarden", "8h", "Chapter 12 is also where we first see the mechanics of Awakening in detail. The magic system reveal is woven into character work.", 34),
            ),
        ),
        "5" to ThreadExtra(
            bodyEn = "Sharing my latest portrait of Dalinar Kholin, inspired by the scene where he faces the Thrill at Narak. Charcoal on toned paper, about 12 hours of work.\n\nI tried to capture the weight of the moment\u2014the struggle between the warrior he was and the man he\u2019s becoming.",
            bodyEs = "Comparto mi \u00FAltimo retrato de Dalinar Kholin, inspirado en la escena donde enfrenta la Emoci\u00F3n en Narak. Carb\u00F3n sobre papel tintado, unas 12 horas de trabajo.\n\nIntent\u00E9 capturar el peso del momento\u2014la lucha entre el guerrero que fue y el hombre en que se est\u00E1 convirtiendo.",
            replies = listOf(
                ThreadReply("r1", "allomancer_22", "20h", "This is incredible. The expression in his eyes tells the whole story.", 178),
                ThreadReply("r2", "breath_hoarder", "18h", "Do you take commissions? I\u2019d love to see your take on Vin.", 45, depth = 1),
                ThreadReply("r3", "elantris_scholar", "12h", "The texture work on the Shardplate is phenomenal. You can almost feel the cracks.", 89),
                ThreadReply("r4", "stormwarden", "6h", "Journey before destination, artist. This is stunning work.", 134),
            ),
        ),
        "6" to ThreadExtra(
            bodyEn = "I\u2019ve been analyzing the geometric patterns of AonDor and I believe the Aons are not arbitrary\u2014they map to the actual geography of Arelon.\n\nSpecifically, Aon Rao (the base of all Aons) mirrors the coastline and river system around Elantris. Every modification to an Aon corresponds to a physical feature.",
            bodyEs = "He estado analizando los patrones geom\u00E9tricos del AonDor y creo que los Aones no son arbitrarios\u2014mapean la geograf\u00EDa real de Arelon.\n\nEspec\u00EDficamente, Aon Rao (la base de todos los Aones) refleja la l\u00EDnea costera y el sistema fluvial alrededor de Elantris. Cada modificaci\u00F3n a un Aon corresponde a un rasgo f\u00EDsico.",
            replies = listOf(
                ThreadReply("r1", "stormwarden", "1d", "This is canon! Sanderson confirmed it in a signing. The chasm that broke Elantris\u2019s magic was a geographical change.", 56),
                ThreadReply("r2", "cosmere_nerd", "20h", "The implication is that AonDor would work differently on any other continent. It\u2019s location-locked Investiture.", 34, depth = 1),
                ThreadReply("r3", "breath_hoarder", "12h", "I wonder if this is why Sel\u2019s magic systems are all different by region. ChayShan, Forgery, AonDor\u2014all geographic.", 28),
            ),
        ),
    )
}

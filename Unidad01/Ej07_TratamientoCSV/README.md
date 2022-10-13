## Ejercicio 7. Tratamiento de CSV
Se han descargado desde https://github.com/toUpperCase78/formula1-datasets dos <b>datasets</b> con datos de
la temporada 2021 del campeonato del mundo de Fórmula 1.

El archivo <b>formula1_2021season_raceResults.csv</b> contiene los datos de cada piloto en cada carrera de la
temporada:

    • Track: Lugar donde se celebra la carrera.
    • Position: Posición final del piloto en la carrera. DQ: Piloto descalificado.
    • No: Dorsal del piloto.
    • Driver: Nombre del piloto.
    • Team: Equipo o escudería para la que corre el piloto.
    • StartingGrid Posición inicial del piloto en la carrera.
    • Laps: Vueltas dadas por el piloto.
    • Time: Diferencia de tiempo tras el ganador. DNF: retirado, DNS: no empezó
      EX: si no acaba la carrera, DNS si no la empieza.
    • Points: Puntos conseguidos por la clasificación final.
    • ExtraPoint: Se otorga un punto extra si hace la vuelta rápida en carrera.
    • FastestLap: Vuelta rápida personal en carrera.
El archivo <b>formula1_2021season_sprintQualifyingResults.csv</b> contiene los datos de cada piloto en cada una
de las carreras al sprint puntuables de la temporada:

    • Track: Lugar donde se celebra la carrera.
    • Position: Posición final del piloto en la carrera. DQ: Piloto descalificado.
    • No: Dorsal del piloto.
    • Driver: Nombre del piloto.
    • Team: Equipo o escudería para la que corre el piloto.
    • StartingGrid Posición inicial del piloto en la carrera.
    • Laps: Vueltas dadas por el piloto.
    • Time: Diferencia de tiempo tras el ganador. DNF si no acaba la carrera, DNS si no la empieza.
    • Points: Puntos conseguidos por la clasificación final.

Esta práctica consiste en el tratamiento de la información obtenida de ambos ficheros con el fin de
responder las siguientes preguntas y mostrar estadísticas relacionadas con el campeonato.

    • ¿Qué piloto consiguió más puntos en el campeonato y, por lo tanto, fue campeón del mundo?
    • ¿Qué escudería se alzó con la victoria en el mundial de constructores?
    • ¿Qué piloto consiguió más victorias? ¿Y qué equipo?
    • ¿Qué piloto consiguió subirse más veces al pódium? ¿Y qué equipo?
    • ¿Qué piloto consiguió hacer más poles? ¿Y qué equipo?
    • ¿Qué piloto ha hecho más vueltas rápidas en carrera? ¿Y qué equipo?
    • ¿Qué pilotos han sufrido más abandonos, ya sea en carrera o que no han podido participar?
    • ¿Cómo ha sido el cara a cara en clasificación y en carrera de los pilotos de la misma escudería?
Para comprobar tus resultados, puedes consultar esta información en <a>motor.es</a> y en <a>soymotor.com</a>.
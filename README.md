# App Mutantes

Es una aplicacion que detecta si un humano es mutante basándose en su secuencia de ADN.

## Pre-Requisitos:
Se requiere una base datos MySLQ llamada: mutants_db corriendo en el mismo servidor de la aplicación.

La ejecución de la aplicación se encargará de crear la estructura de datos correspondiente:
            Estructura de tabla para la tabla `dna_entity`
            --
            CREATE TABLE `dna_entity` (
              `id` bigint(20) NOT NULL,
              `dna_sequence` varchar(255) COLLATE utf8_bin DEFAULT NULL,
              `is_mutant` bit(1) NOT NULL
            ) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin


## Servicios disponibles:
1. POST → /mutant/
  Recibe un archivo Json con la siguiente estructura:
  { “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] } 
  Este array de Strings representan cada fila de una tabla de (NxN) con la secuencia del ADN. 
   
  En caso de verificar un mutante, devuelve HTTP 200-OK, en caso contrario un 403-Forbidden
  Las letras de los Strings representa cada base nitrogenada del ADN y solo pueden ser: (A,T,C,G), en caso de introducir una letra distinta devolvera un error 500.
 
2. GET → /stats/
  Devuelve un Json con las estadísticas de las verificaciones de ADN: 
  {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4} 

3. GET →/consultValidatedDna/
  Servicio de consulta que lista los ADN’s verificados con la API

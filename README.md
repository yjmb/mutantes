App Mutantes
============

Es una aplicacion desarrollada con Java Spring Boot, que detecta si un humano es mutante basándose en su secuencia de ADN.  Se conecta con una base de datos MySQL que guarda los ADN’s verificados con la API.

## Servicios disponibles:
1. POST → [IP DE SERVIDOR: PUERTO]/mutant/
  Recibe un archivo Json con la siguiente estructura:
  { “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] } 
  Este array de Strings representan cada fila de una tabla de (NxN) con la secuencia del ADN. 
   
  En caso de verificar un mutante, devuelve HTTP 200-OK, en caso contrario un 403-Forbidden
  Las letras de los Strings representa cada base nitrogenada del ADN y solo pueden ser: (A,T,C,G), en caso de introducir una letra distinta devolvera un error 500.
 
2. GET → [IP DE SERVIDOR: PUERTO]/stats/
  Devuelve un Json con las estadísticas de las verificaciones de ADN: 
  {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4} 

3. GET → [IP DE SERVIDOR: PUERTO]/consultValidatedDna/
  Servicio de consulta que lista los ADN’s verificados con la API

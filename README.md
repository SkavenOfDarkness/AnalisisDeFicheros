Analisis de fichero
===================

OBJETIVO:
El alumno debe demostrar haber adquirido los conocimientos de programación desarrollados durante el curso. Desarrollo de algoritmos de búsqueda y recorrido, desarrollo de subprogramas, utilización de arrays, encapsulación/abstracción en clases y tratamiento de ficheros secuenciales.

ENUNCIADO:
Implementar un programa que permita realizar diferentes análisis de un fichero de texto.
La práctica se realizará de forma individual o en grupos de dos personas como máximo, y en este caso, ambas deben ser del mismo grupo mediano de prácticas.
Las prácticas se depositarán en Campus Extens, la fecha límite de entrega es el 9 de Febrero a las 23:55 para la convocatoria de febrero, y el 13 de Julio para la evaluación extraordinaria. Dicha entrega estará conformada por:
1. el proyecto correspondiente junto con el código fuente.
2. una documentación en PDF con los siguientes apartados:
a. Portada con el título de la práctica, el nombre del autor o autores, el nombre de la asignatura y el profesor de las clases prácticas y el grupo.
b. Introducción que sintetice el enunciado de la práctica.
c. Diseño. Donde se describa el diseño descendente que ha conducido a la solución propuesta.
d. Juego de pruebas con la descripción de los ensayos, pruebas y datos utilizados para comprobar el buen funcionamiento del programa.
e. Conclusiones que sinteticen la experiencia alcanzada, describan los conceptos asimilados y resalten los puntos que han resultado más difíciles de resolver.

DETALLES DEL PROGRAMA:
El usuario debe poder seleccionar que fichero desea analizar, indicando el nombre del fichero.

Al indicar el fichero debe hacer un análisis de dicho fichero donde muestre:
	- número de caracteres
	- número de palabras
	- número de líneas
	
Una vez analizado debe poder realizar las siguientes consultas/operaciones:
letra más repetida y número de apariciones (en caso de empate debe mostrar una de ellas)
número de apariciones de cada carácter
palabra más repetida y número de apariciones (en caso de empate debe mostrar todas)
buscar una palabra (muestra el número de línea y columna de cada aparición)
buscar un texto (muestra el número de línea y columna de cada aparición)
buscar palabras repetidas, mostrar el número de línea y columna de las palabras que aparecen dos veces seguidas en el texto
codificar fichero (crea un nuevo fichero nombre.txt.cod.txt). La codificación consiste en crear una tabla de codificación "aleatoria", cada carácter posible se le asigna uno de los caracteres posibles. Para crear esta tabla debe pedir un número que servirá como semilla en la generación de números aleatorios. De esta forma, un fichero codificado con la misma semilla dará siempre el mismo resultado.
Por ejemplo:

a→p
g→
m→d
s→m
y→g
?→r
">"→(

b→c
h→h
n→!
t→a
z→o
!→s

c→.
i→"
o→)
u→e
.→:
"→u

d→k
j→>
p→t
v→@
,→n
(→b

e→l
k→f
q→y
w→i
:→>
)→j

f→,
l→v
r→w
x→q
@→z
<→x


descodificar fichero, cuando el usuario selecciona el fichero debe poder indicar la semilla utilizada para codificar, entonces el fichero se tratará siempre con esta semilla, descodificándolo al leerlo.

DETALLES DE IMPLEMENTACIÓN:
El fichero de texto, puede contener espacios en blanco, saltos de línea, caracteres del alfabeto (minúsculas sin acentos, ni diéresis) y los siguientes signos . , : @ ? ! " ( ) < >
Podemos considerar que un fichero tendrá como mucho n palabras diferentes, p.e. n = 500, en caso de que se supere el límite debe avisar al usuario y no tratar el fichero.
No se puede usar la clase String para leer palabras o solventar algoritmos, si para leer comandos o el ficheros a abrir.
Cualquier carácter que no esté en la tabla no debe encriptarse/desencriptarse.

OPCIONES ADICIONALES:
1. Ampliar el conjunto de caracteres. Permitiendo mayúsculas, acentos, diéresis, etc. En la comparación de palabras se debe tener en cuenta que hola y Hola son iguales.
2. Permitir reemplazar palabra, reemplaza todas las apariciones de una palabra por la nueva palabra y escribe el fichero, si es un fichero encriptado lo escribe encriptado.
3. Permitir reemplazar texto, reemplaza todas las apariciones de un texto por el nuevo texto, el resultado lo escribe en el fichero, si es un fichero encriptado lo escribe encriptado.

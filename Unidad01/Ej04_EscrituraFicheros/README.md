## Ejercicio 4. Escritura de ficheros de texto
### Ejercicio 4.1:
Crea un programa que pida frases al usuario (una cantidad indeterminada, hasta que introduzca una línea
vacía) y las almacene en un fichero llamado <i>frases.txt</i>. Cada vez que se lance el programa, se destruirá el
fichero <i>frases.txt</i> y se creará uno nuevo que lo reemplace.
### Ejercicio 4.2:
Crea una variante del ejercicio anterior, en la que el fichero se llamará <i>anotaciones.txt</i> y no se destruirá en
cada nueva ejecución, sino que se añadirán las nuevas frases al final de las existentes.
### Ejercicio 4.3:
Crea una versión mejorada del ejercicio anterior, en la que antes de cada frase se anotará la fecha y la hora
en la que se ha realizado dicha anotación.
### Ejercicio 4.4:
Modifica la clase <b>Product</b> añadiendo un método <b>writeFile</b> que reciba un String con la ruta de un fichero y
añada a dicho fichero una línea al final con la información del producto instanciado. Ten en cuenta la
separación entre “,” de los atributos y que hay campos en el archivo csv que tendrás que dejar vacíos al
escribir la línea.
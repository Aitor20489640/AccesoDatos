### Ejercicio 6. Modificación de ficheros con RandomAccessFile
En el ejercicio 4 creaste un método <b>writeFile para la clase Product que recibía un String con la ruta de un
fichero y añadía a dicho fichero una línea al final con la información del producto instanciado.

En ese ejercicio no se tenía en consideración la opción de que el producto ya estuviera en el fichero. Para
ello, y usando la clase RandomAccessFile, vas a mejorar dicho método de forma que si el nombre del
producto ya aparece en el fichero hará una modificación sobre la línea donde aparece en vez de una adición
al final del fichero.
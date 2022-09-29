Crea un programa que lea un fichero CSV (usando las librerías java.io y java.nio) de productos con sus
características y cree una lista de productos sobre el cuál haremos consultas.

El modelado del objeto producto lo tienes dado por la clase Product, donde ya tienes definidos sus
atributos, sus métodos y su constructor, además de la implementación para la interfaz Comparable y su
método CompareTo().

Una vez creada la lista de productos tendremos que permitir al usuario realizar una serie de consultas sobre
los productos:

    • Imprimir la lista de productos

    • Imprimir los nombres de los productos

    • Imprimir los nombres de los productos cuyas unidades en stock sean menor que 10

    • Imprimir el nombre de los productos con unidades en stock mayor de 10 ordenados por unidad de stock 
      de forma descendente

    • Imprimir el número de productos agrupados por proveedor

    • Imprimir el producto con el precio unitario más alto

    • Imprimir el promedio de existencias en almacén
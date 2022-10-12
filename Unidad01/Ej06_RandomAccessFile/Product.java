package Ej06_RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private int supplier;
    private int category;
    private double unitPrice;
    private int unitsInStock;

    public Product(int id, String name, int supplier, int category, double unitPrice, int unitsInStock) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.category = category;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplier=" + supplier +
                ", category=" + category +
                ", unitPrice=" + unitPrice +
                ", unitsInStock=" + unitsInStock +
                '}';
    }


    @Override
    public int compareTo(Product p) {
        if (this.getUnitsInStock() < p.getUnitsInStock())
            return -1;
        else if (this.getUnitsInStock() > p.getUnitsInStock())
            return 1;
        else
            return 0;
    }

    public void writeFile(String ruta) {
        List<String> prodCsv = new ArrayList<>();
        Product product = seekProduct(ruta);
        long pos;

        if (product == null) {
            prodCsv.add(String.valueOf(this.getId()));
            prodCsv.add(this.getName());
            prodCsv.add(String.valueOf(this.getSupplier()));
            prodCsv.add(String.valueOf(this.getCategory()));
            prodCsv.add("");
            prodCsv.add(String.valueOf(this.getUnitPrice()));
            prodCsv.add(String.valueOf(this.getUnitsInStock()));
            prodCsv.add("");
            prodCsv.add("");
            prodCsv.add("");

            try (RandomAccessFile fichero = new RandomAccessFile(ruta, "rw")) {
                fichero.seek(fichero.length());
                fichero.writeBytes("\n");
                fichero.write(String.join(",", prodCsv).getBytes());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            prodCsv.add(String.valueOf(product.getId()));
            prodCsv.add(product.getName());
            prodCsv.add(String.valueOf(this.getSupplier()));
            prodCsv.add(String.valueOf(product.getCategory()));
            prodCsv.add("");
            prodCsv.add(String.valueOf(product.getUnitPrice()));
            prodCsv.add(String.valueOf(product.getUnitsInStock()));
            prodCsv.add("");
            prodCsv.add("");
            prodCsv.add("");

            try (RandomAccessFile fichero = new RandomAccessFile(ruta, "rw")) {
                pos = seekPositionProduct(ruta);
                fichero.seek(pos);
                fichero.write(String.join(",", prodCsv).getBytes());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public Product seekProduct(String ruta) {
        List<Product> listaProducts = new ArrayList<>();
        Path rutaCsv = Path.of(ruta);

        try (Stream<String> lineas = Files.lines(rutaCsv).skip(1)) {
            listaProducts = lineas.map(Product::crearProduct).toList();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (Product p : listaProducts) {
            if (this.getName().equals(p.getName())) {
                return p;
            }
        }

        return null;

    }

    /**
     *
     * @param ruta String con la ruta donde están los objetos almacenados
     * @return Devuelve la posición del objeto con el mismo nombre en el archivo (Devolverá -1 si el objeto no se encuentra)
     */
    public long seekPositionProduct(String ruta) {
        long pos = -1;
        String cadena;

        try (RandomAccessFile fichero = new RandomAccessFile(ruta, "rw")) {
            cadena = fichero.readLine();
            while (cadena != null) {
                if (cadena.contains(this.getName())) {
                    pos = fichero.getFilePointer();
                }
                cadena = fichero.readLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return pos;
    }

    public static Product crearProduct(String line) {

        String[] arr = line.split(",");
        return new Product(
                Integer.parseInt(arr[0]),
                arr[1],
                Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3]),
                Double.parseDouble(arr[5]),
                Integer.parseInt(arr[6]));
    }

}
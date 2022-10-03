import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

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
        Path rutaCsv = Path.of(ruta);

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

        try {
            if (Files.exists(rutaCsv)){
                Files.writeString(rutaCsv, "\n", StandardOpenOption.APPEND);
                Files.write(rutaCsv, String.join(",", prodCsv).getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
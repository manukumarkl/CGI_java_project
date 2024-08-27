package com.main;

import com.bean.Product;
import com.service.ProductService;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PMSApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String con;
        int pid;
        String pname;
        float price;
        String result;

        // Load existing products from file
        ProductService ps = new ProductService(loadProductsFromFile());

        do {
            System.out.println("1: Add Product 2: View all products 3: Delete product 4: Update product price");
            System.out.println("Please enter your choice:");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the product id:");
                    pid = sc.nextInt();
                    System.out.println("Enter the product name:");
                    pname = sc.next();
                    System.out.println("Enter the product price:");
                    price = sc.nextFloat();
                    Product p = new Product(pid, pname, price);
                    result = ps.addProduct(p);
                    System.out.println(result);
                    break;
                case 2:
                    List<Product> listOfProduct = ps.findAllProducts();
                    for (Product product : listOfProduct) {
                        System.out.println(product);   // toString method
                    }
                    break;
                case 3:
                    System.out.println("Enter the product id:");
                    pid = sc.nextInt();
                    result = ps.deleteProduct(pid);
                    System.out.println(result);
                    break;
                case 4:
                    System.out.println("Enter the product id:");
                    pid = sc.nextInt();
                    System.out.println("Enter the new product price:");
                    price = sc.nextFloat();
                    Product p1 = new Product();
                    p1.setPid(pid);
                    p1.setPrice(price);
                    result = ps.updateProduct(p1);
                    System.out.println(result);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("Do you want to continue (y/n)?");
            con = sc.next();
        } while (con.equalsIgnoreCase("y"));

        // Serialize products to file before exiting
        saveProductsToFile(ps.getProductList());
        System.out.println("Thank you!");
        sc.close();
    }

    private static void saveProductsToFile(List<Product> productList) {
        try (FileOutputStream fos = new FileOutputStream("product.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(productList);
        } catch (IOException e) {
        	System.err.println("Error occurred while saving products to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Product> loadProductsFromFile() {
        File file = new File("product.ser");
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (List<Product>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}

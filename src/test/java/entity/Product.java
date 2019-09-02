package entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Product {
    private int id;
    private String name;
    private String category;
}
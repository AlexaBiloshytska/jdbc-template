package entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Products {
    private int id;
    private String name;
    private String category;
}
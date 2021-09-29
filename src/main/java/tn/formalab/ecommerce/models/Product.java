package tn.formalab.ecommerce.models;

import javax.persistence.*;


    @Entity
    @Table(name = "products")
    public class Product {
        @Id
        @GeneratedValue
        public  Integer id;

        @Column(name = "name",nullable = false)
        public  String name;

        @Column(name = "description",nullable = false)
        public  String description;

        @Column(name = "imageUrl",nullable = false)
        public  String imageUrl;

        @Column(name = "price",nullable = false)
        public  Double price;

        @ManyToOne
        @JoinColumn(name = "idcategory")
        public Category category ;





    }

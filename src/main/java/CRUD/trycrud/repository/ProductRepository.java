package CRUD.trycrud.repository;

import CRUD.trycrud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<Product> findAllProducts();

    @Query(value = "SELECT * FROM products WHERE id = :id", nativeQuery = true)
    Product findProductById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO products (name, price) VALUES (:name, :price)", nativeQuery = true)
    void insertProduct(@Param("name") String name, @Param("price") double price);

    @Modifying
    @Transactional
    @Query(value = "UPDATE products SET name = :name, price = :price WHERE id = :id", nativeQuery = true)
    void updateProduct(@Param("id") Long id, @Param("name") String name, @Param("price") double price);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM products WHERE id = :id", nativeQuery = true)
    void deleteProductById(@Param("id") Long id);
}
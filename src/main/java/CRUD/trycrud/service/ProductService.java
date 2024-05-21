package CRUD.trycrud.service;
import CRUD.trycrud.dto.ProductDTO;
import CRUD.trycrud.mapper.ProductMapper;
import CRUD.trycrud.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAllProducts().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productMapper.toDto(productRepository.findProductById(id));
    }

    @Transactional
    public void saveProduct(ProductDTO productDTO) {
        productRepository.insertProduct(productDTO.getName(), productDTO.getPrice());
    }

    @Transactional
    public void updateProduct(ProductDTO productDTO) {
        productRepository.updateProduct(productDTO.getId(), productDTO.getName(), productDTO.getPrice());
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteProductById(id);
    }
}
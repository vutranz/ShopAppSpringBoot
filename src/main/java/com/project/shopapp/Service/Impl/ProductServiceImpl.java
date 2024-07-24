package com.project.shopapp.Service.Impl;

import com.project.shopapp.DTO.ProductDTO;
import com.project.shopapp.DTO.ProductImageDTO;
import com.project.shopapp.Models.Category;
import com.project.shopapp.Models.Product;
import com.project.shopapp.Models.ProductImage;
import com.project.shopapp.Repository.CategoryRepository;
import com.project.shopapp.Repository.ProductImageRepository;
import com.project.shopapp.Repository.ProductRepository;
import com.project.shopapp.Service.CategoryService;
import com.project.shopapp.Service.ProductService;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.exception.InvalidParamException;
import com.project.shopapp.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException {
        Category existingCategory = categoryRepository.findById(productDTO.getCategoryID())
                .orElseThrow(() -> new DataNotFoundException("Category not found with id "
                        + productDTO.getCategoryID()));

        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .category(existingCategory)
                .build();
        return  productRepository.save(product);
    }

    @Override
    public Product getProductById(Long productId) throws Exception{
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("can not find product with id"+ productId));
    }

    public Page<ProductResponse> getAllProducts(PageRequest pageRequest){
        return productRepository.findAll(pageRequest).map(ProductResponse::fromProduct);
    }
    public Product updateProduct(Long id, ProductDTO productDTO) throws Exception {
        Product existingProduct = getProductById(id);
        if(existingProduct != null)
        {
            Category existingCategory =  categoryRepository
                    .findById(productDTO.getCategoryID())
                    .orElseThrow(()-> new DataNotFoundException("Category not found with id "
                            + productDTO.getCategoryID()));
            existingProduct.setName(productDTO.getName());
            existingProduct.setCategory(existingCategory);
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            existingProduct.setDescription(productDTO.getDescription());
            return productRepository.save(existingProduct);
        }
       return null;
    }
    public void deleteProduct(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        optionalProduct.ifPresent(productRepository::delete);

    }
    public boolean existsByName(String name ){
        return productRepository.existsByName(name);
    }

    public  ProductImage createProductImage(Long productId, ProductImageDTO productImageDTO) throws Exception {
        Product existingProduct =  productRepository
                .findById(productId)
                .orElseThrow(()-> new DataNotFoundException("Category not found with id "
                        + productImageDTO.getProductId()));
        ProductImage productImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        //không cho insert quá 5 ảnh cho 1 sp
        int size = productImageRepository.findByProductId(productId).size();
        if(size >=5 ){
            throw new InvalidParamException("number of img must be <= 5");
        }
        return productImageRepository.save(productImage);
    }
}

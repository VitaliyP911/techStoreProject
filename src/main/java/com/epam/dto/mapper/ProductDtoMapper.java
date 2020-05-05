package com.epam.dto.mapper;

import com.epam.dto.ProductDto;
import com.epam.entity.Entity;
import com.epam.entity.Product;

public class ProductDtoMapper implements DtoMapper<ProductDto> {

    @Override
    public ProductDto mapFromEntityToDto(Entity entity) {
        Product product = (Product) entity;
        return new ProductDto(product.getId(), product.getName(),
                product.getPrice(), product.getCategory(), product.getGuarantee());
    }
}

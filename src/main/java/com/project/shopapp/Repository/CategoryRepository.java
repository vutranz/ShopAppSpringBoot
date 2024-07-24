package com.project.shopapp.Repository;

import com.project.shopapp.Models.Category;
import com.project.shopapp.Models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}

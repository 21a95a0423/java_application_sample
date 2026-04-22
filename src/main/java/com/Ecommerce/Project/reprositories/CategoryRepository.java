package com.Ecommerce.Project.reprositories;

import com.Ecommerce.Project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

package event.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import event.manager.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}

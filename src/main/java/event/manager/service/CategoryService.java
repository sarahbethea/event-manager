package event.manager.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import event.manager.controller.model.CategoryData;
import event.manager.controller.model.EventData;
import event.manager.dao.CategoryDao;
import event.manager.dao.EventDao;
import event.manager.entity.Category;
import event.manager.entity.Event;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private EventDao eventDao;
	
	
	@Transactional(readOnly = false)
	public CategoryData saveCategory(CategoryData categoryData) {
		
		Category category = categoryData.toCategory();
		Category dbCategory = categoryDao.save(category);
		
		return new CategoryData(dbCategory);
	}
	
	private Category findOrCreateCategory(Long categoryId) {
		Category category;
		
		if(Objects.isNull(categoryId)) {
			category = new Category();
		} else {
			category = findCategoryById(categoryId);
		}
		return category;
	}

	private Category findCategoryById(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly = true) 
	public List<CategoryData> retrieveAllCategories() {
		List<Category> categories = categoryDao.findAll();
		List<CategoryData> result = new LinkedList<>();
		
		for (Category category : categories) {
			CategoryData cd = new CategoryData(category);
			
			result.add(cd);
		}
		
		return result;
	}
	
	@Transactional(readOnly = false)
	public void deleteCategoryById(Long categoryId) {
		Category category = findCategoryById(categoryId);
		categoryDao.delete(category);
		
	}
	
	@Transactional(readOnly = false)
	public CategoryData addEventToCategory(Long categoryId, Long eventId) {
		Category category = categoryDao.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category not found."));
		Event event = eventDao.findById(eventId).orElseThrow(() -> new NoSuchElementException("Event not found"));
		
		category.getEvents().add(event);
		event.getCategories().add(category);
		
		categoryDao.save(category);
		return new CategoryData(category);
	}
	

}

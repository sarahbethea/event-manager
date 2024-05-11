package event.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import event.manager.controller.model.CategoryData;
import event.manager.controller.model.EventData;
import event.manager.service.CategoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CategoryData createCategory(@RequestBody CategoryData categoryData) {
		log.info("Creating new category");
		return categoryService.saveCategory(categoryData);
		
	}
	
	@PostMapping("/{categoryId}/addevent/{eventId}/")
	public CategoryData addEventToCategory(@PathVariable Long categoryId, 
				@PathVariable Long eventId) {
		log.info("Adding category {} to event {}", categoryId, eventId);
		return categoryService.addEventToCategory(categoryId, eventId);
		
	}
	
	@GetMapping("/all")
	public List<CategoryData> listAllCategories() {
		log.info("Listing all categories");
		return categoryService.retrieveAllCategories();
		
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public Map<String, String> deleteCategoryById(@PathVariable Long categoryId) {
		log.info("Deleting category with ID=" + categoryId);
		categoryService.deleteCategoryById(categoryId);
	
		return Map.of("message", "Deletion of category with ID=" + categoryId + " was successful");
	}
	
	
}

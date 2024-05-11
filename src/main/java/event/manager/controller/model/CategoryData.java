package event.manager.controller.model;

import java.util.HashSet;
import java.util.Set;

import event.manager.entity.Category;
import event.manager.entity.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryData {
	
	private Long categoryId;
	private String categoryName;
	private Set<Long> eventIds = new HashSet<>();
	
	public CategoryData(Category category) {
		categoryId = category.getCategoryId();
		categoryName = category.getCategoryName();
		
		for (Event event : category.getEvents()) {
			eventIds.add(event.getEventId());
			
		}
	}
	
	public Category toCategory() {
		Category category = new Category();
		
		category.setCategoryId(categoryId);;
		category.setCategoryName(categoryName);;
		
		return category;
	}

}

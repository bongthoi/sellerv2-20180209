package seller.service;

import java.util.List;

import seller.domain.About;


public interface IAbout {
	List<About> findAll();

	void save(About about);
	About findOne(String id);
	
}

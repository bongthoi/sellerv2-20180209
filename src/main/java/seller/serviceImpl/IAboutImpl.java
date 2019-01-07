package seller.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seller.domain.About;
import seller.repository.AboutRepository;
import seller.service.IAbout;


@Service
public class IAboutImpl implements IAbout {
	
	@Autowired
	private AboutRepository aboutRepository;

	@Override
	public List<About> findAll() {
		// TODO Auto-generated method stub
		return aboutRepository.findAll();
	}

	@Override
	public void save(About about) {
		// TODO Auto-generated method stub
		aboutRepository.save(about);
	}

	@Override
	public About findOne(String id) {
		// TODO Auto-generated method stub
		return aboutRepository.findOne(id);
	}

	
}

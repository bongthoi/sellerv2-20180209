package seller.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import seller.domain.Contact;
import seller.repository.ContactRepository;
import seller.service.IContact;

@Service
public class IContactImpl  implements IContact{
	
	@Autowired
	private ContactRepository contactRepository;
	  @Override
	    public Iterable<Contact> findAll() {
	        return contactRepository.findAll();
	    }
	    @Override
	    public Contact findOne(int id) {
	        return contactRepository.findOne(id);
	    }

	    @Override
	    public void save(Contact contact) {
	        contactRepository.save(contact);
	    }

	    @Override
	    public void delete(int id) {
	        contactRepository.delete(id);
	    }

		@Override
		public Page<Contact> findAll(Pageable pageable) {
			// TODO Auto-generated method stub
			 return contactRepository.findAll(pageable);
		}
		@Override
		public boolean exists(int id) {
			// TODO Auto-generated method stub
			return contactRepository.exists(id);
		}
}

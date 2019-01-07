package seller.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import seller.domain.Contact;

public interface IContact {

	Page<Contact> findAll(Pageable pageable);
    Iterable<Contact> findAll();

    Contact findOne(int id);

    void save(Contact contact);

    void delete(int id);
    boolean exists(int id);
}

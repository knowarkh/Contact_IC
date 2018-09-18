import java.util.Optional;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

public class ContactServiceMockTest extends EasyMockSupport {
	@Rule
	public EasyMockRule rule = new EasyMockRule(this);
	
	@TestSubject
	private ContactService service = new ContactService();
	
	@Mock
	private IContactDAO contactDao;
	
	@Test(expected = ContactDuplicateException.class)
	public void testAddContact() throws ContactDuplicateException {
		//1 - Enregistrement des comportements de mocks
		EasyMock.expect(contactDao.findByName("Ce que vous voulez")).andReturn(new Contact());
		//2 - Fin d'enregistrement
		replayAll();
		//3 - Test
		service.add("Ce que vous voulez");
	}
	
	//A REVOIR
	@Test
	public void testDeleteContact() throws ContactNotFound {
		Contact contact = new Contact();
		contact.setNom("ContactInexistant");
		EasyMock.expect(contactDao.findByName("ContactInexistant")).andReturn(null);
		contactDao.delete(contact);
		replayAll();
		service.delete(contact.getNom());
	}
}

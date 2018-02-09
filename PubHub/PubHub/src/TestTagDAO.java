import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.dao.*;
import examples.pubhub.utilities.DAOUtilities;
import java.util.ArrayList;
import java.util.List;

public class TestTagDAO {
	public static void main(String[] args){
		TagDAO dao = new TagDAOImpl();
/*		dao.addTag("1111111111112", "Second Book");
		dao.addTag("1111111111112", "Third Book");	*/	
		List<Tag> tags = dao.getAllTags();
//		dao.addTag("1111111111116", "Holla");
		for (int i = 0; i < tags.size(); i++){
			Tag t = tags.get(i);
			System.out.println("ISBN: " + t.getIsbn13() + " Tag: " + t.getTag());
		}
		
	
		
	}
}

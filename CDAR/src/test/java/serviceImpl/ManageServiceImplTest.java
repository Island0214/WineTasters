package serviceImpl;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ManageService;

/**
 * ManageServiceImpl Tester.
 *
 * @author <mac xzh>
 * @version 1.0
 * @since <pre>七月 18, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class ManageServiceImplTest {

    @Autowired
    private ManageService manageService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: uploadDocument(File file)
     */
    @Test
    public void testUploadDocument() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteDocument(Integer documentID)
     */
    @Test
    public void testDeleteDocument() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getPageNumberByRex(String rex, int max)
     */
    @Test
    public void testGetPageNumberByRex() throws Exception {
        System.out.println(manageService.getPageNumberByRex("生活",10));
    }

    /**
     * Method: getDocumentsByRex(String rex, int page, int max)
     */
    @Test
    public void testGetDocumentsByRex() throws Exception {
        manageService.getDocumentsByRex("生活",3,10);
    }

    /**
     * Method: getPageNumber(String category, int max)
     */
    @Test
    public void testGetPageNumber() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDocumentsByCategory(String category, int page, int max)
     */
    @Test
    public void testGetDocumentsByCategory() throws Exception {
//TODO: Test goes here... 
    }


} 

package com.example.propconnectsolo.models;

import com.example.propconnectsolo.repositories.PropertyRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = PropConnectSoloApplication.class)
//@AutoConfigureMockMvc
public class PropsIntegrationTests {

//    private User testUser;
//    private HttpSession httpSession;
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    UserRepository userDao;
//
//    @Autowired
//    PropertyRepository propDao;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Before
//    public void setup() throws Exception {
//
//        testUser = userDao.findByUsername("testUser");
//
//        // Creates the test user if not exists
//        if(testUser == null){
//            User newUser = new User();
//            newUser.setUsername("testUser");
//            newUser.setPassword(passwordEncoder.encode("pass"));
//            newUser.setEmail("testUser@codeup.com");
//            testUser = userDao.save(newUser);
//        }
//
//        // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
//        httpSession = this.mvc.perform(post("/login").with(csrf())
//                        .param("username", "testUser")
//                        .param("password", "pass"))
//                .andExpect(status().is(HttpStatus.FOUND.value()))
//                .andExpect(redirectedUrl("/ads"))
//                .andReturn()
//                .getRequest()
//                .getSession();
//    }

//    @Test
//    public void contextLoads() {
//        // Sanity Test, just to make sure the MVC bean is working
//        assertNotNull(mvc);
//    }
//
//    @Test
//    public void testIfUserSessionIsActive() throws Exception {
//        // It makes sure the returned session is not null
//        assertNotNull(httpSession);
//    }

}

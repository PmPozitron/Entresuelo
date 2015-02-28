package pmp;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.Level;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.mock.web.MockServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.springframework.mock.web.*;

public class MainControllerTest {
	
    @Resource
    private FilterChainProxy springSecurityFilterChain;

    private static Logger logger; // = Logger.getLogger(MainController.class);
    private static ConsoleAppender consoleLog; // = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_ERR);
    
    InternalResourceViewResolver viewResolver;
    MockMvc mockMvc;
    MainController controller;
    
    ApplicationContext applicationContext;
    
    @BeforeClass
    public static void setupClass() {
        logger = Logger.getLogger(MainControllerTest.class);
        consoleLog = new ConsoleAppender(new SimpleLayout(), ConsoleAppender.SYSTEM_OUT);
        logger.addAppender(consoleLog);
        logger.setLevel(Level.ALL);        
        
    }   // end public void setup() {}
    
    public MainControllerTest () {
        super();
        
        applicationContext = new ClassPathXmlApplicationContext("classpath:application-security.xml");
        applicationContext.getResource("classpath:web.xml");        
        this.viewResolver = new InternalResourceViewResolver();
        this.viewResolver.setPrefix("/WEB-INF/pages/");
        this.viewResolver.setSuffix(".jsp");
//        this.viewResolver = (InternalResourceViewResolver)this.applicationContext.getBean("org.springframework.web.servlet.view.InternalResourceViewResolver");
        
        DelegatingFilterProxy filterChain = new DelegatingFilterProxy ();        
        filterChain.setServletContext(new MockServletContext());
//        this.controller = new MainController();
        this.controller = (MainController) this.applicationContext.getBean("mainController");
                
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller)
        		.setViewResolvers(this.viewResolver).build();
        		
//        		      .addFilter(filterChain, "/*")
//        this.mockMvc = standaloneSetup(this.controller).build();       
        
        logger.debug(new Date() + " public MainControllerTests () {}");        
    }   // end public MainControllerTests () {}
    
//    @Before
//    public void setup() {               
//        this.logger.debug(new Date() + " public void setup() {}");      
//    }   // end public void setup() {}
    
    @Test
    public void thatRootRequestReturnsHelloView() throws Exception {       
        logger.debug(new Date() + " public void thatRootRequestReturnsHelloView() throws Exception {}\n" 
                + this.mockMvc.perform(get("/")).andReturn().getModelAndView().getViewName());
                
        this.mockMvc.perform(get("/")).andExpect(view().name("hello"));
    }   // end public void thatRootRequestReturnsHelloView() {}
    
    @Test
    public void thatWelcomeRequestReturnsHelloView() throws Exception {      
        logger.debug(new Date() + " public void thatWelcomeRequestReturnsHelloView() throws Exception {}\n" + 
                this.mockMvc.perform(get("/welcome")).andReturn().getModelAndView().getViewName());
        
        this.mockMvc.perform(get("/welcome")).andExpect(view().name("hello"));        
    }   // end public void thatAdminRequestReturnsLoginView() throws Exception {}
    
    @Test
    public void thatAdminRequestReturnsAdminView() throws Exception {        
        logger.debug(new Date() + " public void thatAdminRequestReturnsAdminView() throws Exception {}\n" 
                + this.mockMvc.perform(get("/admin")).andReturn().getModelAndView().getViewName());        
                        
        this.mockMvc.perform(get("/admin")).andExpect(view().name("admin"));   
    }   // end public void thatAdminRequestReturnsLoginView() throws Exception {}

    @Test
    public void thatLoginRequestReturnsLoginView() throws Exception {
        logger.debug(new Date() + " public void thatLoginRequestReturnsLoginView() throws Exception {}\n" 
                + this.mockMvc.perform(get("/login")).andExpect(forwardedUrl("/WEB-INF/pages/login.jsp")));
                               
                //.andReturn().getModelAndView().getViewName());
        
        this.mockMvc.perform(get("/login")).andExpect(view().name("login"));
    }   // end public void thatLoginRequestReturnsLoginView() throws Exception {}
    
    
//    @Ignore
    @Test
    public void that403RequestReturns403View() throws Exception {
        MainControllerTest.logger.debug(this.viewResolver.getApplicationContext().getEnvironment().getProperty("propertySources"));
        
    	        
        UserDetailsManager userDetailsService = applicationContext.getBean(UserDetailsManager.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername ("non_grata");        
        User user = new User (userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());        
        Authentication token = new UsernamePasswordAuthenticationToken (user, null);                
        SecurityContextHolder.getContext().setAuthentication(token);
        
        logger.debug(new Date() + " public void thatLoginRequestReturnsLoginView() throws Exception {}\n" 
                + this.mockMvc.perform(get("/403")).andReturn().getModelAndView().getViewName());
        
        this.mockMvc.perform(get("/403")).andExpect(view().name("403"));
        
        applicationContext = null;
                
    }   // end public void that403RequestReturns403View() throws Exception {}   
    
    @Ignore
    @Test
    public void thatAuthenticationIsRequested() throws Exception {   	
    	
    	this.mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "user1")
                .param("password", "password1")
        )
                .andExpect(status().isUnauthorized());
    }	// end public void thatAuthenticationIsRequested() {}

//    @After
//    public void tearDown() {
//        this.consoleLog.close();
//        this.consoleLog = null;    	
//    }   // end public void tearDown() {}
    
}   // end public class MainControllerTests {]
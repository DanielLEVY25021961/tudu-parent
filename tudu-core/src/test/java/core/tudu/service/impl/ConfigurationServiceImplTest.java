package tudu.service.impl;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tudu.domain.dao.IPropertyDAO;
import tudu.domain.model.PropertyConfiguration;

public class ConfigurationServiceImplTest {

    /**
     * propertyDAO : IPropertyDAO :<br/>
     * .<br/>
     */
    IPropertyDAO propertyDAO = null;

    
    
    /**
     * configurationService : ConfigurationServiceImpl :<br/>
     * .<br/>
     */
    ConfigurationServiceImpl configurationService = null;

    
    
    
    /**
     * method setUp() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Before
    public void setUp() {
    	this.configurationService = new ConfigurationServiceImpl();
    	this.propertyDAO = createMock(IPropertyDAO.class);
    	this.configurationService.setPropertyDAO(this.propertyDAO);
    }

    
    
    /**
     * method getProperty() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test
    public void getProperty() {    	
    	PropertyConfiguration propertyConfiguration = new PropertyConfiguration();
		propertyConfiguration.setKey("key");
		propertyConfiguration.setValue("value");
		expect(this.propertyDAO.getProperty("key")).andStubReturn(propertyConfiguration);

		PropertyConfiguration defaultProperty = new PropertyConfiguration();
		defaultProperty.setKey("default");
		defaultProperty.setValue("default");
		expect(this.propertyDAO.getProperty((String) anyObject())).andStubReturn(
				defaultProperty);

		replay(this.propertyDAO);
		
		PropertyConfiguration test = this.configurationService.getProperty("key");
		assertEquals("value", test.getValue());
		
		test = this.configurationService.getProperty("anything");
		assertEquals("default", test.getValue());
		
		verify(this.propertyDAO);
    }

    
    
    
    /**
     * method updateEmailProperties() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    @Test
    public void updateEmailProperties() {
        PropertyConfiguration hostProperty = new PropertyConfiguration();
        hostProperty.setKey("smtp.host");
        hostProperty.setValue("value");
        expect(this.propertyDAO.getProperty("smtp.host")).andReturn(hostProperty);
        this.propertyDAO.updateProperty(hostProperty);

        PropertyConfiguration portProperty = new PropertyConfiguration();
        portProperty.setKey("smtp.port");
        portProperty.setValue("value");
        expect(this.propertyDAO.getProperty("smtp.port")).andReturn(portProperty);
        this.propertyDAO.updateProperty(portProperty);

        PropertyConfiguration userProperty = new PropertyConfiguration();
        userProperty.setKey("smtp.user");
        userProperty.setValue("value");
        expect(this.propertyDAO.getProperty("smtp.user")).andReturn(userProperty);
        this.propertyDAO.updateProperty(userProperty);

        PropertyConfiguration passwordProperty = new PropertyConfiguration();
        passwordProperty.setKey("smtp.password");
        passwordProperty.setValue("value");
        expect(this.propertyDAO.getProperty("smtp.password")).andReturn(
                passwordProperty);
        this.propertyDAO.updateProperty(passwordProperty);

        PropertyConfiguration fromProperty = new PropertyConfiguration();
        fromProperty.setKey("smtp.host");
        fromProperty.setValue("value");
        expect(this.propertyDAO.getProperty("smtp.from")).andReturn(fromProperty);
        this.propertyDAO.updateProperty(fromProperty);

        replay(this.propertyDAO);
        this.configurationService.updateEmailProperties("host", "port", "user",
                "password", "from");
        assertEquals("host", hostProperty.getValue());
        assertEquals("port", portProperty.getValue());
        assertEquals("user", userProperty.getValue());
        assertEquals("password", passwordProperty.getValue());
        assertEquals("from", fromProperty.getValue());
    }

    
    
}

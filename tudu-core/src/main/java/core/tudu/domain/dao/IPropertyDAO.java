package tudu.domain.dao;

import tudu.domain.model.PropertyConfiguration;

/**
 * DAO for the PropertyConfiguration table.
 * 
 * @author Julien Dubois
 */
public interface IPropertyDAO {

    /**
     * Find a property by key.
     * 
     * @param key
     *            The property key
     * @return The property
     */
    PropertyConfiguration getProperty(String key);

    /**
     * Update a property.
     * 
     * @param property
     *            The property to update
     */
    void updateProperty(PropertyConfiguration property);

    /**
     * Save a property.
     * 
     * @param property
     *            The property to save
     */
    void saveProperty(PropertyConfiguration property);
}

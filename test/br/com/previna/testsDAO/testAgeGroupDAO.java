package br.com.previna.testsDAO;

import br.com.previna.dao.AgeGroupDAO;
import br.com.previna.model.AgeGroup;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class testAgeGroupDAO {
    @Test
    public void testCreationTableAgeGroup() {
        AgeGroupDAO ageGroupDAO = new AgeGroupDAO();
        for (int i = 0; i < 1500; i++) {
            AgeGroup ageGroup = new AgeGroup();
            ageGroup.setName(i+"oi");
            ageGroupDAO.save(ageGroup);
        }
        List<AgeGroup> userList = ageGroupDAO.listAll(AgeGroup.class);
        assertNotNull(userList);
        assertTrue(userList.size() > 0);
    }
}

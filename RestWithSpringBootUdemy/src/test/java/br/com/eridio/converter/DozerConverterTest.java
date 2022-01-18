package br.com.eridio.converter;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.eridio.converter.mocks.MockPerson;
import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;

public class DozerConverterTest {

    MockPerson inputObject;

    @Before
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO output = DozerConverter.parseObject(inputObject.mockEntity(), PersonVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("First Name Test: 0", output.getFirstName());
        assertEquals("Last Name Test: 0", output.getLastName());
        assertEquals("Address Test: 0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("First Name Test: 0", outputZero.getFirstName());
        assertEquals("Last Name Test: 0", outputZero.getLastName());
        assertEquals("Address Test: 0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        PersonVO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("First Name Test: 7", outputSeven.getFirstName());
        assertEquals("Last Name Test: 7", outputSeven.getLastName());
        assertEquals("Address Test: 7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());

        PersonVO outputEleven = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputEleven.getKey());
        assertEquals("First Name Test: 12", outputEleven.getFirstName());
        assertEquals("Last Name Test: 12", outputEleven.getLastName());
        assertEquals("Address Test: 12", outputEleven.getAddress());
        assertEquals("Male", outputEleven.getGender());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = DozerConverter.parseObject(inputObject.mockVO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test: 0", output.getFirstName());
        assertEquals("Last Name Test: 0", output.getLastName());
        assertEquals("Address Test: 0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parseVOListToEntityListTest() {
        List<Person> outputList = DozerConverter.parseListObjects(inputObject.mockVOList(), Person.class);

        Person outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test: 0", outputZero.getFirstName());
        assertEquals("Last Name Test: 0", outputZero.getLastName());
        assertEquals("Address Test: 0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        Person outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test: 7", outputSeven.getFirstName());
        assertEquals("Last Name Test: 7", outputSeven.getLastName());
        assertEquals("Address Test: 7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());

        Person outputEleven = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputEleven.getId());
        assertEquals("First Name Test: 12", outputEleven.getFirstName());
        assertEquals("Last Name Test: 12", outputEleven.getLastName());
        assertEquals("Address Test: 12", outputEleven.getAddress());
        assertEquals("Male", outputEleven.getGender());

    }
    
}

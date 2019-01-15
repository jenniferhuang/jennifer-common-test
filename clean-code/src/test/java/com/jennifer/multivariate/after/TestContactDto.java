package com.jennifer.multivariate.after;


import org.testng.annotations.Test;

/**
 * Created by com.jennifer.huang on 5/17/18.
 */
public class TestContactDto {


    @Test
    public void testCreateContact() {
        ContactDto contactDto1 = new ContactDto.ContactDtoBuilder("12343143143").build();
        ContactDto contactDto2 = new ContactDto.ContactDtoBuilder("com.jennifer", "huang").build();
        ContactDto contactDto3 = new ContactDto.ContactDtoBuilder("com.jennifer", "huang").withBusinessPhone("12343143143").withEmail3("com.jennifer.huang@ringcentral.com").build();
    }
}

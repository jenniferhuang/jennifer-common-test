package com.jennifer.multivariate.after;


import org.testng.annotations.Test;

/**
 * Created by jennifer.huang on 5/17/18.
 */
public class TestContactDto {


    @Test
    public void testCreateContact() {
        ContactDto contactDto1 = new ContactDto.ContactDtoBuilder("12343143143").build();
        ContactDto contactDto2 = new ContactDto.ContactDtoBuilder("jennifer", "huang").build();
        ContactDto contactDto3 = new ContactDto.ContactDtoBuilder("jennifer", "huang").withBusinessPhone("12343143143").withEmail3("jennifer.huang@ringcentral.com").build();


    }
}

package com.jennifer.multivariate.after;

import com.jennifer.multivariate.before.ContactAddressDto;

public class ContactDto {

    private long id;
    private String uri;
    private String availability;
    private String firstName;
    private String lastName;
    private String middleName;
    private String nickName;
    private String company;
    private String jobTitle;
    private String homePhone;
    private String homePhone2;
    private String businessPhone;
    private String businessPhone2;
    private String mobilePhone;
    private String businessFax;
    private String companyPhone;
    private String assistantPhone;
    private String carPhone;
    private String otherPhone;
    private String otherFax;
    private String callbackPhone;
    private String email;
    private String email2;
    private String email3;
    private ContactAddressDto homeAddress;
    private ContactAddressDto businessAddress;
    private ContactAddressDto otherAddress;
    private String birthday;
    private String webPage;
    private String notes;

    private ContactDto() {
    }

    public long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getAvailability() {
        return availability;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompany() {
        return company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getHomePhone2() {
        return homePhone2;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public String getBusinessPhone2() {
        return businessPhone2;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getBusinessFax() {
        return businessFax;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getAssistantPhone() {
        return assistantPhone;
    }

    public String getCarPhone() {
        return carPhone;
    }

    public String getOtherPhone() {
        return otherPhone;
    }


    public String getOtherFax() {
        return otherFax;
    }


    public String getCallbackPhone() {
        return callbackPhone;
    }


    public String getEmail() {
        return email;
    }


    public String getEmail2() {
        return email2;
    }


    public String getEmail3() {
        return email3;
    }


    public ContactAddressDto getHomeAddress() {
        return homeAddress;
    }


    public ContactAddressDto getBusinessAddress() {
        return businessAddress;
    }


    public ContactAddressDto getOtherAddress() {
        return otherAddress;
    }


    public String getBirthday() {
        return birthday;
    }


    public String getWebPage() {
        return webPage;
    }


    public String getNotes() {
        return notes;
    }

    public static final class ContactDtoBuilder {
        private long id;
        private String uri;
        private String availability;
        private String firstName;
        private String lastName;
        private String middleName;
        private String nickName;
        private String company;
        private String jobTitle;
        private String homePhone;
        private String homePhone2;
        private String businessPhone;
        private String businessPhone2;
        private String mobilePhone;
        private String businessFax;
        private String companyPhone;
        private String assistantPhone;
        private String carPhone;
        private String otherPhone;
        private String otherFax;
        private String callbackPhone;
        private String email;
        private String email2;
        private String email3;
        private ContactAddressDto homeAddress;
        private ContactAddressDto businessAddress;
        private ContactAddressDto otherAddress;
        private String birthday;
        private String webPage;
        private String notes;

        public ContactDtoBuilder(String firstName, String lastName) {  //mandatory 1
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public ContactDtoBuilder(String businessPhone) {  //mandatory 2
            this.businessPhone = businessPhone;
        }

        public ContactDtoBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public ContactDtoBuilder withUri(String uri) {
            this.uri = uri;
            return this;
        }

        public ContactDtoBuilder withAvailability(String availability) {
            this.availability = availability;
            return this;
        }

        public ContactDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ContactDtoBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ContactDtoBuilder withMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public ContactDtoBuilder withNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public ContactDtoBuilder withCompany(String company) {
            this.company = company;
            return this;
        }

        public ContactDtoBuilder withJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public ContactDtoBuilder withHomePhone(String homePhone) {
            this.homePhone = homePhone;
            return this;
        }

        public ContactDtoBuilder withHomePhone2(String homePhone2) {
            this.homePhone2 = homePhone2;
            return this;
        }

        public ContactDtoBuilder withBusinessPhone(String businessPhone) {
            this.businessPhone = businessPhone;
            return this;
        }

        public ContactDtoBuilder withBusinessPhone2(String businessPhone2) {
            this.businessPhone2 = businessPhone2;
            return this;
        }

        public ContactDtoBuilder withMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
            return this;
        }

        public ContactDtoBuilder withBusinessFax(String businessFax) {
            this.businessFax = businessFax;
            return this;
        }

        public ContactDtoBuilder withCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
            return this;
        }

        public ContactDtoBuilder withAssistantPhone(String assistantPhone) {
            this.assistantPhone = assistantPhone;
            return this;
        }

        public ContactDtoBuilder withCarPhone(String carPhone) {
            this.carPhone = carPhone;
            return this;
        }

        public ContactDtoBuilder withOtherPhone(String otherPhone) {
            this.otherPhone = otherPhone;
            return this;
        }

        public ContactDtoBuilder withOtherFax(String otherFax) {
            this.otherFax = otherFax;
            return this;
        }

        public ContactDtoBuilder withCallbackPhone(String callbackPhone) {
            this.callbackPhone = callbackPhone;
            return this;
        }

        public ContactDtoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactDtoBuilder withEmail2(String email2) {
            this.email2 = email2;
            return this;
        }

        public ContactDtoBuilder withEmail3(String email3) {
            this.email3 = email3;
            return this;
        }

        public ContactDtoBuilder withHomeAddress(ContactAddressDto homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        public ContactDtoBuilder withBusinessAddress(ContactAddressDto businessAddress) {
            this.businessAddress = businessAddress;
            return this;
        }

        public ContactDtoBuilder withOtherAddress(ContactAddressDto otherAddress) {
            this.otherAddress = otherAddress;
            return this;
        }

        public ContactDtoBuilder withBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public ContactDtoBuilder withWebPage(String webPage) {
            this.webPage = webPage;
            return this;
        }

        public ContactDtoBuilder withNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public ContactDto build() {
            ContactDto contactDto = new ContactDto();
            contactDto.homePhone = this.homePhone;
            contactDto.callbackPhone = this.callbackPhone;
            contactDto.mobilePhone = this.mobilePhone;
            contactDto.email2 = this.email2;
            contactDto.availability = this.availability;
            contactDto.middleName = this.middleName;
            contactDto.email3 = this.email3;
            contactDto.otherFax = this.otherFax;
            contactDto.companyPhone = this.companyPhone;
            contactDto.firstName = this.firstName;
            contactDto.businessFax = this.businessFax;
            contactDto.nickName = this.nickName;
            contactDto.businessPhone = this.businessPhone;
            contactDto.homeAddress = this.homeAddress;
            contactDto.assistantPhone = this.assistantPhone;
            contactDto.carPhone = this.carPhone;
            contactDto.uri = this.uri;
            contactDto.id = this.id;
            contactDto.email = this.email;
            contactDto.businessAddress = this.businessAddress;
            contactDto.birthday = this.birthday;
            contactDto.jobTitle = this.jobTitle;
            contactDto.webPage = this.webPage;
            contactDto.businessPhone2 = this.businessPhone2;
            contactDto.homePhone2 = this.homePhone2;
            contactDto.company = this.company;
            contactDto.otherPhone = this.otherPhone;
            contactDto.lastName = this.lastName;
            contactDto.notes = this.notes;
            contactDto.otherAddress = this.otherAddress;
            return contactDto;
        }
    }

}

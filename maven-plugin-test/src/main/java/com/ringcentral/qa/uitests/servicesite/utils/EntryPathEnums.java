
package com.ringcentral.qa.uitests.servicesite.utils;
public enum EntryPathEnums {
    Login("/"),
    AutoReceptionist("/company/index.html#receptionist"),
    Billing("/settings/billing.html"),
    CompanyNumbers("/company/index.html#numbers"),
    PhoneNumbers("/company/index.html#/phoneNumbers"),
    CompanyInfo("/company/index.html#/companyInfo/companyAddress"),
    Contacts("/contacts/index.html"),
    Departments("/company/index.html#/departments"),
    SharedLineGroups("/company/index.html#/departments/sharedLines"),
    PagingGroups("/company/index.html#/departments/pagingOnly"),
    GroupsOthers("/company/index.html#/departments/others"),
    InternationalCalling("/settings/billing.html#iCalling"),
    Overview("/company/index.html"),
    MyExtensionOverview("/overview.html"),
    PhonesAndDevices("/company/index.html#phones"),
    Settings("/settings/index.html"),
    SettingsDetails("/settings/index.html#details"),
    SettingsDetailsNewUI("/settings/index.html#/settings/extensionInfo/settingsAndPermissions"),
    SettingsPhonesAndNumbers("/settings/index.html#phone"),
    SettingsPhonesAndNumbersNewUI("/settings/index.html#/settings/phonesAndNumbers/phone"),
    SettingsScreening("/settings/index.html#/settings/screeningGreetingHoldMusic"),
    SettingsCallHandling("/settings/index.html#/settings/callHandling"),
    SettingsVoicemail("/settings/index.html#/settings/messagesAndNotifications"),
    SettingsOutboud("/settings/index.html#/outboundCallsFaxes"),
    SettingsCallerId("/settings/index.html#/outboundCallsFaxes/callerId"),
    SettingsFax("/settings/index.html#/outboundCallsFaxes/faxSettings"),
    PhoneSystem("/company/index.html"),
    PhoneSystemUserPhones("/company/index.html#/phones"),
    ServicePlan("/settings/billing.html#servicePlan"),
    UserManagement("/users/index.html#/users"),
    Roles("/users/index.html#/roles"),
    TemplatesNew("/users/index.html#/templates"),
    CompanyUsers("/company/index.html#users"),
    PaymentMethod("/settings/billing.html#payment"),
    IVR("/tools/ivrTools.html"),
    DirectoryAssistance("/company/index.html#/companyInfo/directoryAssistance"),
    Appearance("/tools/appearance.html"),
    SessionTimeout("/tools/inactiveSessionTimer.html"),
    SSO("/tools/setUpSSO.html"),
    HipaaSetting("/tools/hipaa.html"),
    PhoneAndDevices("/company/index.html#/phones"),
    PhoneAndDevicesSLG("/company/index.html#/phones/slg"),
    PhoneAndDevicesCommon("/company/index.html#/phones/common"),
    PhoneAndDevicesPaging("/company/index.html#/phones/paging"),
    SLGGroups("/company/index.html#/departments/sharedLines"),
    AutoReceptionistNewUi("/company/index.html#/autoReceptionist"),
    AutoReceptionistNewUi_GeneralSettings("/company/index.html#/autoReceptionist/generalSettings"),
    AutoReceptionistNewUi_IvrMenus("/company/index.html#/autoReceptionist/ivrMenus"),
    IVRNewUi("/company/index.html#/autoReceptionist/ivrEditor"),
    AdvancedIVR("/company/index.html#/autoReceptionist/advancedIvr"),
    PhoneNumbersUnassignedNumber("/company/index.html#/phoneNumbers/unassignedNumber"),
    MeetingsLicenses("/settings/billing.html#/meetings"),
    LargeMeetingLicenses("/settings/billing.html#/meetings/largeMeeting"),
    WebinarLicenses("/settings/billing.html#/meetings/ringCentralWebinar"),
    RCRoomsLicenses("/settings/billing.html#/meetings/ringCentralRooms"),
    RoomConnectorLicense("/settings/billing.html#/meetings/roomConnector"),
    GlipLicense("/settings/billing.html#/glip/glipUsers");
    String pathValue;
    EntryPathEnums(String pathValue) {
        this.pathValue = pathValue;
    }
    public String getPathValue(){
        return pathValue;
    }
}
    
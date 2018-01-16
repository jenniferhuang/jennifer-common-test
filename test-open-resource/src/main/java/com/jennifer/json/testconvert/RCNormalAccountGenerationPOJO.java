package com.jennifer.json.testconvert;

import java.util.List;

public class RCNormalAccountGenerationPOJO {


    /**
     * companyName : RingCentral Inc.
     * companyEmailDomain : testjennifer@ringcentral4444.com
     * mainCompanyNumber : 18772181121
     * users : [{"id":"user701","email":"user701@tatestrcbetaaccount.com","name":"John Doe701","number":0,"extension":701,"password":"Test!123","did":[18632933334]}]
     * guests : [{"id":"guest2DID","email":"guest@tatestrcguestwithdid.com","name":"guest2DID","number":18772181122,"extension":701,"password":"Test!123","did":[18632933334,18632933335]}]
     * teams : [{"id":"team1_u1_u2","email":"team1_u1_u2@acmemthorta1","name":"team1_u1_u2","password":""},{"id":"team2_u2_u3","email":"team2_u2_u3@acmemthorta1","name":"team2_u2_u3","password":""}]
     */

    private String companyName;
    private String companyEmailDomain;
    private long mainCompanyNumber;
    private List<UsersBean> users;
    private List<UsersBean> guests;
    private List<TeamsBean> teams;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmailDomain() {
        return companyEmailDomain;
    }

    public void setCompanyEmailDomain(String companyEmailDomain) {
        this.companyEmailDomain = companyEmailDomain;
    }

    public long getMainCompanyNumber() {
        return mainCompanyNumber;
    }

    public void setMainCompanyNumber(long mainCompanyNumber) {
        this.mainCompanyNumber = mainCompanyNumber;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public List<UsersBean> getGuests() {
        return guests;
    }

    public void setGuests(List<UsersBean> guests) {
        this.guests = guests;
    }

    public List<TeamsBean> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamsBean> teams) {
        this.teams = teams;
    }


    public static class UsersBean {
        /**
         * id : guest2DID
         * email : guest@tatestrcguestwithdid.com
         * name : guest2DID
         * number : 18772181122
         * extension : 701
         * password : Test!123
         * did : [18632933334,18632933335]
         */

        private String id;
        private String email;
        private String name;
        private long number;
        private int extension;
        private String password;
        private List<Long> did;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getNumber() {
            return number;
        }

        public void setNumber(long number) {
            this.number = number;
        }

        public int getExtension() {
            return extension;
        }

        public void setExtension(int extension) {
            this.extension = extension;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<Long> getDid() {
            return did;
        }

        public void setDid(List<Long> did) {
            this.did = did;
        }
    }

    public static class TeamsBean {
        /**
         * id : team1_u1_u2
         * email : team1_u1_u2@acmemthorta1
         * name : team1_u1_u2
         * password :
         */

        private String id;
        private String email;
        private String name;
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

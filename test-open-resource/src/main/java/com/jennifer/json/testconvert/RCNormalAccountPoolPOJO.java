package com.jennifer.json.testconvert;

import java.util.List;

public class RCNormalAccountPoolPOJO {


    /**
     * companyName : acmemthorta1
     * companyEmailDomain : @acmemthorta1.com
     * users : {"user1":{"email":"user1@acmemthorta1.com","name":"user1","password":"Test!123","favorites":["user2"]},"user2":{"email":"user2@acmemthorta1.com","name":"user2","password":"Test!123","favorites":["user1"]},"user3":{"email":"user3@acmemthorta1.com","name":"user3","password":"Test!123","favorites":["user4"]},"user4":{"email":"user4@acmemthorta1.com","name":"user4","password":"Test!123","favorites":["user3"]},"guest1":{"email":"guest1@acmeguest1.com","name":"guest1","password":"Test!123"},"rc_guest1":{"email":"something@xmnlab2.ru","name":"something2 xmnlab","password":"Test!123"},"admin":{"email":"admin@acmemthorta1.com","name":"admin","password":"Test!123"}}
     * teams : {"team1_u1_u2":{"email":"team1_u1_u2@acmemthorta1","name":"team1_u1_u2","password":""},"team2_u2_u3":{"email":"team2_u2_u3@acmemthorta1","name":"team2_u2_u3","password":""}}
     */

    private String companyName;
    private String companyEmailDomain;
    private UsersBean users;
    private TeamsBean teams;

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

    public UsersBean getUsers() {
        return users;
    }

    public void setUsers(UsersBean users) {
        this.users = users;
    }

    public TeamsBean getTeams() {
        return teams;
    }

    public void setTeams(TeamsBean teams) {
        this.teams = teams;
    }

    public static class UsersBean {
        /**
         * user1 : {"email":"user1@acmemthorta1.com","name":"user1","password":"Test!123","favorites":["user2"]}
         * user2 : {"email":"user2@acmemthorta1.com","name":"user2","password":"Test!123","favorites":["user1"]}
         * user3 : {"email":"user3@acmemthorta1.com","name":"user3","password":"Test!123","favorites":["user4"]}
         * user4 : {"email":"user4@acmemthorta1.com","name":"user4","password":"Test!123","favorites":["user3"]}
         * guest1 : {"email":"guest1@acmeguest1.com","name":"guest1","password":"Test!123"}
         * rc_guest1 : {"email":"something@xmnlab2.ru","name":"something2 xmnlab","password":"Test!123"}
         * admin : {"email":"admin@acmemthorta1.com","name":"admin","password":"Test!123"}
         */

        private User1Bean user1;
        private User2Bean user2;
        private User3Bean user3;
        private User4Bean user4;
        private Guest1Bean guest1;
        private RcGuest1Bean rc_guest1;
        private AdminBean admin;

        public User1Bean getUser1() {
            return user1;
        }

        public void setUser1(User1Bean user1) {
            this.user1 = user1;
        }

        public User2Bean getUser2() {
            return user2;
        }

        public void setUser2(User2Bean user2) {
            this.user2 = user2;
        }

        public User3Bean getUser3() {
            return user3;
        }

        public void setUser3(User3Bean user3) {
            this.user3 = user3;
        }

        public User4Bean getUser4() {
            return user4;
        }

        public void setUser4(User4Bean user4) {
            this.user4 = user4;
        }

        public Guest1Bean getGuest1() {
            return guest1;
        }

        public void setGuest1(Guest1Bean guest1) {
            this.guest1 = guest1;
        }

        public RcGuest1Bean getRc_guest1() {
            return rc_guest1;
        }

        public void setRc_guest1(RcGuest1Bean rc_guest1) {
            this.rc_guest1 = rc_guest1;
        }

        public AdminBean getAdmin() {
            return admin;
        }

        public void setAdmin(AdminBean admin) {
            this.admin = admin;
        }

        public static class User1Bean {
            /**
             * email : user1@acmemthorta1.com
             * name : user1
             * password : Test!123
             * favorites : ["user2"]
             */

            private String email;
            private String name;
            private String password;
            private List<String> favorites;

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

            public List<String> getFavorites() {
                return favorites;
            }

            public void setFavorites(List<String> favorites) {
                this.favorites = favorites;
            }
        }

        public static class User2Bean {
            /**
             * email : user2@acmemthorta1.com
             * name : user2
             * password : Test!123
             * favorites : ["user1"]
             */

            private String email;
            private String name;
            private String password;
            private List<String> favorites;

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

            public List<String> getFavorites() {
                return favorites;
            }

            public void setFavorites(List<String> favorites) {
                this.favorites = favorites;
            }
        }

        public static class User3Bean {
            /**
             * email : user3@acmemthorta1.com
             * name : user3
             * password : Test!123
             * favorites : ["user4"]
             */

            private String email;
            private String name;
            private String password;
            private List<String> favorites;

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

            public List<String> getFavorites() {
                return favorites;
            }

            public void setFavorites(List<String> favorites) {
                this.favorites = favorites;
            }
        }

        public static class User4Bean {
            /**
             * email : user4@acmemthorta1.com
             * name : user4
             * password : Test!123
             * favorites : ["user3"]
             */

            private String email;
            private String name;
            private String password;
            private List<String> favorites;

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

            public List<String> getFavorites() {
                return favorites;
            }

            public void setFavorites(List<String> favorites) {
                this.favorites = favorites;
            }
        }

        public static class Guest1Bean {
            /**
             * email : guest1@acmeguest1.com
             * name : guest1
             * password : Test!123
             */

            private String email;
            private String name;
            private String password;

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

        public static class RcGuest1Bean {
            /**
             * email : something@xmnlab2.ru
             * name : something2 xmnlab
             * password : Test!123
             */

            private String email;
            private String name;
            private String password;

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

        public static class AdminBean {
            /**
             * email : admin@acmemthorta1.com
             * name : admin
             * password : Test!123
             */

            private String email;
            private String name;
            private String password;

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

    public static class TeamsBean {
        /**
         * team1_u1_u2 : {"email":"team1_u1_u2@acmemthorta1","name":"team1_u1_u2","password":""}
         * team2_u2_u3 : {"email":"team2_u2_u3@acmemthorta1","name":"team2_u2_u3","password":""}
         */

        private Team1U1U2Bean team1_u1_u2;
        private Team2U2U3Bean team2_u2_u3;

        public Team1U1U2Bean getTeam1_u1_u2() {
            return team1_u1_u2;
        }

        public void setTeam1_u1_u2(Team1U1U2Bean team1_u1_u2) {
            this.team1_u1_u2 = team1_u1_u2;
        }

        public Team2U2U3Bean getTeam2_u2_u3() {
            return team2_u2_u3;
        }

        public void setTeam2_u2_u3(Team2U2U3Bean team2_u2_u3) {
            this.team2_u2_u3 = team2_u2_u3;
        }

        public static class Team1U1U2Bean {
            /**
             * email : team1_u1_u2@acmemthorta1
             * name : team1_u1_u2
             * password :
             */

            private String email;
            private String name;
            private String password;

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

        public static class Team2U2U3Bean {
            /**
             * email : team2_u2_u3@acmemthorta1
             * name : team2_u2_u3
             * password :
             */

            private String email;
            private String name;
            private String password;

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
}

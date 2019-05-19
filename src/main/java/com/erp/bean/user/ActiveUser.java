package com.erp.bean.user;

    /**
     * @Author: Qiu
     * @Date: 2019/5/19 15:41
     */
    public class ActiveUser {

        String username;
        String rolename;

        public ActiveUser() {
        }

        public ActiveUser(String username, String rolename) {
            this.username = username;
            this.rolename = rolename;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRolename() {
            return rolename;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }

        @Override
        public String toString() {
            return "ActiveUser{" +
                    "username='" + username + '\'' +
                    ", rolename='" + rolename + '\'' +
                    '}';
        }
    }

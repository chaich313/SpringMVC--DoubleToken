package com.itranlin.basic.common.bean;

/**
 * 创建时间: 2020/3/27 21:10
 *
 * @author itranlin
 */
public class Constants {
    public static class Authority {
        public static final String ADMIN = "1";
        public static final String NORMAL_ADMIN = "2";
        public static final String NORMAL = "3";
    }

    public static class UpdateType {
        public static final String INSERT = "insert";
        public static final String UPDATE = "update";
        public static final String DELETE = "delete";
    }

    public static class TaskType {
        public static final String ING = "1";
        public static final String WAIT = "2";
        public static final String HISTORY = "3";
    }


}

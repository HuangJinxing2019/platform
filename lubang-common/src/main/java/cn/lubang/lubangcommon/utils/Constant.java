package cn.lubang.lubangcommon.utils;

/**
 * 常量
 *
 * @author feking.fang
 * @email feking.fang@smallshark.cn
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    /**
     * ORACLE、MYSQL
     */
    public static final String USE_DATA = "MYSQL";

    public static final String SMS_CONFIG_KEY = "1";
    /**
     * 分页条数
     */
    public static final int pageSize=10;
    /**
     * 权限前缀
     */
    public static final String PERMS_LIST = "permsList";

    /**
     * 菜单类型
     *
     * @author feking.fang
     * @email feking.fang@smallshark.cn
     * @date 2016年11月15日 下午1:24:29
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
     * @author feking.fang
     * @email feking.fang@smallshark.cn
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 是否类型
     */
    public enum YESNO{
        /**
         * 是
         */
        YES("0"),
        /**
         * 否
         */
        NO("1");
        private String value;

        private YESNO(String value){
            this.value=value;
        }
        public String getValue(){
            return value;
        }
    }
}

1.框架配置文件: springmvc-servlet.xml

2.mysql 数据库配置文件是在 springmvc-servlet <jdbcDataSource>中

3.学生系统案例用到的表结构:
-- ----------------------------
-- Table structure for student
-- ----------------------------
CREATE TABLE `demo_ssm_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `age` varchar(2) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

4.学生系统访问地址  http://localhost:8080/kuangke/student/query

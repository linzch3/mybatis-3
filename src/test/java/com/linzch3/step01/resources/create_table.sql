DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role` VALUES ('1', '张三', '张三的备注');

SELECT * FROM role;

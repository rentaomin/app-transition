-- 日期类型数据测试
CREATE TABLE testdb:tb_account(
    id      INTEGER PRIMARY key ,
    user_name VARCHAR(100),
    age      INTEGER,
    birthday  datetime year to second
);

INSERT INTO testdb:tb_account(id, user_name, age, birthdate) VALUES (1, '张三', 18, '2020-01-11 14:01:32'),(2, '李四', 19, '2021-03-21  12:02:01');

-- 创建表
create table tab_dt
(
    col1 date,
    col2 datetime year to day,
    col3 datetime year to second,
    col4 datetime year to fraction(3),
    col5 datetime year to fraction(5),
    col6 datetime hour to second
);
-- 插入数据
insert into tab_dt values(
         current year to fraction(5),
         current year to fraction(5),
         current year to fraction(5),
         current year to fraction(5),
         current year to fraction(5),
         current year to fraction(5));

Use test;
DROP TABLE IF EXISTS book;
create table book
(
  id int auto_increment
  primary key,
  title varchar(100) not null,
  description varchar(255) null,
  author varchar(100) not null,
  isbn varchar(20) not null,
  printYear int not null,
  readAlready tinyint(1) default '0' not null,
  constraint book_id_uindex
  unique (id)
)
  engine=InnoDB
;

create index book_title_index
  on book (title)
;

create index book_printYear_index
  on book (printYear)
;

create index book_readAlready_index
  on book (readAlready)
;
INSERT into book(id, title, description, author, isbn, printYear) VALUES (1, 'Чужак', 'Если бы все человеческие желания исполнялись, земля стала бы адом', 'Макс Фрай', '978-5-17-087266-4', 1996);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (2,'Волонтеры вечности', 'Настоящее - это суммарно взятое прошлое.', 'Макс Фрай', '978-5-17-087846-8', 1997);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (3,'Простые волшебные вещи', 'Женщины созданы исключительно для того, чтобы нравиться мужчине; причина, почему мужчина нравится женщине, заключается в его силе, умственном и нравственном превосходстве: он нравится, потому что он силен.', 'Макс Фрай', '978-5-17-088268-7', 1997);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (4,'Темная сторона', 'У каждого человека три характера: тот, который ему приписывают; тот, который он сам себе приписывает; и, наконец, тот, который есть в действительности.', 'Макс Фрай', '978-5-17-088270-0 ', 1997);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (5,'Наваждения', 'Кто весь день работает, тому некогда зарабатывать деньги', 'Макс Фрай', '978-5-17-088726-2', 1997);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (6,'Власть несбывшегося', 'Брак - это мирное сосуществование двух нервных систем', 'Макс Фрай', '978-5-17-088765-1', 1998);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (7,'Болтливый мертвец', 'Военное правосудие имеет такое же отношение к правосудию, как военная музыка к музыке.', 'Макс Фрай', '978-5-17-089829-9', 1999);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (8,'Лабиринт Мёнина', 'Самое трудное – взять меньше, когда можешь получить больше', 'Макс Фрай', '978-5-17-090207-1', 2000);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (9,'Мастер ветров и закатов', 'Все хотят дожить до старости, а когда доживут, ее же винят', 'Макс Фрай', '978-5-17-087268-8', 2014);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (10,'Слишком много кошмаров', 'Добродетель несовместима с невежеством, суеверием, рабством; рабов можно удержать лишь страхом наказания.', 'Макс Фрай', '978-5-17-088720-0 ', 2015);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (11,'Цвет волшебства', 'Мало найдется людей, которые, затрудняясь в материале для беседы, не выдали бы секретных дел своих друзей', 'Терри Пратчетт', '978-5-699-15629-0', 1983);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (12,'Вещие сестрички', 'Исправить злого человека невозможно, он может изменить только вид, но не нрав.', 'Терри Пратчетт', '978-5-699-16814-9', 1988);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (13,'Дамы и господа', 'евота – это молчаливый крик', 'Терри Пратчетт', '978-5-699-17159-0', 1992);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (14,'Ведьмы за границей', 'В будние дни мы не очень удачно используем свою нравственность. К воскресенью она всегда требует ремонта.', 'Терри Пратчетт', '978-5-699-18075-2', 1991);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (15,'Патриот', 'Чем сильнее скука обыденной жизни, тем вернее действуют яды, называемые благодарностью, восхищением и любопытством.', 'Терри Пратчетт', '978-5-699-19912-9', 1998);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (16,'Космобиолухи', 'Самые храбрые войска набираются из людей отчаявшихся, готовых на все, потому что им нечего терять.', 'Ольга Громыко', '978-5-9922-0830-6 ', 2011);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (17,'Космоэколухи. Том 1', 'Кто не любит вина, женщин и песен, так дураком и умрет!', 'Ольга Громыко', '978-5-9922-1244-0', 2012);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (18,'Космоэколухи. Том 2', 'Видишь, как несчастен человек, если и тот, кому завидуют, завидует тоже.', 'Ольга Громыко', '978-5-9922-1244-0', 2012);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (19,'Космопсихолухи. Том 1', 'Потребность быть правым есть признак вульгарного ума', 'Ольга Громыко', '978-5-9922-1732-2', 2014);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (20,'Космопсихолухи. Том 2', 'Благодаря материнскому инстинкту женщина предпочитает владеть одной акцией из ста на первоклассного мужчину, а не всем пакетом акций на второстепенного', 'Ольга Громыко', '978-5-9922-1737-7', 2014);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (21,'Космотехнолухи. Том 1', 'Мы часто видим, что мужчина - кое-какой, а женщина -превосходная. Это значит, мы не знаем скрытого достоинства этого мужчины, оцененного женщиной.', 'Ольга Громыко', '978-5-9922-2185-5', 2016);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (22,'Космотехнолухи. Том 2', 'Выбирай, кого будешь любить', 'Ольга Громыко', '978-5-9922-2185-5', 2016);
INSERT into book(id, title, description, author, isbn, printYear) VALUES (23,'Космотехнолухи. Том 4', 'Выбирай, кого будешь любить снова и снова', 'Ольга Громыко', '978-5-9922-2985-5', 2016);






-- restaurantsテーブル
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (1, 'わびすけ', 9, 'restaurant9.jpg', 'おばんざいを多数揃えており、一人飲みの方も多数いらっしゃいます。仕事終わりにぜひ、一杯。', '2000円〜3000円', 8, '18:00', '23:00', '073-0145', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (2, 'イタリアン', 2, 'restaurant4.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '2000円〜3000円', 8, '11:00', '21:00', '204-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (3, 'nagoya BURGAR', 8, 'restaurant3.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '1000円〜2000円', 4, '11:00', '21:00', '504-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (4, 'やきにく名古屋', 7, 'restaurant1.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '3000円〜4000円', 10, '11:00', '21:00', '604-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (5, 'やきとり栄', 9, 'restaurant2.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '2000円〜3000円', 4, '11:00', '21:00', '904-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (6, 'さみだれ', 10, 'restaurant5.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '1000円〜2000円', 2, '11:00', '21:00', '204-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (7, '名古屋ラーメン', 10, 'restaurant6.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '1000円〜2000円', 5, '11:00', '21:00', '204-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (8, 'インディア', 11, 'restaurant10.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '1000円〜3000円', 6, '11:00', '21:00', '204-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (9, 'アジアン', 3, 'restaurant8.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '4000円〜8000円', 10, '11:00', '21:00', '204-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (10, 'ソウルフード', 4, 'restaurant11.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '3000円〜4000円', 5, '11:00', '21:00', '204-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE INTO restaurants (id, name, category_id, image_name, description, price, capacity, opening_time, closing_time, postal_code, address, phone_number) VALUES (11, 'さと', 6, 'restaurant7.jpg', '名古屋老舗のお店。老舗の味をご堪能ください。', '4000円〜8000円', 7, '11:00', '21:00', '204-4168', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');


-- rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT IGNORE INTO roles (id, name) VALUES (3, 'ROLE_PAYMENT');

-- usersテーブル
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (1, '侍 太郎', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', '', 1, true);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (2, '侍 花子', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', '', 2, true);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (3, '侍 義勝', 'yoshikatsu.samurai@example.com', 'password', '', 1, false);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (4, '侍 幸美', 'sachimi.samurai@example.com', 'password', '', 1, false);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (5, '侍 雅', 'miyabi.samurai@example.com', 'password', '', 1, false);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (6, '侍 正保', 'masayasu.samurai@example.com', 'password', '', 1, false);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (7, '侍 真由美', 'mayumi.samurai@example.com', 'password', '', 1, false);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (8, '侍 安民', 'yasutami.samurai@example.com', 'password', '', 1, false);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (9, '侍 章緒', 'akio.samurai@example.com', 'password', '', 1, false);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (10, '侍 祐子', 'yuko.samurai@example.com', 'password', '', 1, false);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (11, '侍 秋美', 'akimi.samurai@example.com', 'password', '', 1, false);
INSERT IGNORE INTO users (id, name, email, password, subscription_id, role_id, enabled) VALUES (12, '侍 信平', 'shinpei.samurai@example.com', 'password', '', 1, false);

--categoriesテーブル
INSERT IGNORE INTO categories (id, name) VALUES (1, '中華');
INSERT IGNORE INTO categories (id, name) VALUES (2, 'イタリアン');
INSERT IGNORE INTO categories (id, name) VALUES (3, 'アジア料理');
INSERT IGNORE INTO categories (id, name) VALUES (4, '韓国料理');
INSERT IGNORE INTO categories (id, name) VALUES (5, '洋食');
INSERT IGNORE INTO categories (id, name) VALUES (6, '和食');
INSERT IGNORE INTO categories (id, name) VALUES (7, '焼き肉');
INSERT IGNORE INTO categories (id, name) VALUES (8, 'ファーストフード');
INSERT IGNORE INTO categories (id, name) VALUES (9, '居酒屋');
INSERT IGNORE INTO categories (id, name) VALUES (10, 'ラーメン');
INSERT IGNORE INTO categories (id, name) VALUES (11, 'カレー');

-- reservationsテーブル
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (1, 1, 1, '2024-12-01', '18:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (2, 2, 1, '2024-12-02', '18:00', 3);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (3, 3, 1, '2024-12-03', '18:00', 4);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (4, 4, 1, '2024-12-04', '18:00', 5);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (5, 5, 1, '2024-12-05', '18:00', 6);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (6, 6, 1, '2024-12-06', '18:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (7, 7, 1, '2024-12-07', '18:00', 3);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (8, 8, 1, '2024-12-08', '18:00', 4);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (9, 9, 1, '2024-12-09', '18:00', 5);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (10, 10, 1, '2024-12-10', '18:00', 6);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_date, reservation_time, number_of_people) VALUES (11, 11, 1, '2024-12-11', '18:00', 2);

--reviewテーブル
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (1, 1, 1, '5', 'とても美味しかったです。また来たいです。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (2, 1, 3, '5', '素敵な接客でした。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (3, 1, 4, '5', 'とても美味しかったです。また来たいです。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (4, 1, 2, '5', 'とても美味しかったです。また来たいです。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (5, 1, 3, '5', '家族を連れてまた来たいです。。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (6, 1, 2, '4', 'とてもきれいで快適に過ごせました。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (7, 1, 8, '5', 'また来たいです。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (8, 1, 4, '3', 'とてもきれいで快適に過ごせました。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (9, 1, 6, '5', '素敵な接客でした。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (10, 1, 3, '4', 'とても美味しく快適に過ごせました。', '2024-04-01', '2024-04-01');
INSERT IGNORE INTO review (id, restaurant_id, user_id, review_star, review_comment, created_at, updated_at) VALUES (11, 1, 3, '2', '接客が良かったです。', '2024-04-01', '2024-04-01');
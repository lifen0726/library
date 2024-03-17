INSERT INTO Book (ISBN, Name, Author, Introduction)
VALUES 
    ('9786263337954','Vue.js 實戰', '作者1', 'Vue.js 實戰內容簡介'),
    ('9785106537954','JavaScript 高級編程', '作者2', 'JavaScript 高級編程內容簡介'),
    ('9785106562014','設計模式', '作者3', '設計模式內容簡介'),
    ('5842006537954','哈利·波特與魔法石', 'J.K. 羅琳', '哈利·波特與魔法石內容簡介'),
    ('9782584106954','飄', '瑪格麗特·米切爾', '飄內容簡介');

-- 插入 Book 資料到 Inventory 表中
INSERT INTO Inventory (ISBN, store_time, status)
SELECT ISBN, GETDATE(), '在庫'
FROM Book;


-- 創建 User 使用者表
CREATE TABLE User (
    UserId INT PRIMARY KEY, -- 假設使用 INT 作為使用者ID的資料類型
    PhoneNumber VARCHAR(20) UNIQUE NOT NULL, -- 假設手機號碼長度不超過20個字元
    Password VARCHAR(255) NOT NULL, -- 密碼經過加鹽和雜湊後可能會很長
    UserName NVARCHAR(100), -- 假設使用者名稱的長度不超過100個字元
    RegistrationTime DATETIME NOT NULL, -- 註冊日期時間
    LastLoginTime DATETIME -- 最後登入時間
);

-- 創建 Inventory 庫存表
CREATE TABLE Inventory (
    InventoryId INT PRIMARY KEY, -- 假設使用 INT 作為庫存ID的資料類型
    ISBN VARCHAR(20), -- 假設ISBN長度不超過20個字元
    StoreTime DATETIME NOT NULL, -- 書籍入庫日期時間
    Status NVARCHAR(50) NOT NULL -- 書籍狀態
);

-- 創建 Book 書籍表
CREATE TABLE Book (
    ISBN VARCHAR(20) PRIMARY KEY, -- 假設ISBN長度不超過20個字元
    Name NVARCHAR(255) NOT NULL, -- 假設書名長度不超過255個字元
    Author NVARCHAR(100) NOT NULL, -- 假設作者名稱長度不超過100個字元
    Introduction NVARCHAR(MAX) -- 書籍內容簡介，使用MAX表示不限長度
);

-- 創建 Borrowing Record 借閱紀錄表
CREATE TABLE BorrowingRecord (
    UserId INT, -- 使用者 ID
    InventoryId INT, -- 庫存 ID
    BorrowingTime DATETIME NOT NULL, -- 借出日期時間
    ReturnTime DATETIME, -- 歸還日期時間
);
-- 為 Borrowing_Records 表的 user_id 欄位添加索引
CREATE INDEX idx_borrowing_records_user_id ON Borrowing_Records(user_id);

-- 為 Borrowing_Records 表的 inventory_id 欄位添加索引
CREATE INDEX idx_borrowing_records_inventory_id ON Borrowing_Records(inventory_id);


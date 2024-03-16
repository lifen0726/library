-- �Ы� User �ϥΪ̪�
CREATE TABLE User (
    UserId INT PRIMARY KEY, -- ���]�ϥ� INT �@���ϥΪ�ID���������
    PhoneNumber VARCHAR(20) UNIQUE NOT NULL, -- ���]������X���פ��W�L20�Ӧr��
    Password VARCHAR(255) NOT NULL, -- �K�X�g�L�[�Q�M�����i��|�ܪ�
    UserName NVARCHAR(100), -- ���]�ϥΪ̦W�٪����פ��W�L100�Ӧr��
    RegistrationTime DATETIME NOT NULL, -- ���U����ɶ�
    LastLoginTime DATETIME -- �̫�n�J�ɶ�
);

-- �Ы� Inventory �w�s��
CREATE TABLE Inventory (
    InventoryId INT PRIMARY KEY, -- ���]�ϥ� INT �@���w�sID���������
    ISBN VARCHAR(20), -- ���]ISBN���פ��W�L20�Ӧr��
    StoreTime DATETIME NOT NULL, -- ���y�J�w����ɶ�
    Status NVARCHAR(50) NOT NULL -- ���y���A
);

-- �Ы� Book ���y��
CREATE TABLE Book (
    ISBN VARCHAR(20) PRIMARY KEY, -- ���]ISBN���פ��W�L20�Ӧr��
    Name NVARCHAR(255) NOT NULL, -- ���]�ѦW���פ��W�L255�Ӧr��
    Author NVARCHAR(100) NOT NULL, -- ���]�@�̦W�٪��פ��W�L100�Ӧr��
    Introduction NVARCHAR(MAX) -- ���y���e²���A�ϥ�MAX��ܤ�������
);

-- �Ы� Borrowing Record �ɾ\������
CREATE TABLE BorrowingRecord (
    UserId INT, -- �ϥΪ� ID
    InventoryId INT, -- �w�s ID
    BorrowingTime DATETIME NOT NULL, -- �ɥX����ɶ�
    ReturnTime DATETIME, -- �k�٤���ɶ�
);
-- �� Borrowing_Records �� user_id ���K�[����
CREATE INDEX idx_borrowing_records_user_id ON Borrowing_Records(user_id);

-- �� Borrowing_Records �� inventory_id ���K�[����
CREATE INDEX idx_borrowing_records_inventory_id ON Borrowing_Records(inventory_id);


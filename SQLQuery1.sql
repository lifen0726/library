-- �Ы� User �ϥΪ̪�
CREATE TABLE Users (
    user_id INT PRIMARY KEY IDENTITY(1,1), -- ���]�ϥ� INT �@���ϥΪ�ID���������
    phone_number VARCHAR(20) UNIQUE NOT NULL, -- ���]������X���פ��W�L20�Ӧr��
    password VARCHAR(255) NOT NULL, -- �K�X�g�L�[�Q�M�����i��|�ܪ�
    user_name NVARCHAR(100) NOT NULL, -- ���]�ϥΪ̦W�٪����פ��W�L100�Ӧr��
    registration_time DATETIME NOT NULL, -- ���U����ɶ�
    last_login_time DATETIME  -- �̫�n�J�ɶ�
);

-- �Ы� Inventory �w�s��
CREATE TABLE Inventory (
    inventory_id INT PRIMARY KEY IDENTITY(10000,1), -- ���]�ϥ� INT �@���w�sID���������
    ISBN VARCHAR(20),  -- ���]ISBN���פ��W�L20�Ӧr��
    store_time DATETIME NOT NULL,  -- ���y�J�w����ɶ�
    status NVARCHAR(50) NOT NULL  -- ���y���A-->�b�w�B�X�ɤ��B��z��(�k�٫᥼�J�w)�B�򥢡B�l���B�o��
);

-- �Ы� Book ���y��
CREATE TABLE Book (
    ISBN VARCHAR(20) PRIMARY KEY, -- ���]ISBN���פ��W�L20�Ӧr��
    Name NVARCHAR(255) NOT NULL, -- ���]�ѦW���פ��W�L255�Ӧr��
    Author NVARCHAR(100) NOT NULL, -- ���]�@�̦W�٪��פ��W�L100�Ӧr��
    Introduction NVARCHAR(MAX) -- ���y���e²���A�ϥ�MAX��ܤ�������
);

-- �Ы� Borrowing Record �ɾ\������
CREATE TABLE Borrowing_Records (
    Borrowing_Records_id INT PRIMARY KEY IDENTITY(100,1),
    user_id INT, -- �ϥΪ� ID
    inventory_id INT, -- �w�s ID
    borrowing_time DATETIME NOT NULL, -- �ɥX����ɶ�
    return_time DATETIME, -- �k�٤���ɶ�
	FOREIGN KEY (user_id) REFERENCES Users(user_id),-- �~��A�PUser�����p
    FOREIGN KEY (inventory_id) REFERENCES Inventory(inventory_id), -- �]�m�~��A�PInventory�����p
    
);



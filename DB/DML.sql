INSERT INTO Book (ISBN, Name, Author, Introduction)
VALUES 
    ('9786263337954','Vue.js ���', '�@��1', 'Vue.js ��Ԥ��e²��'),
    ('9785106537954','JavaScript ���Žs�{', '�@��2', 'JavaScript ���Žs�{���e²��'),
    ('9785106562014','�]�p�Ҧ�', '�@��3', '�]�p�Ҧ����e²��'),
    ('5842006537954','���Q�P�i�S�P�]�k��', 'J.K. ù�Y', '���Q�P�i�S�P�]�k�ۤ��e²��'),
    ('9782584106954','��', '�����R�S�P�̤���', '�Ƥ��e²��');

-- ���J Book ��ƨ� Inventory ��
INSERT INTO Inventory (ISBN, store_time, status)
SELECT ISBN, GETDATE(), '�b�w'
FROM Book;


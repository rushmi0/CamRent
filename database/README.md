# Database Design

#### _ฐานข้อมูลสำหรับระบบการเช่ายืมออนไลน์_

เราเลือกใช้ SQLite เป็นระบบจัดเก็บข้อมูลในโครงการของเรา เนื่องจากมีคุณค่าและเหมาะสมในหลายด้าน ด้วยคุณสมบัติที่เด่นเช่นความเร็วและความสะดวกในการใช้งาน ซึ่งเหมาะสำหรับการสร้างโปรแกรมเพื่อการทดสอบหรือการแสดงตัวอย่าง

SQLite มาพร้อมกับระบบจัดเก็บข้อมูลแบบ SQL (Structured Query Language) และการเก็บข้อมูลในไฟล์เดียว เพื่อความความสะดวก ไม่ต้องมีการติดตั้งเซิร์ฟเวอร์ฐานข้อมูลซับซ้อน สามารถเริ่มต้นใช้งานได้ทันที

ทั้งในโหมดออนไลน์และออฟไลน์ SQLite มีประสิทธิภาพและความต่อเนื่อง ช่วยให้เราสามารถเข้าถึงข้อมูลในทุกสถานการณ์ และช่วยเพิ่มความยืดหยุ่นในการจัดการโครงการ

สรุปได้ว่า SQLite เป็นตัวเลือกที่เหมาะสมสำหรับโครงการของเรา ด้วยคุณสมบัติที่ดีและประสิทธิภาพที่สูง ทั้งในการทดสอบและการนำเสนอตัวอย่าง


## People Table
| คอลัมน์    | ประเภท    | PK หรือ FK | อ้างอิง FK  | คำอธิบาย                          |
| :---        | :---       |:----------:| :---:         | ---                                  |
| PersonID    | TEXT       |   **PK**   |               | รหัสบุคคล            |
| FirstName   | TEXT       |            |               | ชื่อ                              |
| LastName    | TEXT       |            |               | นามสกุล                           |
| Email       | TEXT       |            |               | อีเมล                               |
| PhoneNumber | TEXT       |            |               | เบอร์โทรศัพท์                     |
| AddressID   | TEXT       |   **FK**   | Address | รหัสที่อยู่    |

## Customers Table
| คอลัมน์    | ประเภท    | PK หรือ FK  | อ้างอิง FK  | คำอธิบาย                           |
| :---        | :---       | :---:       | :---:         | ---                                  |
| CustomerID | TEXT       | **PK** |               | รหัสลูกค้า           |
| UserName   | TEXT       |             |               | ชื่อผู้ใช้                          |
| AuthKey    | TEXT       |             |               | รหัสยืนยันตัวตน                   |
| PersonID   | TEXT       |    **FK**    | People | รหัสบุคคล         |

## Stores Table
| คอลัมน์      | ประเภท    | PK หรือ FK  | อ้างอิง FK  | คำอธิบาย                           |
| :---          | :---       | :---:       | :---:         | ---                                  |
| StoreID       | TEXT       | **PK** |               | รหัสร้านค้า           |
| StoreName     | TEXT       |             |               | ชื่อร้านค้า                         |
| PaymentMethod | TEXT       |             |               | วิธีการชำระเงิน                     |
| AuthKey       | TEXT       |             |               | รหัสยืนยันตัวตนของร้านค้า        |
| PersonID      | TEXT       |    **FK**   |  People | รหัสบุคคล         |

## Address Table
| คอลัมน์      | ประเภท    | PK หรือ FK  | อ้างอิง FK  | คำอธิบาย                           |
| :---          | :---       | :---:       | :---:         | ---                                  |
| AddressID    | TEXT       | **PK** |               | รหัสที่อยู่           |
| LocationName | TEXT       |             |               | ชื่อสถานที่                         |
| City         | TEXT       |             |               | เมือง                                |
| Province     | TEXT       |             |               | จังหวัด                              |
| PostalNumber | TEXT       |             |               | รหัสไปรษณีย์                         |
| StreetName   | TEXT       |             |               | ชื่อถนน                              |

## CreditScore Table
| คอลัมน์    | ประเภท    | PK หรือ FK  | อ้างอิง FK  | คำอธิบาย                           |
| :---        | :---       | :---:       | :---:         | ---                                  |
| ScoreID     | TEXT       | **PK** |               | รหัสคะแนนเครดิต     |
| Score       | INTEGER    |             |               | คะแนนเครดิต                           |
| Report      | TEXT       |             |               | รายงานเครดิต                         |
| StoreID     | TEXT       |   **FK**  |  Stores | รหัสร้านค้า      |
| CustomerID  | TEXT       |   **FK**  |  Customers| รหัสลูกค้า  |

## OrderContract Table
| คอลัมน์    | ประเภท    | PK หรือ FK  | อ้างอิง FK  | คำอธิบาย                           |
| :---        | :---       | :---:       | :---:         | ---                                  |
| OrderID     | TEXT       | **PK** |               | รหัสสัญญาการสั่งซื้อ  |
| Quantity    | INTEGER    |             |               | จำนวน                               |
| Duration    | INTEGER    |             |               | ระยะเวลา                            |
| CustomerID  | TEXT       |   **FK**  |  Customers| รหัสลูกค้า  |
| AddressID   | TEXT       |    **FK**  |  Address | รหัสที่อยู่   |
| ProductID   | TEXT       |   **FK**  |  Products | รหัสสินค้า     |

## Transactions Table
| คอลัมน์    | ประเภท    | PK หรือ FK  | อ้างอิง FK  | คำอธิบาย                           |
| :---        | :---       | :---:       | :---:         | ---                                  |
| TxID        | TEXT       | **PK** |               | รหัสธุรกรรม          |
| TotalPrice  | INTEGER    |             |               | ราคารวม                              |
| Status      | TEXT       |             |               | สถานะ                                |
| TimeSpam    | DATETIME   |             |               | เวลาที่บันทึก                        |
| OrderID     | TEXT       |   **FK**  |  OrderContract | รหัสสัญญาการสั่งซื้อ |

## Products Table
| คอลัมน์     | ประเภท    | PK หรือ FK  | อ้างอิง FK  | คำอธิบาย              |
| :---         | :---       | :---:       | :---:         |-----------------------|
| ProductID    | TEXT       | **PK** |               | รหัสสินค้า            |
| ProductName  | TEXT       |             |               | ชื่อสินค้า            |
| Image1       | BLOB       |             |               | รูปภาพ 1              |
| Image2       | BLOB       |             |               | รูปภาพ 2              |
| Image3       | BLOB       |             |               | รูปภาพ 3              |
| Image4       | BLOB       |             |               | รูปภาพ 4              |
| Type         | TEXT       |             |               | ประเภทสินค้า          |
| Price        | INTEGER    |             |               | ราคา                  |
| SpecDetail   | TEXT       |             |               | รายละเอียดสินค้า JSON |
| DESC         | TEXT       |             |               | คำอธิบายสินค้า        |
| ProductStatus| TEXT       |             |               | สถานะสินค้า           |
| StoreID      | TEXT       |   **FK**  |  Stores | รหัสร้านค้า           |

## Fine Table
| คอลัมน์    | ประเภท    | PK หรือ FK  | อ้างอิง FK  | คำอธิบาย                           |
| :---        | :---       | :---:       | :---:         | ---                                  |
| Fine        | TEXT       | **PK** |               | รหัสค่าปรับ           |
| Date        | DATETIME   |             |               | วันที่                               |
| Description | TEXT       |             |               | คำอธิบายค่าปรับ                      |
| Image1      | BLOB       |             |               | รูปภาพ 1                             |
| Image2      | BLOB       |             |               | รูปภาพ 2                             |
| Image3      | BLOB       |             |               | รูปภาพ 3                             |
| Image4      | BLOB       |             |               | รูปภาพ 4                             |
| PenaltyFee  | INTEGER    |             |               | ค่าปรับ                               |
| Status      | TEXT       |             |               | สถานะ                                |
| CustomerID  | TEXT       |   **FK**  |  Customers| รหัสลูกค้า  |
| StoreID     | TEXT       |   **FK**  |  Stores | รหัสร้านค้า  |
| ProductID   | TEXT       |   **FK**  |  Products | รหัสสินค้า     |


### SQL Script
```sqlite
-- [ People Table ]
CREATE TABLE IF NOT EXISTS People
(
    PersonID    TEXT PRIMARY KEY,
    FirstName   TEXT NOT NULL,
    LastName    TEXT NOT NULL,
    Email       TEXT CHECK (Email LIKE '%_@__%.__%' OR Email LIKE '%@%.%'),
    PhoneNumber TEXT,
    AddressID   TEXT,
    FOREIGN KEY (AddressID) REFERENCES Address (AddressID)
);

-- [ Customers Table ]
CREATE TABLE IF NOT EXISTS Customers
(
    CustomerID TEXT PRIMARY KEY,
    UserName   TEXT NOT NULL UNIQUE,
    AuthKey    TEXT,
    PersonID   TEXT UNIQUE,
    FOREIGN KEY (PersonID) REFERENCES People (PersonID)
);

-- [ Stores Table ]
CREATE TABLE IF NOT EXISTS Stores
(
    StoreID       TEXT PRIMARY KEY,
    StoreName     TEXT NOT NULL UNIQUE,
    PaymentMethod TEXT,
    AuthKey       TEXT,
    PersonID      TEXT UNIQUE,
    FOREIGN KEY (PersonID) REFERENCES People (PersonID)
);

-- [ Address Table ]
CREATE TABLE IF NOT EXISTS Address
(
    AddressID    TEXT PRIMARY KEY,
    LocationName TEXT NOT NULL,
    City         TEXT NOT NULL,
    Province     TEXT NOT NULL,
    PostalNumber TEXT NOT NULL,
    StreetName   TEXT NOT NULL
);

-- [ CreditScore Table ]
CREATE TABLE IF NOT EXISTS CreditScore
(
    ScoreID    TEXT PRIMARY KEY,
    StoreID    TEXT,
    CustomerID TEXT,
    Score      INTEGER,
    Report     TEXT,
    FOREIGN KEY (StoreID) REFERENCES Stores (StoreID),
    FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID)
);

-- [ OrderContract Table ]
CREATE TABLE IF NOT EXISTS OrderContract
(
    OrderID    TEXT PRIMARY KEY,
    ProductID  TEXT,
    Quantity   INTEGER,
    Duration   INTEGER,
    CustomerID TEXT,
    AddressID  TEXT,
    FOREIGN KEY (ProductID) REFERENCES Products (ProductID),
    FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID),
    FOREIGN KEY (AddressID) REFERENCES Address (AddressID)
);

-- [ Transactions Table ]
CREATE TABLE IF NOT EXISTS Transactions
(
    TxID       TEXT PRIMARY KEY,
    TotalPrice INTEGER,
    Status     TEXT,
    TimeSpam   DATETIME,
    OrderID    TEXT,
    FOREIGN KEY (OrderID) REFERENCES OrderContract (OrderID)
);

-- [ Products Table ]
CREATE TABLE IF NOT EXISTS Products
(
    ProductID     TEXT PRIMARY KEY,
    ProductName   TEXT,
    Image1        BLOB,
    Image2        BLOB,
    Image3        BLOB,
    Image4        BLOB,
    Type          TEXT,
    Price         INTEGER,
    SpecDetail    TEXT,
    DESC          TEXT,
    ProductStatus TEXT,
    StoreID       TEXT,
    FOREIGN KEY (StoreID) REFERENCES Stores (StoreID)
);


-- [ FineTable ]
CREATE TABLE IF NOT EXISTS Fine
(
    Fine        TEXT PRIMARY KEY,
    CustomerID  TEXT,
    StoreID     TEXT,
    ProductID   TEXT,
    Date        DATETIME DEFAULT 'N/A',
    Description TEXT,
    Image1      BLOB,
    Image2      BLOB,
    Image3      BLOB,
    Image4      BLOB,
    PenaltyFee  INTEGER,
    Status      TEXT NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID),
    FOREIGN KEY (StoreID) REFERENCES Stores (StoreID),
    FOREIGN KEY (ProductID) REFERENCES Products (ProductID)
);




-- [ คำสั่งลบตาราง ]
DROP TABLE IF EXISTS People;

DROP TABLE IF EXISTS Customers;

DROP TABLE IF EXISTS Stores;

DROP TABLE IF EXISTS Address;

DROP TABLE IF EXISTS CreditScore;

DROP TABLE IF EXISTS OrderContract;

DROP TABLE IF EXISTS Transactions;

DROP TABLE IF EXISTS Products;

DROP TABLE IF EXISTS Fine;

```

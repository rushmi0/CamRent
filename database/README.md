# Database Design

เราได้เลือกใช้ SQLite เป็นระบบจัดเก็บข้อมูลใน Project ของเราเพื่อประโยชน์ในด้านหลาย ๆ ด้าน โดย SQLite เป็นระบบจัดเก็บข้อมูลแบบ SQL (Structured Query Language) ที่ทำงานโดยการเก็บข้อมูลลงในไฟล์เดียว มีความคุ้มค่าอย่างมากในสถานการณ์ที่เราต้องการความเร็วและความสะดวกในการใช้งานข้อมูลของโครงการของเรา

SQLite เหมาะสำหรับการสร้างเว็บ Demo อย่างมีเหตุผล เนื่องจากมีข้อได้เปรียบที่สำคัญทั้งในด้านความเร็วในการเรียกใช้ข้อมูลและความง่ายในการใช้งาน โดยเราสามารถเริ่มใช้งาน SQLite ได้ทันทีโดยไม่จำเป็นต้องติดตั้งเซิร์ฟเวอร์ฐานข้อมูลอื่น ๆ อันซับซ้อน นี่เป็นเหตุผลหนึ่งที่ทำให้ SQLite เหมาะสำหรับการนำเสนอแอปพลิเคชันขนาดเล็กหรือทำการทดสอบ

ระบบนี้ยังรองรับการทำงานทั้งในโหมดออนไลน์และออฟไลน์อย่างมีประสิทธิภาพเช่นกัน ทำให้เราสามารถใช้งานข้อมูลได้โดยต่อเนื่องไม่ว่าจะอยู่ในสถานการณ์ใดๆ และทำให้โครงการของเรามีความยืดหยุ่นในการดำเนินการต่าง ๆ ได้มากยิ่งขึ้น ดังนั้นเราเชื่อว่าการเลือกใช้ SQLite เป็นระบบจัดเก็บข้อมูลเป็นคำตอบที่เหมาะสมสำหรับ Project ของเราในทุกปัจจัย

****
**Customers Table:**

- CustomerID (**PK**)
    + UserName
    + FirstName
    + LastName
    + Email
    + PhoneNumber
    + ImageProfile
    + AuthKey
    + ContactID (**PK, PK,FK** from Contact)
    + AddressID (**PK, PK,FK** from Address)


****
**Stores Table:**

- StoreID (**PK**)
    + StoreName
    + FirstName
    + LastName
    + Email
    + PhoneNumber
    + PaymentMethod
    + ImageStore
    + AuthKey
    + ContactID (**PK,FK** from Address)
    + AddressID (**PK,FK** from Address)
    + ProductID (**PK,FK** from Address)
    + ScoreID (**PK,FK** from Address)


****
**Address Table:**

- AddressID (**PK**)
    + LocationName
    + City
    + Province
    + PostalNumber
    + StreetName


****
**CreditScore Table:**

- ScoreID (**PK**)
    + StoreID (**PK,FK** from Stores)
    + CustomerID (**PK,FK** from Customers)
    + Score


****
**Cart Table:**

- CartID (**PK**)
    + ProductID (**PK,FK** from Address)
    + Quantity
    + Duration
    + CustomerID (**PK,FK** from Customers)
    + ContactID (**PK,FK** from Contact)
    + AddressID (**PK,FK** from Customers)


****
**Transactions Table:**

- TxID
   + TotalPrice
   + Status
   + TimeSpam 
   + CartID (**PK,FK** from Cart)
   + ProductID (**PK,FK** from Cart)
   + ContactID (**PK,FK** from Cart)
   + CustomerID (**PK,FK** from Cart)
   + AddressID (**PK,FK** from Cart)


****
**Products Table:**

- ProductID (**PK**)
    + ProductName
    + Image1
    + Image2
    + Image3
    + Image4
    + Type
    + Price
    + SpecDetail 
    + DESC
    + ProductStatus 
    + StoreID (**PK,FK** from Stores)

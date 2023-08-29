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
    + ImageProfile
    + AuthKey
    + ContactID (**FK** from Contact)
    + AddressID (**FK** from Address)


****
**Stores Table:**

- StoreID (**PK**)
    + StoreName
    + FirstName
    + LastName
    + PaymentMethod
    + ImageStore
    + AuthKey
    + ContactID (**FK** from Address)
    + AddressID (**FK** from Address)
    + ProductID (**FK** from Address)
    + ScoreID (**FK** from Address)


****
**Contact Table:**

- ContactID (**PK**)
  + Email
  + PhoneNumber


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
    + StoreID (**FK** from Stores)
    + CustomerID (**FK** from Customers)
    + Score


****
**Cart Table:**

- CartID (**PK**)
    + ProductID (**FK** from Address)
    + Quantity
    + Duration
    + CustomerID (**FK** from Customers)
    + ContactID (**FK** from Customers)
    + AddressID (**FK** from Customers)


****
**Transactions Table:**

- TxID
   + TotalPrice
   + Status
   + TimeSpam 
   + CartID (**FK** from Cart)
   + ProductID (**FK** from Cart)
   + CustomerID (**FK** from Cart)


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
    + SpecDetail (json)
    + DESC
    + ProductStatus 
    + StoreID (**FK** from Stores)

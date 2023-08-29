# Database Design

เราได้เลือกใช้ SQLite ซึ่งเป็นระบบจัดเก็บข้อมูลใน Project ของเราเพื่อประโยชน์ในด้านหลาย ๆ ด้าน โดย SQLite เป็นระบบจัดเก็บข้อมูลแบบ SQL (Structured Query Language) ที่ทำงานโดยการเก็บข้อมูลลงในไฟล์เดียว มีความคุ้มค่าอย่างมากในสถานการณ์ที่เราต้องการความเร็วและความสะดวกในการใช้งานข้อมูลของโครงการของเรา

SQLite เหมาะสำหรับการสร้างเว็บ Demo อย่างมีเหตุผล เนื่องจากมีข้อได้เปรียบที่สำคัญทั้งในด้านความเร็วในการเรียกใช้ข้อมูลและความง่ายในการใช้งาน โดยเราสามารถเริ่มใช้งาน SQLite ได้ทันทีโดยไม่จำเป็นต้องติดตั้งเซิร์ฟเวอร์ฐานข้อมูลอื่น ๆ อันซับซ้อน นี่เป็นเหตุผลหนึ่งที่ทำให้ SQLite เหมาะสำหรับการนำเสนอแอปพลิเคชันขนาดเล็กหรือทำการทดสอบ

ระบบนี้ยังรองรับการทำงานทั้งในโหมดออนไลน์และออฟไลน์อย่างมีประสิทธิภาพเช่นกัน ทำให้เราสามารถใช้งานข้อมูลได้โดยต่อเนื่องไม่ว่าจะอยู่ในสถานการณ์ใดๆ และทำให้โครงการของเรามีความยืดหยุ่นในการดำเนินการต่าง ๆ ได้มากยิ่งขึ้น ดังนั้นเราเชื่อว่าการเลือกใช้ SQLite เป็นระบบจัดเก็บข้อมูลเป็นคำตอบที่เหมาะสมสำหรับ Project ของเราในทุกปัจจัย

****
**Customers Table:**

- CustomerID (PK)
    + UserName
    + FirstName
    + LastName
    + Email
    + PhoneNumber
    + ImageProfile
    + AuthKey
    + AddressID (FK from Address)


****
**Stores Table:**

- StoreID (PK)
    + StoreName
    + FirstName
    + LastName
    + Email
    + PhoneNumber
    + PaymentMethod
    + ImageStore
    + AuthKey
    + AddressID (FK from Address)
    + ProductID (FK from Products)
    + ScoreID (FK from CreditScore)


****
**Address Table:**

- AddressID (PK)
    + LocationName
    + City
    + Province
    + PostalNumber
    + StreetName


**CreditScore Table:**

- ScoreID (PK)
    + StoreID (FK from Stores)
    + CustomerID (FK from Customers)
    + Score
    + Report


****
**Cart Table:**

- CartID (PK)
    + ProductID (FK from Products)
    + Quantity
    + Duration
    + CustomerID (FK from Customers)
    + AddressID (FK from Address)


****
**Transactions Table:**

- TxID (PK)
    + TotalPrice
    + Status
    + TimeSpam
    + CartID (FK from Cart)


****
**Products Table:**

- ProductID (PK)
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
    + StoreID (FK from Stores)


### การแสดงเส้นเชื่อมและความสัมพันธ์ระหว่าง Entity ในโครงสร้างฐานข้อมูล

1. **Customers** (1) ---- (N) **Address**
   + ลูกค้า (Customers) สามารถมีหลายที่อยู่ (Address) แต่แต่ละที่อยู่เชื่อมต่อกับลูกค้าเพียงคนเดียว (1 to N ความสัมพันธ์)

2. **Stores** (1) ---- (N) **Address**
   + ร้านค้า (Stores) สามารถมีหลายที่อยู่ (Address) แต่แต่ละที่อยู่เชื่อมต่อกับร้านค้าเพียงคนเดียว (1 to N ความสัมพันธ์)

3. **Stores** (1) ---- (N) **Products**
   + ร้านค้า (Stores) สามารถมีหลายสินค้า (Products) แต่แต่ละสินค้าเชื่อมต่อกับร้านค้าเพียงคนเดียว (1 to N ความสัมพันธ์)

4. **Stores** (1) ---- (1) **CreditScore**
   + ร้านค้า (Stores) และคะแนนเครดิต (CreditScore) เชื่อมต่อกันแบบ 1 ต่อ 1 (1 to 1 ความสัมพันธ์)

5. **Customers** (1) ---- (N) **Cart**
   + ลูกค้า (Customers) สามารถมีหลายตะกร้า (Cart) และแต่ละตะกร้าเชื่อมต่อกับลูกค้าเพียงคนเดียว (1 to N ความสัมพันธ์)

6. **Products** (1) ---- (N) **Cart**
   + สินค้า (Products) สามารถมีในหลายตะกร้า (Cart) และแต่ละตะกร้าเชื่อมต่อกับสินค้าเพียงคนเดียว (1 to N ความสัมพันธ์)

7. **Cart** (1) ---- (1) **Transactions**
   + ตะกร้า (Cart) และรายการธุรกรรม (Transactions) เชื่อมต่อกันแบบ 1 ต่อ 1 (1 to 1 ความสัมพันธ์)

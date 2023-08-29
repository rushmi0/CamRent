<b style="color: #272323">Customers Table:</b>

- <b style="color: #98A8F8">CustomerID (Primary Key)</b>
    + UserName
    + FirstName
    + LastName
    + ImageProfile
    + AuthKey
    + <b style="color: #FB2C72">ContactID (Foreign key from Contact)</b> 
    + <b style="color: #FB2C72">AddressID (Foreign key from Address)</b>


****
<b style="color: #272323">Stores Table:</b>

- <b style="color: #98A8F8">StoreID (Primary Key)</b>
    + StoreName
    + FirstName
    + LastName
    + PaymentMethod
    + ImageStore
    + AuthKey
    + ContactID (Foreign key from Address)
    + AddressID (Foreign key from Address)
    + ProductID (Foreign key from Address)
    + ScoreID (Foreign key from Address)


****
<b style="color: #272323">Address Table:</b>

- <b style="color: #98A8F8">AddressID (Primary Key)</b>
    + LocationName
    + City
    + Province
    + PostalNumber
    + StreetName


****
<b style="color: #272323">Contact Table:</b>

- <b style="color: #98A8F8">ContactID (Primary Key)</b>
    + Email
    + PhoneNumber


****
<b style="color: #272323">CreditScore Table:</b>

- <b style="color: #98A8F8">ScoreID (Primary Key)</b>
    + StoreID (Foreign key from Stores)
    + CustomerID (Foreign key from Customers)
    + Score


****
<b style="color: #272323">Cart Table:</b>

- <b style="color: #98A8F8">CartID (Primary Key)</b>
    + ProductID (Foreign key from Address)
    + Quantity
    + Duration
    + CustomerID (Foreign key from Customers)


****
<b style="color: #272323">Transactions Table:</b>

- <b style="color: #98A8F8">TxID</B>
   + TotalPrice
   + Status
   + TimeSpam 
   + CartID (Foreign key from Cart)
   + ProductID (Foreign key from Cart)
   + CustomerID (Foreign key from Cart)


****
<b style="color: #272323">Products Table:</b>

- <b style="color: #98A8F8">ProductID (Primary Key)</b>
    + ProductName (not null)
    + Image1 (not null)
    + Image2
    + Image3
    + Image4
    + Type (Lens Filters, Lens, Tripod, Flash, Flash Diffuser, (Compact, Mirrorless, DSLR))
    + Price
    + SpecDetail (json)
    + DESC
    + ProductStatus 
    + StoreID (Foreign key from Stores)

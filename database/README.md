**Customers Table:**

- CustomerID (Primary Key)
    + UserName
    + FirstName
    + LastName
    + ImageProfile
    + AuthKey
    + ContactID (Foreign key referencing Address)
    + AddressID (Foreign key referencing Address)


**Stores Table:**

- StoreID (Primary Key)
    + StoreName
    + FirstName
    + LastName
    + PaymentMethod
    + ImageStore
    + AuthKey
    + ContactID (Foreign key referencing Address)
    + AddressID (Foreign key referencing Address)
    + ProductID (Foreign key referencing Address)
    + ScoreID (Foreign key referencing Address)


**Address Table:**

- AddressID (Primary Key)
    + LocationName
    + City
    + Province
    + PostalNumber
    + StreetName


**Contact Table:**

- ContactID (Primary Key)
    + Email
    + PhoneNumber


**CreditScore Table:**

- ScoreID (Primary Key)
    + StoreID (Foreign key referencing Stores)
    + CustomerID (Foreign key referencing Customers)
    + Score


**Cart Table:**

- CartID (Primary Key)
    + ProductID (Foreign key referencing Address)
    + Quantity
    + Duration
    + CustomerID (Foreign key referencing Customers)


**Transactions Table:**

- TxID
   + TotalPrice
   + Status
   + TimeSpam
   + CartID


**Products Table:**

- ProductID (Primary Key)
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
    + StoreID (Foreign key referencing Stores)

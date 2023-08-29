# Database Design


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
**Address Table:**

- AddressID (**PK**)
    + LocationName
    + City
    + Province
    + PostalNumber
    + StreetName


****
**Contact Table:**

- ContactID (**PK**)
    + Email
    + PhoneNumber


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
    + StoreID (**FK** from Stores)

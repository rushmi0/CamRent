**Customers Table:**

- CustomerID (Primary Key)
    + UserName
    + FirstName
    + LastName
    + Email
    + PhoneNumber
    + ImagePath
    + authKey
    + AddressID (Foreign key referencing Address)



**Stores Table:**


- StoreID (Primary Key)
    + StoreName
    + FirstName
    + LastName
    + Details (json)
    + PaymentMethod
    + Email
    + PhoneNumber
    + ImagePath
    + authKey
    + AddressID (Foreign key referencing Address)



**Address Table:**

- AddressID (Primary Key)
    + LocationName
    + City
    + Province
    + PostalNumber




**CreditScore Table:**

Customers can rate multiple restaurants,
each restaurant can receive points from multiple customers.

- ScoreID (Primary Key)
    + StoreID (Foreign key referencing Stores)
    + CustomerID (Foreign key referencing Customers)
    + Score



**ContractAlders Table:**

- AlderID (Primary Key)
    + Quantity
    + Price
    + Duration
    + CustomerID (Foreign key referencing Customers)



**Products Table:**

- ProductID (Primary Key)

    + ProductName
    + ProductType
    + ProductPrice
    + Details (json)
    + ProductStatus
    + StoreID (Foreign key referencing Stores)



**Camera Table:**

- CameraID (Primary Key)
    + Type
    + Image
    + Name
    + Details (json)


**LensFilters  Table:**

- FilterID (Primary Key)
    + Name

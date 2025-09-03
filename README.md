# Library Management System

A comprehensive web-based Library Management System designed to efficiently manage books, members, and lending activities. This application provides a user-friendly interface for librarians and administrators to handle day-to-day library operations with ease.


## ✨ Features

### Book Management
- **Add New Books**: Register books with detailed information including title, author, ISBN, category, and availability status
- **Update Book Information**: Modify existing book details and maintain up-to-date records
- **Search Books**: Advanced search functionality by title, author, ISBN, or category
- **Book Inventory**: Track book availability and manage multiple copies

### Member Management
- **Member Registration**: Register new library members with personal details
- **Member Profile Management**: Update and maintain member information
- **Member Search**: Find members quickly using various search criteria
- **Membership Status Tracking**: Monitor active and inactive memberships

### Lending Operations
- **Issue Books**: Process book checkouts with automatic availability updates
- **Return Books**: Handle book returns with date tracking and fine calculations
- **Lending History**: Complete transaction history for both books and members
- **Overdue Management**: Track overdue books and calculate fines automatically


## 🛠️ Technologies Used

### Backend
- **Java** - Core programming language
- **Spring Boot** - Application framework
- **Spring Data JPA** - Data persistence layer
- **Spring Security** - Authentication and authorization
- **PostgreSQL** - Primary database


### Development Tools
- **IntelliJ IDEA Ultimate** - Integrated Development Environment
- **DBeaver Ultimate** - Database management tool
- **Maven** - Dependency management and build automation

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 11 or higher**
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Git** (for cloning the repository)
- **IntelliJ IDEA** (recommended) or any Java IDE

## 🚀 
## ⚙️ Configuration

### Database Configuration
The application uses PostgreSQL as the primary database. Key configuration options:

- **Connection Pool**: HikariCP (default with Spring Boot)
- **JPA Settings**: Hibernate as the ORM provider
- **Database Initialization**: Tables are created automatically on first run



### Core Operations

#### Managing Books
1. Navigate to **Books** section
2. Click **Add New Book** to register a new book
3. Fill in book details: Title, Author, ISBN, Category, etc.
4. Use the search functionality to find specific books
5. Edit or delete books as needed

#### Managing Members
1. Go to **Members** section
2. Click **Add New Member** to register a library member
3. Enter member details and contact information
4. Search and manage existing members

#### Processing Transactions
1. **Issue Book**: Select member and book, then process the checkout
2. **Return Book**: Scan book or search, then process the return
3. **View History**: Check lending history for books and members

## 🔗 API Endpoints

### Books API
```
GET    /api/books                 - Get all books
GET    /api/books/{id}           - Get book by ID
POST   /api/books                - Create new book
PUT    /api/books/{id}           - Update book
DELETE /api/books/{id}           - Delete book
GET    /api/books/search         - Search books
```

### Members API
```
GET    /api/members              - Get all members
GET    /api/members/{id}         - Get member by ID
POST   /api/members              - Create new member
PUT    /api/members/{id}         - Update member
DELETE /api/members/{id}         - Delete member
```

### Transactions API
```
GET    /api/transactions         - Get all transactions
POST   /api/transactions/issue   - Issue a book
POST   /api/transactions/return  - Return a book
GET    /api/transactions/overdue - Get overdue books
```

## 🗄️ Database Schema

### Core Tables
- **books**: Book information and inventory
- **members**: Library member details
- **transactions**: Lending and return records
- **categories**: Book categories
- **authors**: Author information
- **fines**: Fine calculations and payments

### Key Relationships
- Books → Categories (Many-to-One)
- Books → Authors (Many-to-Many)
- Transactions → Books (Many-to-One)
- Transactions → Members (Many-to-One)

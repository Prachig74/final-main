# Property App

## Overview
This is a property management application built with **Spring Boot**, **MySQL**, **Redis**, and **JWT** for authentication. The backend is deployed on **Render** and uses **Aiven MySQL** and **Upstash Redis** for storage and caching.

## Features
- User registration and login with JWT authentication.
- Property CRUD operations (Create, Read, Update, Delete).
- Property listing with pagination support.
- Caching of GET API responses using Redis.

## Technologies
- **Spring Boot** 3.4.2
- **Java 21**
- **MySQL** (Aiven)
- **Redis** (Upstash)
- **JWT Authentication**
- **Spring Security**
- **Spring Data JPA**
- **Spring Boot DevTools** for automatic restarts
- **Maven** for build and dependency management

## Deployed API Endpoints

The backend is deployed on **Render** and can be accessed via the following endpoints.

### 1. **User APIs**

- **POST** `/api/auth/signup`  
  - URL: `https://final-main-1-3mad.onrender.com/api/auth/signup`
  - Request Body:
    ```json
    {
      "username": "string",
      "email": "string",
      "password": "string"
    }
    ```

- **POST** `/api/auth/login`  
  - URL: `https://final-main-1-3mad.onrender.com/api/auth/login`
  - Request Body:
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```

### 2. **Property APIs**

- **GET** `/properties?page=0&size=9`  
  - URL: `https://final-main-1-3mad.onrender.com/properties?page=0&size=9`  
  - Description: Retrieves a paginated list of properties.

- **POST** `/properties/add`  
  - URL: `https://final-main-1-3mad.onrender.com/properties/add`  
  - Request Body:
    ```json
    {
      "title": "string",
      "address": "string",
      "description": "string",
      "price": "number",
      "bhks": "number",
      "available_date": "YYYY-MM-DD",
      "amenities": "string"
    }
    ```

- **GET** `/properties/{id}`  
  - URL: `https://final-main-1-3mad.onrender.com/properties/{id}`  
  - Description: Retrieves a specific property by ID.

- **GET** `/properties/all`  
  - URL: `https://final-main-1-3mad.onrender.com/properties/all`  
  - Description: Retrieves all properties.

- **PUT** `/properties/{id}`  
  - URL: `https://final-main-1-3mad.onrender.com/properties/{id}`  
  - Request Body:
    ```json
    {
      "title": "string",
      "address": "string",
      "description": "string",
      "price": "number",
      "bhks": "number",
      "available_date": "YYYY-MM-DD",
      "amenities": "string"
    }
    ```

- **DELETE** `/properties/{id}`  
  - URL: `https://final-main-1-3mad.onrender.com/properties/{id}`  
  - Description: Deletes a property by ID.




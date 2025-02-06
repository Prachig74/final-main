# final-main
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

 **POST** `/api/auth/signup`
  - URL: `https://final-main-1-3mad.onrender.com/api/auth/signup`
  **POST** `/api/auth/login`
  - URL: `https://final-main-1-3mad.onrender.com/api/auth/login`
  - # 2. **Property APIs**

- **GET** `/properties?page=0&size=9`
  - URL: `https://final-main-1-3mad.onrender.com/properties?page=0&size=9`
 **POST** `/properties/add`
  - URL: `https://final-main-1-3mad.onrender.com/properties/add`
     **GET** `/properties/{id}`
  - URL: `https://final-main-1-3mad.onrender.com/properties/{id}`
  - **GET** `/properties/all`
  - URL: `https://final-main-1-3mad.onrender.com/properties/all`
    **PUT** `/properties/{id}`
  - URL: `https://final-main-1-3mad.onrender.com/properties/{id}`
  **DELETE** `/properties/{id}`
  - URL: `https://final-main-1-3mad.onrender.com/properties/{id}`
  

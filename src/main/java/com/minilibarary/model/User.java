package com.minilibarary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

    @Entity
    @Table(name = "user", schema = "library_mini_project")
    @AllArgsConstructor(staticName = "build")
    @NoArgsConstructor
    @Getter
    @Setter
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userId;

        @Email
        @Column(name = "email", unique = true)
        private String email;

        @NotNull
        @NotBlank(message = "name cannot be blank")
        @NotEmpty(message = "name cannot be empty")
        @Column(name = "fullName")
        private String fullName;


        @NotEmpty
        @NotBlank
        @NotNull
        @Column(name = "age")
        @Length(min = 18, max = 70, message = "Age cannot be less than 18 or greater than 70")
        private String age;

        @NotEmpty(message = "address cannot be empty")
        @NotBlank
        @NotNull
        @Column(name = "address")
        private String address;


        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

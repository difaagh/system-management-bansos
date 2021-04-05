/*
 * The MIT License
 *
 * Copyright 2021 difaagh.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.msb.app.management.system.bansos.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author difaagh
 */
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate = new Date();

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate() {
        this.createdDate = new Date();
    }

    @Column(name = "username")
    private String username;
    
    public String getUsername(){
        return this.username;
    }
    
    
    public void setUsername(String username){
        this.username = username;
    }
    
    @Column(name = "password")
    private String password;
    
    public String getPassword(){
        return this.password;
    }
    
    
    public void setPassword(String password){
      this.password = password;
    }
    
    @Column(name = "role")
    private String role;
    
    public String getRole(){
        return this.role;
    }
    
    public void setRole(String role){
        this.role = role;
    }
}


package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"strings"

	"github.com/go-sql-driver/mysql"
)

type User struct {
	Username string
	Password string
	Name     string
	Surname  string
}

type LoginResponse struct {
	Response bool `json:"login"`
}

type LoginRequest struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

func findUsersByUsername(db *sql.DB, username string) User {
	var user User
	row := db.QueryRow("SELECT * FROM user WHERE username = ?", strings.TrimSpace(username))

	row.Scan(&user.Username, &user.Password, &user.Name, &user.Surname)

	return user
}

func login(username string, password string) bool {
	cfg := mysql.NewConfig()
	cfg.User = "admin"
	cfg.Passwd = "admin"
	cfg.Net = "tcp"
	cfg.Addr = "127.0.0.1:3306"
	cfg.DBName = "users"

	var db, err = sql.Open("mysql", cfg.FormatDSN())

	if err != nil {
		log.Fatal(err)
	}

	pingErr := db.Ping()
	if pingErr != nil {
		log.Fatal(pingErr)
	}

	user := findUsersByUsername(db, username)

	if user.Username == "" {
		fmt.Println("User not found!")
		return false
	} else {
		if user.Password != password {
			fmt.Println("Password mismatch!")
			return false
		} else {
			fmt.Println("Login allowed")
			return true
		}
	}

}

func loginRequestHandler(w http.ResponseWriter, r *http.Request) {
	var loginRequest LoginRequest
	json.NewDecoder(r.Body).Decode(&loginRequest)

	var res = login(loginRequest.Username, loginRequest.Password)

	var loginResponse LoginResponse
	loginResponse.Response = res

	if !res {
		w.WriteHeader(http.StatusUnauthorized)
	} else {
		w.WriteHeader(http.StatusOK)
	}

	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(loginResponse)
}

func main() {
	http.HandleFunc("/api/login", loginRequestHandler)

	log.Println("Authentication Service running on :3001")
	http.ListenAndServe(":3001", nil)
}

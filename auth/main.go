package main

import (
	"encoding/json"
	"log"
	"net/http"
)

type TokenResponse struct {
	Access  string `json:"access_token"`
	Refresh string `json:"refresh_token"`
}

type VerifyResponse struct {
	Valid bool `json:"valid"`
}

type LoginRequest struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

func verifyUserCredentials(r *http.Request) (bool, error) {

	resp, err := http.Post("http://localhost:3001/api/login", "application/json", r.Body)
	if err != nil {
		return false, err
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		return false, nil
	}

	return true, nil
}

func jwtAccessHandler(w http.ResponseWriter, r *http.Request) {
	resp, err := verifyUserCredentials(r)
	if err != nil || !resp {
		w.WriteHeader(http.StatusUnauthorized)
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(`{"message":"Invalid credentials"}`)
	}

	response := TokenResponse{
		Access:  "temp",
		Refresh: "temp",
	}

	w.WriteHeader(http.StatusOK)
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(response)
}

func jwtRefreshHandler(w http.ResponseWriter, r *http.Request) {
	response := TokenResponse{
		Access:  "temp",
		Refresh: "temp",
	}

	w.WriteHeader(http.StatusOK)
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(response)
}

func jwtVerifyHandler(w http.ResponseWriter, r *http.Request) {
	response := VerifyResponse{Valid: true}

	w.WriteHeader(http.StatusOK)
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(response)
}

func main() {
	http.HandleFunc("/api/jwt/access", jwtAccessHandler)
	http.HandleFunc("/api/jwt/refresh", jwtRefreshHandler)
	http.HandleFunc("/api/jwt/verify", jwtVerifyHandler)

	log.Println("Authentication Service running on :3000")
	http.ListenAndServe(":3000", nil)
}

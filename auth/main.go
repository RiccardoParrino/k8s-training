package main

import (
	"encoding/json"
	"log"
	"net/http"
	"time"

	"github.com/golang-jwt/jwt/v5"
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

func GenerateAccessJWT(username string) (string, string, error) {
	claims := jwt.MapClaims{
		"username": username,
		"exp":      time.Now().Add(time.Hour * 24).Unix(),
	}

	refreshClaims := jwt.MapClaims{
		"username": username,
		"exp":      time.Now().Add(time.Hour * 24 * 7).Unix(),
	}

	accessToken := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
	accessTokenString, err := accessToken.SignedString("jwtSecret")
	if err != nil {
		return "", "", err
	}

	refreshToken := jwt.NewWithClaims(jwt.SigningMethodHS256, refreshClaims)
	refreshTokenString, err := refreshToken.SignedString("jwtSecret")
	if err != nil {
		return "", "", err
	}

	return accessTokenString, refreshTokenString, nil
}

func jwtAccessHandler(w http.ResponseWriter, r *http.Request) {
	resp, err := verifyUserCredentials(r)
	if err != nil || !resp {
		w.WriteHeader(http.StatusUnauthorized)
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(`{"message":"Invalid credentials"}`)
	}

	var loginRequest LoginRequest
	err = json.NewDecoder(r.Body).Decode(&loginRequest)
	if err != nil {
		http.Error(w, "Invalid request", http.StatusBadRequest)
		return
	}

	accessToken, refreshToken, err := GenerateAccessJWT(loginRequest.Username)
	if err != nil {
		response := TokenResponse{
			Access:  accessToken,
			Refresh: refreshToken,
		}
		w.WriteHeader(http.StatusOK)
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(response)
	} else {
		response := TokenResponse{
			Access:  "",
			Refresh: "",
		}
		w.WriteHeader(http.StatusUnauthorized)
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(response)
	}
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

<template>
  <div class="auth-container">
    <div class="auth-card">
      <v-alert class="mx-2 p-2 font-13 mt-5" :value="alert.show" :color="alert.color" outlined dense>
                        <template v-if="alert.icon">
                            <v-icon class="pr-1">{{ alert.icon }}</v-icon>
                        </template>
                        <span>{{ alert.message }}</span>
                    </v-alert>
      <!-- Login Form -->
      <form v-if="showForm === 'login'" @submit.prevent="handleLogin" class="auth-form">
        <h2 class="form-title">Login</h2>
        <div class="form-input">
          <label for="login-username">Username</label>
          <input
            id="login-username"
            v-model="loginData.username"
            type="email"
            placeholder="Enter your username"
            required
          />
        </div>
        <div class="form-input">
          <label for="login-password">Password</label>
          <input
            id="login-password"
            v-model="loginData.password"
            type="password"
            placeholder="Enter your password"
            required
          />
        </div>
        <button type="submit" class="submit-btn">Login</button>
        <div class="form-footer">
          <span class="footer-text">Don't have an account?</span>
          <button @click="showSignUpForm" class="footer-link">Sign Up</button>
        </div>
      </form>

      <!-- Sign-Up Form -->
      <form v-if="showForm === 'signup'" @submit.prevent="handleSignUp" class="auth-form">
        <h2 class="form-title">Sign Up</h2>
        <div class="form-input">
          <label for="signup-firstname">First Name</label>
          <input
            id="signup-firstname"
            v-model="signUpData.firstName"
            type="text"
            placeholder="Enter your first name"
            required
          />
        </div>
        <div class="form-input">
          <label for="signup-lastname">Last Name</label>
          <input
            id="signup-lastname"
            v-model="signUpData.lastName"
            type="text"
            placeholder="Enter your last name"
            required
          />
        </div>
        <div class="form-input">
          <label for="signup-username">Username</label>
          <input
            id="signup-username"
            v-model="signUpData.username"
            type="email"
            placeholder="Enter your email"
            required
          />
        </div>
        <div class="form-input">
          <label for="signup-password">Password</label>
          <input
            id="signup-password"
            v-model="signUpData.password"
            type="password"
            placeholder="Enter your password"
            required
          />
        </div>
        <div class="form-input">
          <label for="signup-roles">Roles</label>
          <select id="signup-roles" v-model="signUpData.roles" required>
            <option value="ADMIN">ADMIN</option>
            <option value="SUB_ADMIN">SUB_ADMIN</option>
            <option value="AGENT">AGENT</option>
            <option value="SUB_AGENT">SUB_AGENT</option>
          </select>
        </div>
        <button type="submit" class="submit-btn">Sign Up</button>

        <!-- Back to Login Button -->
        <div class="form-footer">
          <button @click="showLoginForm" class="back-to-login-btn">Back to Login</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      showForm: "login", // Default to the login form
      loginData: {
        username: "",
        password: "",
      },
      signUpData: {
        firstName: "",
        lastName: "",
        username: "",
        password: "",
        roles: "ADMIN", // Default roles is ADMIN
      },
      alert: {
                    show: false,
                    color: '',
                    icon: '',
                    message: '',
                    type:'error'
                  },
      userData: null, // To store user details including roles
    };
  },
  methods: {
    showSignUpForm() {
      this.showForm = "signup";
    },
    showLoginForm() {
      this.showForm = "login";
    },
    async handleLogin() {
      try {
        const response = await axios.post("http://localhost:8080/auth/login", this.loginData);
        console.log("Login Response:", response.data);
        this.userData = response.data; 
        console.log("=-=-=-=-=-",this.userData.status.type);
        
        if(this.userData.status.type === 'SUCCESS'){
          console.log()
          this.$router.push('/home')
        }
        else if(this.userData.status.type === 'FAILURE'){
          this.alert = {
                  show: true,
                  color: 'error',
                  message: "The username or password entered is invalid.",
                };
        }
        this.resetForms();
      } catch (error) {
        console.error("Login Error:", error);
        alert("Login failed. Please try again.");
      }
    },
    async handleSignUp() {
      try {
        const response = await axios.post("http://localhost:8080/auth/signup", this.signUpData);
        console.log("Sign-Up Response:", response.data);
        this.userData = response.data; 
        console.log("=--=-=-=-=-=-",this.userData);
        
        // alert(`Sign-up successful! roles: ${this.userData.roles}`);
        this.resetForms();
      } catch (error) {
        console.error("Sign-Up Error:", error);
        alert("Sign-up failed. Please try again.");
      }
    },
    resetForms() {
      this.showForm = "login"; // Show the login form after action
      this.loginData = { username: "", password: "" };
      this.signUpData = { firstName: "", lastName: "", username: "", password: "", roles: "ADMIN" };
    },
  },
};
</script>

<style scoped>
/* General styling for the page */
.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f7f7f7;
}

.auth-card {
  width: 100%;
  max-width: 400px;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 30px;
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form-title {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  color: #333;
}

.form-input {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.form-input label {
  font-size: 14px;
  color: #555;
}

.form-input input,
.form-input select {
  padding: 10px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 8px;
  outline: none;
  transition: border-color 0.3s ease;
}

.form-input input:focus,
.form-input select:focus {
  border-color: #4caf50;
}

.submit-btn {
  background-color: #4caf50;
  color: white;
  font-size: 16px;
  padding: 12px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit-btn:hover {
  background-color: #45a049;
}

.form-footer {
  text-align: center;
  font-size: 14px;
}

.footer-text {
  color: #777;
}

.footer-link {
  color: #4caf50;
  cursor: pointer;
  border: none;
  background: none;
  text-decoration: underline;
}

.footer-link:hover {
  color: #45a049;
}

.back-to-login-btn {
  background-color: #f0f0f0;
  color: #4caf50;
  font-size: 14px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
  width: 100%;
}

.back-to-login-btn:hover {
  background-color: #4caf50;
  color: white;
}

select {
  cursor: pointer;
}
</style>

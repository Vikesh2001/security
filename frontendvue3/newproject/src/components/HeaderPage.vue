<template>
  <header class="header">
    <div class="container">
      <h1 class="logo">School Management System</h1>
      <nav>
        <!-- Only show nav links if the current path is not '/login' -->
        <ul class="nav-links" v-if="!isLoginPage">
          <li><router-link to="/">Home</router-link></li>
          <li><router-link to="/contact">Contact</router-link></li>
          <li><router-link to="/login">Logout</router-link></li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script>
export default {
  name: "HeaderPage",
  computed: {
    // Check if the current route is the login page
    isLoginPage() {
      return this.$route.path === '/login';
    }
  }
};
</script>

<style scoped>
.header {
  background-color: hwb(330 8% 16% / 0.363);
  padding: 20px 0;
  color: white;
}

.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.logo {
  font-size: 24px;
  font-weight: bold;
}

.nav-links {
  display: flex;
  gap: 20px;
}

.nav-links a {
  color: white;
  text-decoration: none;
}

.nav-links a:hover {
  text-decoration: underline;
}
</style>

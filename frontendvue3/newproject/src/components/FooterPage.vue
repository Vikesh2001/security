<template>
  <footer class="footer">
    <div class="container">
      <p>&copy; 2025 My Vue App. All rights reserved.</p>
    </div>
  </footer>
</template>

<script>
export default {
  name: "FooterPage",
};
</script>

<style scoped>
.footer {
  background-color: #333;
  color: white;
  padding: 20px 0;
  text-align: center;
  margin-top: auto; /* Pushes the footer to the bottom */
}

.container {
  padding: 0 20px;
}
</style>
